package com.houstondirectauto.refurb;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class manish {
    public  static void main(String[] a){

        String [] fruits = {"apple","banna","orange","mango"};
        //streams example
        Arrays.stream(fruits).map(String::toUpperCase).forEach(System.out::println);
        System.out.println("yopo manish");

        ArrayList<Integer> numbers = new ArrayList<Integer>();

        numbers.add(5);
        numbers.add(8);
        numbers.add(6);
        numbers.add(2);

        //consumer method allows to store the lamda expression and use it later on
        Consumer<Integer> method = (n)->{
            System.out.println(n);
        };
        numbers.forEach(method);


        List<String> names = Arrays.asList("Alice","Bob","Charlie");

        names.stream().filter(name->name.startsWith("A")).forEach(System.out::println);

        String text = """ 
                This is mainsh
                    singh so you
                don't worry
                """;
        System.out.println(text);


    }

}
