package com.oliver.adventofcode.year2017;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23 {

    private Map<Character, Long> registers;

    public Day23() {
        registers = new HashMap<>();
    }

    public int getMultInvocations(List<String> instructions) {
        int count = 0;
        int instructionPos = 0;
        while (instructionPos < instructions.size()) {
            String instruction = instructions.get(instructionPos);
            String[] parts = instruction.split(" ");
            char charToSet;
            long prevVal;
            switch (parts[0]) {
                case "set":
                    charToSet = parts[1].charAt(0);
                    registers.put(charToSet, getValue(parts[2]));
                    break;
                case "sub":
                    charToSet = parts[1].charAt(0);
                    prevVal = getValue(parts[1]);
                    registers.put(charToSet, prevVal - getValue(parts[2]));
                    break;
                case "mul":
                    count++;
                    charToSet = parts[1].charAt(0);
                    prevVal = getValue(parts[1]);
                    registers.put(charToSet, prevVal * getValue(parts[2]));
                    break;
                case "jnz":
                    long val = getValue(parts[1]);
                    if (val != 0) {
                        instructionPos += getValue(parts[2]);
                        continue;
                    }
            }
            instructionPos++;
        }
        return count;
    }

    private long getValue(String variable) {
        char firstChar = variable.charAt(0);
        if (Character.isAlphabetic(firstChar)) {
            return registers.getOrDefault(firstChar, 0L);
        }
        return Long.parseLong(variable);
    }

    public int getNonPrimeCount(int lowerBound, int upperBound) {
        int h = 0;
        for (int b = lowerBound; b <= upperBound; b += 17) {
            if (!isPrime(b)) {
                h++;
            }
        }
        return h;
    }

    private boolean isPrime(int val) {
        int sqrtVal = (int) Math.sqrt(val);
        for (int i = 2; i <= sqrtVal; i++) {
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }
}
