package com.houstondirectauto.refurb;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

interface StringFunction {
    String run(String str);
}

public class lambda {

    public  static  void main(String [] k){


        StringFunction exclaim = (s)->s+"!";
        StringFunction ask = (s)->s+"?";
        PrintFormatted("hello",exclaim);
        PrintFormatted("hello",ask);


        List<String> names = Arrays.asList("Manish","Rahul","Msi") ;

        names.stream().filter(name->name.startsWith("M")).forEach(System.out::println);



    }

    public static void PrintFormatted(String str,StringFunction format){
        String result = format.run(str);
        System.out.println(result);
    }
}
