package com.thinkinjava.example.enumeration;

public enum Course {
    APPETIZER(Food.Appetizer.class);

    private Food[] values;

    Course(Class<? extends Food> kind) {
        this.values = kind.getEnumConstants();
    }

    public Food randomSelection(){
        return Enums.random(values);
    }
}
