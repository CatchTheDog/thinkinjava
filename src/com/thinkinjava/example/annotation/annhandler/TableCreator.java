package com.thinkinjava.example.annotation.annhandler;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {
    public static void main(String [] args) throws ClassNotFoundException {
        if(args.length < 1){
            System.out.println("arguments : annotated classes");
            System.exit(0);
        }
        for(String className : args){
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(null == dbTable){
                System.out.println("No DBTable annotations in class "+className);
                continue;
            }
            String tableName = dbTable.name();
            if(tableName.length()<1) tableName = cl.getName().toUpperCase();
            List<String> columnDefs = new ArrayList<>();
            for(Field field : cl.getDeclaredFields()){
                String columnName = null;
                Annotation [] anns = field.getDeclaredAnnotations();
                if(anns.length<1) continue;
                if(anns[0] instanceof SQLInteger){
                    SQLInteger sqlInteger = (SQLInteger) anns[0];
                    if(sqlInteger.name().length()<1) columnName = field.getName().toUpperCase();
                    else columnName = sqlInteger.name();
                    columnDefs.add(columnName+" INT "+getConstrains(sqlInteger.constrains()));

                }else if(anns[0] instanceof SQLString){
                    SQLString sqlString = (SQLString) anns[0];
                    if(sqlString.name().length()<1) columnName = field.getName().toUpperCase();
                    else columnName = sqlString.name();
                    columnDefs.add(columnName+" VARCHAR("+sqlString.value()+") "+getConstrains(sqlString.constrains()));
                }
            }
            StringBuilder createdCommand = new StringBuilder("CREATE TABLE ")
                    .append(tableName).append("(");
            for(String columnDef : columnDefs)
                createdCommand.append("\n\t").append(columnDef).append(",");
            System.out.println("Table creation SQL for "+ className + " is:\n"+createdCommand.substring(0,createdCommand.length()-1)+")");
        }
    }

    private static String getConstrains(Constrains con){
        String constrains = "";
        if(!con.allowNull()) constrains += " NOT NULL ";
        else if(con.primaryKey()) constrains += " PRIMARY KEY ";
        else if(con.unique()) constrains += " UNIQUE ";
        return constrains;
    }
}