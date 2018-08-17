package com.thinkinjava.example.regexp;

import java.io.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * PatternSyntaxException 非强制性异常
 */
public class RegexpMatches_4 {
    public static void main(String [] args){
        Pattern pattern = null;
        Matcher matcher = null;

        Console console = System.console();
        if(null == console){
            System.err.println("No console");
            System.exit(1);
        }

        while(true){
            try{
                pattern = Pattern.compile(console.readLine("%nEnter your regex:"));
                matcher = pattern.matcher(console.readLine("Enter input String to search:"));
            }catch (PatternSyntaxException e){
                console.format("There is a problem withe thre regular express!%n");
                console.format("The pattern in question is :%s%n",e.getPattern());
                console.format("The description is :%s%n",e.getDescription());
                console.format("The index is :%s%n",e.getIndex());
                System.exit(0);
            }
            boolean found = false;
            while(matcher.find()){
                console.format("I found the text \"%s\" starting at index %d and ending at index %d.%n",matcher.group(),matcher.start(),matcher.end());
                found = true;
            }

            if(!found){
                console.format("No match found %n");
            }

        }
    }
}
