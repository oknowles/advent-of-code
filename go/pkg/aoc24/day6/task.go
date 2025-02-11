package day6

import (
	"errors"
)

type Solution struct{}

const (
	up = iota
	right
	down
	left
)

var guardChars = map[rune]int{
	'^': up,
	'>': right,
	'v': down,
	'<': left,
}

func (s Solution) Part1(input []string) int {
	grid, i, j, char := parseInput(input)
	history := make([][]map[rune]bool, len(grid))
	for i := range grid {
		history[i] = make([]map[rune]bool, len(grid[i]))
		for j := range grid[i] {
			history[i][j] = make(map[rune]bool)
		}
	}

	finished := false
	for !finished {
		history[i][j][char] = true
		i, j, char = next(grid, i, j, char)
		finished = offGrid(grid, i, j)
	}

	visitedCount := 0
	for i := range history {
		for j := range history[i] {
			if len(history[i][j]) > 0 {
				visitedCount++
			}
		}
	}
	return visitedCount
}

func (s Solution) Part2(input []string) int {
	grid, i, j, char := parseInput(input)
	history := make([][]map[rune]bool, len(grid))
	for x := range grid {
		history[x] = make([]map[rune]bool, len(grid[x]))
		for y := range grid[x] {
			history[x][y] = make(map[rune]bool)
		}
	}

	loopBlocks := make([][]bool, len(grid))
	for x := range grid {
		loopBlocks[x] = make([]bool, len(grid[x]))
	}
	for !offGrid(grid, i, j) {
		checkLoops(grid, i, j, char, history, loopBlocks)
		history[i][j][char] = true
		i, j, char = next(grid, i, j, char)
	}

	res := 0
	for x := range loopBlocks {
		for y := range loopBlocks[x] {
			if loopBlocks[x][y] {
				res++
			}
		}
	}
	return res
}

func checkLoops(grid [][]rune, i, j int, char rune, history [][]map[rune]bool, loopBlocks [][]bool) {
	if blockOrBoundaryAhead(grid, i, j, char) {
		return
	}

	newBlockI, newBlockJ, _ := next(grid, i, j, char)
	if len(history[newBlockI][newBlockJ]) > 0 {
		// can't put a block where the guard has previously been as we wouldn't be able to reach the current position
		return
	}

	// Count how many times each position is visited. We know if we have visited 4 times there is guaranteed to be
	// a loop as there are only 4 directions
	timesVisited := make([][]int, len(grid))
	for x := range grid {
		timesVisited[x] = make([]int, len(grid[x]))
	}

	grid[newBlockI][newBlockJ] = '#'
	for !offGrid(grid, i, j) {
		if timesVisited[i][j] == 4 {
			loopBlocks[newBlockI][newBlockJ] = true
			break
		}
		timesVisited[i][j]++
		i, j, char = next(grid, i, j, char)
	}
	grid[newBlockI][newBlockJ] = '.'
}

func blockOrBoundaryAhead(grid [][]rune, i, j int, char rune) bool {
	nextI, nextJ, nextChar := next(grid, i, j, char)
	blockAhead := nextChar != char
	return blockAhead || offGrid(grid, nextI, nextJ)
}

func next(grid [][]rune, i, j int, char rune) (int, int, rune) {
	direction := guardChars[char]
	nextI := i
	nextJ := j
	switch direction {
	case up:
		nextI--
	case right:
		nextJ++
	case down:
		nextI++
	case left:
		nextJ--
	}

	if offGrid(grid, nextI, nextJ) {
		return nextI, nextJ, char
	}
	if grid[nextI][nextJ] == '#' {
		return i, j, rotate(char)
	}
	return nextI, nextJ, char
}

func offGrid(grid [][]rune, i, j int) bool {
	return i < 0 || i >= len(grid) || j < 0 || j >= len(grid[0])
}

func rotate(char rune) rune {
	switch guardChars[char] {
	case up:
		return '>'
	case right:
		return 'v'
	case down:
		return '<'
	case left:
		return '^'
	}
	panic(errors.New("could not find char"))
}

func parseInput(input []string) ([][]rune, int, int, rune) {
	res := make([][]rune, len(input))
	var guardi, guardj int
	var guardChar rune
	for i, line := range input {
		res[i] = make([]rune, len(line))
		for j, char := range line {
			res[i][j] = char
			if _, ok := guardChars[char]; ok {
				guardi = i
				guardj = j
				guardChar = char
			}
		}
	}
	return res, guardi, guardj, guardChar
}
