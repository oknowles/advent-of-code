package com.oliver.adventofcode;

public class Utils {

    public static int[] stringToIntArray(String input) {
        int[] result = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            result[i] = Character.getNumericValue(input.charAt(i));
        }
        return result;
    }
}
