package com.thinkinjava.example.annotation.apt;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class InterfaceExtractProcessorFactory implements AnnotationProcessorFactory {
    /**
     * 提供支持的选项
     * @return
     */
    @Override
    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }

    /**
     * 提供需要处理的注解
     * @return
     */
    @Override
    public Collection<String> supportedAnnotationTypes() {
        return Collections.singleton("com.thinkinjava.example.annotation.apt.ExtractInterface");
    }

    /**
     * 返回注解处理器
     * @param set
     * @param annotationProcessorEnvironment
     * @return
     */
    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> set, AnnotationProcessorEnvironment annotationProcessorEnvironment) {
        return new InterfaceExtractorProcessor(annotationProcessorEnvironment);
    }

    private class InterfaceExtractorProcessor implements AnnotationProcessor{

        private final AnnotationProcessorEnvironment env;
        private ArrayList<MethodDeclaration> interfaceMethods = new ArrayList<>();

        public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env) {
            this.env = env;
        }

        @Override
        public void process() {
           for(TypeDeclaration typeDeclaration:env.getSpecifiedTypeDeclarations()){
               ExtractInterface annot = typeDeclaration.getAnnotation(ExtractInterface.class);
               if(null == annot) break;
               for(MethodDeclaration m : typeDeclaration.getMethods()){
                  if(m.getModifiers().contains(Modifier.PUBLIC) && !(m.getModifiers().contains(Modifier.STATIC)))
                      interfaceMethods.add(m);
               }
               if(interfaceMethods.size() > 0){
                   try{
                       PrintWriter writer = env.getFiler().createSourceFile(annot.value());
                       writer.println("package "+typeDeclaration.getPackage().getQualifiedName()+";");
                       writer.println("public interface "+annot.value()+" {");
                       for(MethodDeclaration m : interfaceMethods){
                           writer.print(" public ");
                           writer.print(m.getReturnType() + " ");
                           writer.print(m.getSimpleName() + " (");
                           int i = 0;
                           for(ParameterDeclaration parameterDeclaration : m.getParameters()){
                               writer.print(parameterDeclaration.getType()+" "+parameterDeclaration.getSimpleName());
                               if(++i < m.getParameters().size()){
                                   writer.print(", ");
                               }
                           }
                           writer.println(");");
                       }
                       writer.println("}");
                       writer.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
        }
    }
}
