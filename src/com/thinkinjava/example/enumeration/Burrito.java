package com.thinkinjava.example.enumeration;

import static com.thinkinjava.example.enumeration.Spiciness.*;
public class Burrito {
    Spiciness degree;

    public Burrito(Spiciness degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Burrito{");
        sb.append("degree=").append(degree);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String [] args){
        System.out.println(new Burrito(NOT));
        System.out.println(new Burrito(MEDIUM));
        System.out.println(new Burrito(HOT));
    }
}
