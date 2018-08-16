package com.thinkinjava.example.enumeration;

public enum OzWitch {

    WEST("xi"),
    NORTH("bei"),
    EAST("dong"),
    SOUTH("nan");

    private String description;

    OzWitch(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String [] args){
        for(OzWitch witch : OzWitch.values()){
            System.out.println(witch + ":" + witch.getDescription());
        }
    }


}
