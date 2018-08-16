package com.thinkinjava.example.enumeration;

public class RandomTest {
    public static void main(String [] args){
        for(int i = 0;i<20;i++){
            System.out.println(Enums.random(Activity.class) + " ");
        }
    }
}
