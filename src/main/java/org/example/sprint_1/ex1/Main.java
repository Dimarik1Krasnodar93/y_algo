package org.example.sprint_1.ex1;

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
        List<List<Integer>> lists = getHouses();
        for (List<Integer> list : lists) {
            int[] distances = getDistances(list);
            Arrays.stream(distances).forEach(i -> System.out.print(i + " "));
            System.out.println();
        }
    }

    private static List<List<Integer>> getHouses() {
        List<List<Integer>> result = new ArrayList<>();
        String fileName = "src\\main\\java\\org\\example\\sprint_1\\ex1\\input.txt";
        String line;
        try(BufferedReader bfr = new BufferedReader(new FileReader(fileName))) {
            while ((line = bfr.readLine()) != null) {
                List<Integer> v = Arrays.stream(line.split(" "))
                        .map(Integer::parseInt).collect(Collectors.toList());
                result.add(v);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    private static int [] getDistances(List<Integer> houses) {
        long startTime = System.currentTimeMillis();
        int size = houses.size();
        int [] distances = new int[houses.size()];
        if (houses.size() == 0) {
            return distances;
        }
        for(int i = 0; i < size - 1; i++) {
            distances[i] = Integer.MAX_VALUE;
            if (houses.get(i) == 0) {
                distances[i] = 0;
                continue;
            }
            for (int j = i + 1; j < size; j++) {
                if (houses.get(j) == 0) {
                    distances[i] = j - i;
                }
            }
            for (int j = i - 1; j >= 0; j--) {
                if (houses.get(j) == 0) {
                    if (i - j < distances[i]) {
                        distances[i] = i - j;
                    }
                }
            }
        }
        distances[size - 1] = houses.get(size - 1);
        if (distances[size - 1] != 0 && size > 1) {
            distances[size - 1] = distances[size - 2] + 1;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime));
        return distances;
    }
}
