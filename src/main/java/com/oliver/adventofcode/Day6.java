package com.oliver.adventofcode;

import java.util.*;

public class Day6 {
    
    private static List<Integer> INPUT = Arrays.asList(4, 10, 4, 1, 8, 4, 9, 14, 5, 1, 14, 15, 0, 15, 3, 5);

    private List<Integer> memoryBanks;

    public Day6(List<Integer> memoryBanks) {
        this.memoryBanks = memoryBanks;
    }

    public static Day6 fromInput() {
        return new Day6(INPUT);
    }

    public int getTotalRedistributionCycles() {
        return getCycleCountUntilLoop(new HashMap<>());
    }

    public int getLoopRedistributionCycles() {
        Map<List<Integer>, Integer> cycleSeenForState = new HashMap<>();
        int cycles = getCycleCountUntilLoop(cycleSeenForState);

        return cycles - cycleSeenForState.get(memoryBanks);
    }

    private int getCycleCountUntilLoop(Map<List<Integer>, Integer> cycleSeenForState) {
        int cycles = 0;
        redistributionCycle(cycleSeenForState, cycles);
        cycles++;

        while (unseenState(cycleSeenForState)) {
            redistributionCycle(cycleSeenForState, cycles);
            cycles++;
        }
        return cycles;
    }

    private boolean unseenState(Map<List<Integer>, Integer> cycleSeenForState) {
        return cycleSeenForState.get(memoryBanks) == null;
    }

    private void redistributionCycle(Map<List<Integer>, Integer> cycleSeenForState, int curCycle) {
        addState(memoryBanks, curCycle, cycleSeenForState);
        redistribute(getMaxPos());
    }

    private void addState(List<Integer> state, int curCycle, Map<List<Integer>, Integer> cycleSeenForState) {
        cycleSeenForState.put(new ArrayList<>(state), curCycle);
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
