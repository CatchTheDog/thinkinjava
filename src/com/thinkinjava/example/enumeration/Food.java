package com.thinkinjava.example.enumeration;

public interface Food {
    enum Appetizer implements Food{
        SALAD,SOUP,SPRING_ROLLS;
    }
    enum MainCourse implements Food{
        LASAGNE,BURRITO,PAD_THAI,LENTILS,HUMMOOUS,VINDALOO;
    }
}
