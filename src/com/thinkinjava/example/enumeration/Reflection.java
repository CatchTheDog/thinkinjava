package com.thinkinjava.example.enumeration;







import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

public class Reflection {
    public static Set<String> analyze(Class<?> enumClass){
        System.out.println("----- Analyzing " + enumClass + " -----");
        System.out.println("Interfaces:");
        for(Type t : enumClass.getGenericInterfaces())
            System.out.println(t);
        System.out.println("Base: "+enumClass.getSuperclass());
        System.out.println("Methods: ");
        Set<String> methods = new TreeSet<>();
        for(Method m : enumClass.getMethods())
            methods.add(m.getName());
        System.out.println(methods);
        return methods;
    }

    public static void main(String [] args) throws IOException {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println("Explore.containsAll(Enum)? " + exploreMethods.containsAll(enumMethods));
        System.out.println("Explore.removeAll(Enum):"+exploreMethods.removeAll(enumMethods));
        Runtime rt = Runtime.getRuntime();
        Process process = ((Runtime) rt).exec("javap ./Explore");
    }
}
