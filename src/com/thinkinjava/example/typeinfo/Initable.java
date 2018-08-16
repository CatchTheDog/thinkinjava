package com.thinkinjava.example.typeinfo;

public class Initable {
    private  static final int staticFinal = 47;
    private static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);

    static{
        System.out.println("Initializing Initable");
    }
}
