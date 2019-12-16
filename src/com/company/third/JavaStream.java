package com.company.third;

import java.util.Arrays;
import java.util.List;

public class JavaStream {
    public static void main(String[] args) {
        // Find the Avg of the list
        Arrays.stream(new int[] {2,4,6,8,10})   // Creating new Array of Integers
                .map(x -> x * x)                // Performing x^2 on each number of the array
                .average()                      // Calculating the avg of the final array
                .ifPresent(System.out::println);// Print the avg
        System.out.println("\n");
        // Convert all the strings into lowercase and find string starts with 'a' and print them
        List<String> people = Arrays.asList("Al", "Ankit", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah");
        people.stream()
                .map(String::toLowerCase)
                .filter(str -> str.startsWith("a"))
                .forEach(System.out::println);
    }
}
