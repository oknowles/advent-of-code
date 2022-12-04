package com.oliver.adventofcode.year2017;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 {

    private Map<String, Integer> registers;

    public Day8() {
        registers = new HashMap<>();
    }

    public int loadInstructions(List<String> instructions) {
        int largestVal = Integer.MIN_VALUE;

        for (String instruction : instructions) {
            String[] parts = instruction.split(" ");
            String register = parts[0];
            boolean inc = parts[1].equals("inc");
            int amount = Integer.parseInt(parts[2]);

            String conditionalRegister = parts[4];
            String operator = parts[5];
            int conditionalAmount = Integer.parseInt(parts[6]);

            if (conditionPasses(conditionalRegister, operator, conditionalAmount)) {
                int registerVal = registers.getOrDefault(register, 0);
                registerVal += inc ? amount : -amount;
                registers.put(register, registerVal);
                largestVal = (registerVal > largestVal) ? registerVal : largestVal;
            }
        }
        return largestVal;
    }

    private boolean conditionPasses(String register, String operator, int amount) {
        int curRegisterVal = registers.getOrDefault(register, 0);
        switch (operator) {
            case ">":
                return curRegisterVal > amount;
            case ">=":
                return curRegisterVal >= amount;
            case "<":
                return curRegisterVal < amount;
            case "<=":
                return curRegisterVal <= amount;
            case "==":
                return curRegisterVal == amount;
            case "!=":
                return curRegisterVal != amount;
            default:
                throw new RuntimeException();
        }
    }

    public int getLargestRegisterValue() {
        int largestVal = Integer.MIN_VALUE;
        for (Integer value : registers.values()) {
            largestVal = (value > largestVal) ? value : largestVal;
        }
        return largestVal;
    }
}
