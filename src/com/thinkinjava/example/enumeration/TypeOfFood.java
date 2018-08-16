package com.thinkinjava.example.enumeration;

import static com.thinkinjava.example.enumeration.Food.*;

public class TypeOfFood {
    public static void main(String [] args){
        Food food = Appetizer.SALAD;
        food = MainCourse.LASAGNE;
    }
}
