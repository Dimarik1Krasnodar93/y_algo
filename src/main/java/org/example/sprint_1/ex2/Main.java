package org.example.sprint_1.ex2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String fileName = "src\\main\\java\\org\\example\\sprint_1\\ex2\\input.txt";
        List<List<Integer>> lists = parseFile(fileName);
        for (List<Integer> list : lists) {
            System.out.println(calculate(list));
        }
    }

    public static List<List<Integer>> parseFile(String fileName) {
        List<List<Integer>> list = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(Arrays
                        .stream(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static int calculate(List<Integer> args) {
        int a = args.get(0);
        int x = args.get(1);
        int b = args.get(2);
        int c = args.get(3);

        return (int)(a * Math.pow(x, 2)) + b * x + c;
    }
}
