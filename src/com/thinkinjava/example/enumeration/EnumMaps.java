package com.thinkinjava.example.enumeration;

import java.util.EnumMap;
import java.util.Map;

import static com.thinkinjava.example.enumeration.AlarmPoints.*;

public class EnumMaps {
    public static void main(String [] args){
        EnumMap<AlarmPoints,Command> em = new EnumMap<>(AlarmPoints.class);
        em.put(KITCHEN, new Command() {
            @Override
            public void action() {
               System.out.println("Kitchen fire!");
            }
        });
        em.put(BATHROOM, new Command() {
            @Override
            public void action() {
                System.out.println("bathroom alert!");
            }
        });

        for(Map.Entry<AlarmPoints,Command> e : em.entrySet()){
            System.out.println(e.getKey() + ": ");
            e.getValue().action();
        }
        em.get(UTILITY).action();
    }
}
