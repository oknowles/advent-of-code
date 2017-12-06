package com.oliver.adventofcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 {

    private List<Integer> memoryBanks;

    public Day6(List<Integer> memoryBanks) {
        this.memoryBanks = memoryBanks;
    }

    public int getRedistributionCycles() {
        Set<List<Integer>> seenStates = new HashSet<>();
        redistributionCycle(seenStates);

        int cycles = 1;
        while (!seenStates.contains(memoryBanks)) {
            redistributionCycle(seenStates);
            cycles++;
        }
        return cycles;
    }

    private void redistributionCycle(Set<List<Integer>> seenStates) {
        addState(memoryBanks, seenStates);
        redistribute(getMaxPos());
    }

    private void addState(List<Integer> state, Set<List<Integer>> seenStates) {
        seenStates.add(new ArrayList<>(state));
    }

    private int getMaxPos() {
        int maxPos = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < memoryBanks.size(); i++) {
            if (memoryBanks.get(i) > max) {
                max = memoryBanks.get(i);
                maxPos = i;
            }
        }
        return maxPos;
    }

    private void redistribute(int pos) {
        int redistributeAmount = memoryBanks.get(pos);
        memoryBanks.set(pos, 0);

        int curPos = (pos + 1) % memoryBanks.size();
        for (int i = 0; i < redistributeAmount; i++) {
            memoryBanks.set(curPos, memoryBanks.get(curPos) + 1);
            curPos = (curPos + 1) % memoryBanks.size();
        }
    }
}
