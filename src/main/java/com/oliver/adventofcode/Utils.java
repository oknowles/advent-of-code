package com.oliver.adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static List<String> readFile(int day) {
        File file = getFile("2017/Day" + day + ".txt");
        Scanner scanner = getScanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    private static Scanner getScanner(File file) {
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static File getFile(String fileName) {
        try {
            URI resourceURI = ClassLoader.getSystemClassLoader().getResource(fileName).toURI();
            return new File(resourceURI);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] stringToSingleDigitArray(String input) {
        int[] result = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            result[i] = Character.getNumericValue(input.charAt(i));
        }
        return result;
    }

    public static int[] stringToArray(String input, String separatorRegex) {
        String[] parts = input.split(separatorRegex);
        int[] result = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i]);
        }
        return result;
    }
}
