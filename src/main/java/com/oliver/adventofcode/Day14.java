package com.oliver.adventofcode;

public class Day14 {

    private boolean[][] grid;

    public Day14(String input) {
        grid = new boolean[128][128];
        for (int row = 0; row < 128; row++) {
            int col = 0;
            String rowInput = input + "-" + row;
            String knotHash = Day10.fromString(rowInput).getKnotHash();
            for (Character c : knotHash.toCharArray()) {
                String string = hexToBinary(String.valueOf(c));
                for (int j = 0; j < 4; j++) {
                    char square = string.charAt(j);
                    if (square == '1') {
                        grid[row][col] = true;
                    }
                    col++;
                }
            }
        }
    }

    public int getUsedSquares() {
        int count = 0;
        for (int row = 0; row < 128; row++) {
            for (int col = 0; col < 128; col++) {
                if (grid[row][col]) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getRegionCount() {
        int regionCount = 0;
        boolean[][] visited = new boolean[128][128];
        for (int row = 0; row < 128; row++) {
            for (int col = 0; col < 128; col++) {
                if (grid[row][col] && !visited[row][col]) {
                    visitRegion(visited, row, col);
                    regionCount++;
                }
            }
        }
        return regionCount;
    }

    private void visitRegion(boolean[][] visited, int row, int col) {
        if (!validPos(row, col) || !grid[row][col] || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        visitRegion(visited, row - 1, col);
        visitRegion(visited, row + 1, col);
        visitRegion(visited, row, col - 1);
        visitRegion(visited, row, col + 1);
    }

    private boolean validPos(int row, int col) {
        return row >= 0 && row < 128 && col >= 0 && col < 128;
    }

    private String hexToBinary(String hexString) {
        int i = Integer.parseInt(hexString, 16);
        StringBuilder binaryString = new StringBuilder(Integer.toBinaryString(i));
        while (binaryString.length() < 4) {
            binaryString.insert(0, "0");
        }
        return binaryString.toString();
    }
}
