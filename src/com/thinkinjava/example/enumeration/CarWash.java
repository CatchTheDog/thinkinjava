package com.thinkinjava.example.enumeration;

public class CarWash {
    public enum Cycle{
        UNDERBODY{
            @Override
            void action() {
                System.out.println("Spraying the underbody");
            }

            @Override
            void f() {
                System.out.println("override behavior");
            }
        };

        abstract void action();
        void f(){ System.out.println("default behavior");}
    }
}
