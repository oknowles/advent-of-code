package com.oliver.adventofcode.year2017;

import java.util.function.Function;

public class Day15 {

    private static final long DIVISOR = 2147483647;
    private static final long FACTOR_A = 16807;
    private static final long FACTOR_B = 48271;
    private static final int DIVISOR_CRITERIA_A = 4;
    private static final int DIVISOR_CRITERIA_B = 8;

    private static final int SIXTEEN_BIT_CEILING = 65_536;

    private long startA;
    private long startB;

    public Day15(long startA, long startB) {
        this.startA = startA;
        this.startB = startB;
    }

    public int matchingPairs(int pairCount) {
        return getMatchingCount(pairCount, this::getNextA, this::getNextB);
    }

    public int matchingPairsWithCriteria(int pairCount) {
        return getMatchingCount(pairCount, this::getNextAWithCriteria, this::getNextBWithCriteria);
    }

    private int getMatchingCount(int pairCount, Function<Long, Long> generatorA, Function<Long, Long> generatorB) {
        long prevA = startA;
        long prevB = startB;
        int matchingCount = 0;
        for (int pair = 0; pair < pairCount; pair++) {
            long curA = generatorA.apply(prevA);
            long curB = generatorB.apply(prevB);

            if (last16BitsMatch(curA, curB)) {
                matchingCount++;
            }

            prevA = curA;
            prevB = curB;
        }
        return matchingCount;
    }

    private long getNextA(long prev) {
        return getNextNumber(prev, FACTOR_A);
    }

    private long getNextB(long prev) {
        return getNextNumber(prev, FACTOR_B);
    }

    private long getNextAWithCriteria(long prev) {
        return getNextNumber(prev, FACTOR_A, DIVISOR_CRITERIA_A);
    }

    private long getNextBWithCriteria(long prev) {
        return getNextNumber(prev, FACTOR_B, DIVISOR_CRITERIA_B);
    }

    private long getNextNumber(long prev, long factor) {
        return (prev * factor) % DIVISOR;
    }

    private long getNextNumber(long prev, long factor, long divisorCriteria) {
        long cur = getNextNumber(prev, factor);
        while (cur % divisorCriteria != 0) {
            cur = getNextNumber(cur, factor);
        }
        return cur;
    }

    private boolean last16BitsMatch(long curA, long curB) {
        return (curA % SIXTEEN_BIT_CEILING) == (curB % SIXTEEN_BIT_CEILING);
    }
}
