package com.thinkinjava.example.annotation.apt;

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import com.sun.mirror.type.*;
import com.sun.mirror.util.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static java.util.Collections.*;

/**
 * this is an example from java guide of apt
 * This class is used to run an annotation processor that lists class
 * names. The functionality of the processor is analogous to the ListClass
 * doclet in the DocLet Overview
 */
public class ListClassApf implements AnnotationProcessorFactory {

    private static final Collection<String> supportedAnnotations
            = unmodifiableCollection(Arrays.asList("*"));

    private static final Collection<String> supportedOptions = emptySet();


    @Override
    public Collection<String> supportedOptions() {
        return supportedOptions;
    }

    @Override
    public Collection<String> supportedAnnotationTypes() {
        return supportedAnnotations;
    }

    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> set, AnnotationProcessorEnvironment annotationProcessorEnvironment) {
        return new ListClassAp(annotationProcessorEnvironment);
    }

    private static class ListClassAp implements AnnotationProcessor{

        private final AnnotationProcessorEnvironment env;

        public ListClassAp(AnnotationProcessorEnvironment env) {
            this.env = env;
        }

        @Override
        public void process() {
            for(TypeDeclaration typeDeclaration : env.getSpecifiedTypeDeclarations())
                typeDeclaration.accept(getDeclarationScanner(new ListClassVistor(),NO_OP));
        }

        private static class ListClassVistor extends SimpleDeclarationVisitor{
            public void visitClassDeclaration(ClassDeclaration d){
                System.out.println(d.getQualifiedName());
            }
        }
    }
}
