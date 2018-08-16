package com.thinkinjava.example.typeinfo;

public class ToyTest {
    public static void printInfo(Class cc){
        System.out.println("Class name: "+cc.getName()+" is interface? ["+cc.isInterface());
        System.out.println("Simple name : "+ cc.getSimpleName());
        System.out.println("Canonical name: " + cc.getCanonicalName());
    }

    public static void main(String [] args){
        try {
            Class c = Class.forName("com.thinkinjava.example.typeinfo.FancyToy");
            printInfo(c);
            for(Class face : c.getInterfaces())
                printInfo(face);
            Class up = c.getSuperclass();
            Object obj = up.newInstance();
            printInfo(obj.getClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
