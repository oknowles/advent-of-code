package com.oliver.adventofcode;

import java.util.*;
import java.util.stream.Collectors;

public class Day12 {

    private Map<Integer, Set<Integer>> communicationLines;

    public Day12(List<String> input) {
        communicationLines = new HashMap<>();

        for (String s : input) {
            String[] parts = s.split(" <-> ");
            Integer programId = Integer.parseInt(parts[0].trim());
            Set<Integer> connectedNumbers = Arrays.stream(parts[1].split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());

            communicationLines.put(programId, connectedNumbers);
        }
    }

    public int getGroupSize(int programId) {
        return getGroupSize(programId, new HashSet<>());
    }

    public int getNumberOfGroups() {
        int numGroups = 0;
        Set<Integer> visited = new HashSet<>();
        for (Integer programId : communicationLines.keySet()) {
            if (!visited.contains(programId)) {
                getGroupSize(programId, visited);
                numGroups++;
            }
        }
        return numGroups;
    }

    private int getGroupSize(int programId, Set<Integer> visited) {
        if (visited.contains(programId)) {
            return 0;
        }
        visited.add(programId);
        int connections = 1;
        for (Integer connectedProgramId : communicationLines.get(programId)) {
            connections += getGroupSize(connectedProgramId, visited);
        }
        return connections;
    }
}
