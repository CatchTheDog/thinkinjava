package com.thinkinjava.example.annotation.bases;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 注解处理器
 */
public class UserCaseTracker {
    public static void trackUserCases(List<Integer> userCases,Class<?>cl){
        for(Method method : cl.getDeclaredMethods()){
            UserCase userCase = method.getAnnotation(UserCase.class);
            if(null != userCase){
                System.out.println("Found User Case:"+userCase.id()+" "+userCase.description());
                userCases.remove(new Integer(userCase.id()));
            }
        }
        for(int i : userCases){
            System.out.println("Warning:Missing user case -"+i);
        }
    }

    public static void main(String [] args){
        List<Integer> userCases = new ArrayList<>();
        Collections.addAll(userCases,47,48,49,50);
        trackUserCases(userCases,PasswordUtils.class);
    }
}
