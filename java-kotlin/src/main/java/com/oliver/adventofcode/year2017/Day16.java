package com.oliver.adventofcode.year2017;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16 {

    private Map<Character, Integer> programPosition;
    private char[] initialOrdering;
    private int cycleLength;

    public Day16(int numPrograms) {
        initialOrdering = new char[numPrograms];
        programPosition = new HashMap<>();
        for (int i = 0; i < numPrograms; i++) {
            char curChar = (char) ('a' + i);
            programPosition.put(curChar, i);
            initialOrdering[i] = curChar;
        }
    }

    public void executeInstructions(List<String> instructions, int numLoops) {
        for (int loop = 0; loop < numLoops; loop++) {
            executeInstructions(instructions);
            if (loop > 0 && originalOrdering()) {
                cycleLength = loop + 1;
                break;
            }
        }

        if (cycleLength > 0) {
            for (int i = 0; i < numLoops % cycleLength; i++) {
                executeInstructions(instructions);
            }
        }
    }

    private boolean originalOrdering() {
        for (Map.Entry<Character, Integer> entry : programPosition.entrySet()) {
            Integer pos = entry.getValue();
            if (initialOrdering[pos] != entry.getKey()) {
                return false;
            }
        }
        return true;
    }

    private void executeInstructions(List<String> input) {
        for (String instruction : input) {
            if (instruction.startsWith("s")) {
                int spinSize = Integer.parseInt(instruction.substring(1));
                rotateToFront(spinSize);
            } else if (instruction.startsWith("x")) {
                String[] parts = instruction.substring(1).split("/");
                int position1 = Integer.parseInt(parts[0]);
                int position2 = Integer.parseInt(parts[1]);
                swapPrograms(position1, position2);
            } else if (instruction.startsWith("p")) {
                char program1 = instruction.charAt(1);
                char program2 = instruction.charAt(3);
                swapPrograms(program1, program2);
            }
        }
    }

    private void rotateToFront(int spinSize) {
        int offset = programPosition.size() - spinSize;
        for (Map.Entry<Character, Integer> entry : programPosition.entrySet()) {
            Integer curPos = entry.getValue();
            Integer newPos = (curPos < offset) ? curPos + spinSize : curPos - offset;
            programPosition.put(entry.getKey(), newPos);
        }
    }

    private void swapPrograms(int position1, int position2) {
        for (Map.Entry<Character, Integer> entry : programPosition.entrySet()) {
            if (entry.getValue() == position1) {
                programPosition.put(entry.getKey(), position2);
            } else if (entry.getValue() == position2) {
                programPosition.put(entry.getKey(), position1);
            }
        }
    }

    private void swapPrograms(char program1, char program2) {
        int position1 = programPosition.get(program1);
        int position2 = programPosition.get(program2);
        programPosition.put(program1, position2);
        programPosition.put(program2, position1);
    }

    public String getProgramOrderString() {
        char[] chars = new char[programPosition.size()];
        for (Map.Entry<Character, Integer> entry : programPosition.entrySet()) {
            chars[entry.getValue()] = entry.getKey();
        }
        return new String(chars);
    }
}
