package com.company.fivthfilesstreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStream {
    public static void main(String[] args) {
        // Length Filter
        try {
            Stream<String> brands = Files.lines(Paths.get("brands.txt"));
            brands.sorted()     // Sort the brands
                    .filter(x -> x.length() > 13)   // Filter those brands whose length is greater than 13
                    .forEach(System.out::println);  // Print only those brands
            brands.close();     // Close the stream
        } catch (IOException io) {
            io.printStackTrace();
        }
        System.out.println("\n");

        // Collect only those line which contains "jit"
        try {
            List<String> brands = Files.lines(Paths.get("brands.txt"))  // Get all brands from the file
                    .filter(x -> x.contains("jit"))     // Filter those lines which contains the String "jit"
                    .collect(Collectors.toList());      // Collect them and store into a list
            brands.forEach(System.out::println);        // Print the entire list (which contains only those string which contains the jit)
        } catch (IOException io) {
            io.printStackTrace();
        }
        System.out.println("\n");

        // Import data from csv file and count the lines which contains at least two fields
        try {
            Stream<String> rows = Files.lines(Paths.get("data.txt"));
            int rowCount = (int) rows.map(x -> x.split(","))
                    .filter(x -> x.length == 3)
                    .count();
            System.out.println("Row Count : " + rowCount);
            rows.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
        System.out.println("\n");

        // Import data from csv file and parse the data from the rows and print those rows only whose second column is greater than 15
        try {
            Stream<String> rows = Files.lines(Paths.get("data.txt"));
            rows.map(x -> x.split(","))
                    .filter(x -> x.length == 3)
                    .filter(x -> Integer.parseInt(x[1]) > 15)
                    .forEach(x -> System.out.println(x[0] + "  " + x[1] + "  " + x[2]));
            rows.close();
        } catch (IOException io) {
            io.printStackTrace();
        }

        // Extract the rows from csv file and store them into HashMap if second column in greater than 15
        try {
            Map<String, Integer> map = new HashMap<String, Integer>();
            Stream<String> rows = Files.lines(Paths.get("data.txt"));
            map = rows.map(x -> x.split(","))
                    .filter(x -> x.length == 3)
                    .filter(x -> Integer.parseInt(x[1]) > 15)
                    .collect(Collectors.toMap(x -> x[0], x -> Integer.parseInt(x[1])));
            rows.close();
            System.out.println("Map => ");
            for (String key : map.keySet()) {
                System.out.println(key + "->" + map.get(key));
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        System.out.println("\n");
    }
}
