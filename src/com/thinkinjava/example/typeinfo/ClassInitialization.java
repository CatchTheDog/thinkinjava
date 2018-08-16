package com.thinkinjava.example.typeinfo;

import java.util.Random;

/**
 * 类型信息
 */
public class ClassInitialization {
    public static Random rand = new Random(47);
    public static void main(String [] args){
        Class initable = Initable.class;
        System.out.println("After creating Initable ref");

    }
}
