package day8

type Solution struct{}

func (s Solution) Part1(input []string) int {
	return countAntinodes(input, markAntinodesForPair)
}

func (s Solution) Part2(input []string) int {
	return countAntinodes(input, markInlineAntinodes)
}

func countAntinodes(grid []string, pairProcessor func([]string, int, int, int, int, [][]int)) int {
	antinodes := make([][]int, len(grid))
	for i, row := range grid {
		antinodes[i] = make([]int, len(row))
	}

	for i, row := range grid {
		for j, entry := range row {
			if entry != '.' {
				processAntenna(grid, i, j, entry, antinodes, pairProcessor)
			}
		}
	}

	res := 0
	for i := range antinodes {
		for j := range antinodes[i] {
			if antinodes[i][j] > 0 {
				res++
			}
		}
	}
	return res
}

func processAntenna(grid []string, x, y int, antenna rune, antinodes [][]int, pairProcessor func([]string, int, int, int, int, [][]int)) {
	for i := x; i < len(grid); i++ {
		for j := 0; j < len(grid[0]); j++ {
			if i == x && j <= y {
				continue
			}
			if rune(grid[i][j]) == antenna {
				pairProcessor(grid, x, y, i, j, antinodes)
			}
		}
	}
}

func markAntinodesForPair(grid []string, x1, y1, x2, y2 int, antinodes [][]int) {
	// get dist between (x1,y1) and (x2,y2)
	// check if antinodes either side are within bounds
	// if so then add antinode (freq) to antinodes

	antinodeX1 := x1 - (x2 - x1)
	antinodeY1 := y1 - (y2 - y1)
	antinodeX2 := x2 + (x2 - x1)
	antinodeY2 := y2 + (y2 - y1)

	if inMap(antinodeX1, antinodeY1, grid) {
		antinodes[antinodeX1][antinodeY1]++
	}
	if inMap(antinodeX2, antinodeY2, grid) {
		antinodes[antinodeX2][antinodeY2]++
	}
}

func markInlineAntinodes(grid []string, x1, y1, x2, y2 int, antinodes [][]int) {
	// this can increase the antinode count multiple times incorrectly but we only care about whether there is one
	// at a particular place so that is fine

	antinodes[x1][y1]++
	antinodes[x2][y2]++

	xDiff := x2 - x1
	yDiff := y2 - y1

	if x1 == x2 { // both same row
		for y := 0; y < len(grid[0]); y++ {
			antinodes[x1][y]++
		}
	} else if y1 == y2 { // both same column
		for x := 0; x < len(grid[0]); x++ {
			antinodes[x][y1]++
		}
	} else if xDiff == yDiff { // \ diagonal
		offMap := false
		iteration := 1
		for !offMap {
			antinodeX1 := x1 - iteration
			antinodeY1 := y1 - iteration
			antinodeX2 := x1 + iteration
			antinodeY2 := y1 + iteration
			if inMap(antinodeX1, antinodeY1, grid) {
				antinodes[antinodeX1][antinodeY1]++
			}
			if inMap(antinodeX2, antinodeY2, grid) {
				antinodes[antinodeX2][antinodeY2]++
			}
			if !inMap(antinodeX1, antinodeY1, grid) && !inMap(antinodeX2, antinodeY2, grid) {
				offMap = true
			}
			iteration++
		}
	} else if xDiff == (yDiff * -1) { // / diagonal
		offMap := false
		iteration := 1
		for !offMap {
			antinodeX1 := x1 - iteration
			antinodeY1 := y1 + iteration
			antinodeX2 := x1 + iteration
			antinodeY2 := y1 - iteration
			if inMap(antinodeX1, antinodeY1, grid) {
				antinodes[antinodeX1][antinodeY1]++
			}
			if inMap(antinodeX2, antinodeY2, grid) {
				antinodes[antinodeX2][antinodeY2]++
			}
			if !inMap(antinodeX1, antinodeY1, grid) && !inMap(antinodeX2, antinodeY2, grid) {
				offMap = true
			}
			iteration++
		}
	} else {
		offMap := false
		iteration := 1
		for !offMap {
			antinodeX1 := x1 - iteration*xDiff
			antinodeY1 := y1 - iteration*yDiff
			antinodeX2 := x2 + iteration*xDiff
			antinodeY2 := y2 + iteration*yDiff
			if inMap(antinodeX1, antinodeY1, grid) {
				antinodes[antinodeX1][antinodeY1]++
			}
			if inMap(antinodeX2, antinodeY2, grid) {
				antinodes[antinodeX2][antinodeY2]++
			}
			if !inMap(antinodeX1, antinodeY1, grid) && !inMap(antinodeX2, antinodeY2, grid) {
				offMap = true
			}
			iteration++
		}
	}
}

func inMap(x, y int, grid []string) bool {
	return x >= 0 && x < len(grid) && y >= 0 && y < len(grid[0])
}
