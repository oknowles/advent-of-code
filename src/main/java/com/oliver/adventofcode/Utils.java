package com.oliver.adventofcode;

public class Utils {

    public static int[] stringToSingleDigitArray(String input) {
        int[] result = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            result[i] = Character.getNumericValue(input.charAt(i));
        }
        return result;
    }

    public static int[] stringToArray(String input, String seperatorRegex) {
        String[] parts = input.split(seperatorRegex);
        int[] result = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i]);
        }
        return result;
    }
}
