package com.thinkinjava.example.annotation.observermodel;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.SimpleDeclarationVisitor;
import com.thinkinjava.example.annotation.annhandler.Constrains;
import com.thinkinjava.example.annotation.annhandler.DBTable;
import com.thinkinjava.example.annotation.annhandler.SQLInteger;
import com.thinkinjava.example.annotation.annhandler.SQLString;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static com.sun.mirror.util.DeclarationVisitors.NO_OP;
import static com.sun.mirror.util.DeclarationVisitors.getDeclarationScanner;

public class TableCreationProcessorFactory implements AnnotationProcessorFactory {
    @Override
    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }

    @Override
    public Collection<String> supportedAnnotationTypes() {
        return Arrays.asList("com.thinkinjava.example.annotation.annhandler.Contrains",
                            "com.thinkinjava.example.annotation.annhandler.DBTable",
                            "com.thinkinjava.example.annotation.annhandler.SQLInteger",
                            "com.thinkinjava.example.annotation.annhandler.SQLString");
    }

    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> set, AnnotationProcessorEnvironment annotationProcessorEnvironment) {
        return new TableCreationProcessor(annotationProcessorEnvironment);
    }

    private class TableCreationProcessor implements AnnotationProcessor {

        private final AnnotationProcessorEnvironment env;
        private String sql = "";

        public TableCreationProcessor(AnnotationProcessorEnvironment env) {
            this.env = env;
        }


        @Override
        public void process() {
            for(TypeDeclaration typeDeclaration:env.getSpecifiedTypeDeclarations()){
                typeDeclaration.accept(getDeclarationScanner(new TableCreationVisitor(),NO_OP));
                sql = sql.substring(0,sql.length()-1)+");";
                System.out.println("creation SQL is : \n" + sql);
                sql = "";
            }
        }

        private class TableCreationVisitor extends SimpleDeclarationVisitor {
            @Override
            public void visitClassDeclaration(ClassDeclaration classDeclaration) {
                DBTable dbTable = classDeclaration.getAnnotation(DBTable.class);
                if(null != dbTable){
                    sql += "CREATE TABLE ";
                    sql += (dbTable.name().length()<1) ? classDeclaration.getSimpleName().toUpperCase() : dbTable.name();
                    sql += " (";
                }
            }

            @Override
            public void visitFieldDeclaration(FieldDeclaration fieldDeclaration) {
                String columnName = "";
                if(fieldDeclaration.getAnnotation(SQLInteger.class) != null){
                    SQLInteger sqlInteger = fieldDeclaration.getAnnotation(SQLInteger.class);
                    if(sqlInteger.name().length()<1)
                        columnName = fieldDeclaration.getSimpleName().toUpperCase();
                    else columnName = sqlInteger.name();
                    sql += "\n\t" + columnName + " INT " + getConstrains(sqlInteger.constrains()) + ",";
                }
                if(fieldDeclaration.getAnnotation(SQLString.class)!=null){
                    SQLString sqlString = fieldDeclaration.getAnnotation(SQLString.class);
                    if(sqlString.name().length()<1)
                        columnName = fieldDeclaration.getSimpleName().toUpperCase();
                    else columnName = sqlString.name();
                    sql += "\n\t" + columnName + " VARCHAR(" + sqlString.value() + ")" + getConstrains(sqlString.constrains()) + ",";
                }
            }

            private String getConstrains(Constrains con){
                String constrains = "";
                if(!con.allowNull()) constrains += " NOT NULL ";
                else if(con.primaryKey()) constrains += " PRIMARY KEY ";
                else if(con.unique()) constrains += " UNIQUE ";
                return constrains;
            }
        }
    }


}
