package day4

type Solution struct{}

const target = "XMAS"

const (
	left = iota
	right
	up
	down
	leftup
	rightup
	leftdown
	rightdown
)

func (s Solution) Part1(input []string) int {
	chars := parseInput(input)
	res := 0
	for i := range len(chars) {
		for j := range len(chars[i]) {
			for direction := left; direction <= rightdown; direction++ {
				if isWord(i, j, chars, direction) {
					res++
				}
			}
		}
	}
	return res
}

func (s Solution) Part2(input []string) int {
	chars := parseInput(input)
	res := 0
	for i := range len(chars) {
		for j := range len(chars[i]) {
			if isX(i, j, chars) {
				res++
			}
		}
	}
	return res
}

func isWord(i, j int, chars [][]rune, direction int) bool {
	if chars[i][j] != 'X' {
		return false
	}

	rowAddition := getRowAddition(direction)
	colAddition := getColAddition(direction)
	for count, char := range target {
		row := i + (count * rowAddition)
		col := j + (count * colAddition)
		if row < 0 || row >= len(chars) || col < 0 || col >= len(chars[0]) {
			return false
		}
		if chars[row][col] != char {
			return false
		}
	}
	return true
}

func isX(i, j int, chars [][]rune) bool {
	if i > len(chars)-3 || j > len(chars[0])-3 {
		return false
	}

	if chars[i+1][j+1] != 'A' {
		return false
	}

	leftDiag := chars[i][j] == 'M' && chars[i+2][j+2] == 'S' || chars[i][j] == 'S' && chars[i+2][j+2] == 'M'
	rightDiag := chars[i][j+2] == 'M' && chars[i+2][j] == 'S' || chars[i][j+2] == 'S' && chars[i+2][j] == 'M'

	return leftDiag && rightDiag
}

func getRowAddition(direction int) int {
	switch direction {
	case up, leftup, rightup:
		return -1
	case down, leftdown, rightdown:
		return 1
	default:
		return 0
	}
}

func getColAddition(direction int) int {
	switch direction {
	case left, leftup, leftdown:
		return -1
	case right, rightup, rightdown:
		return 1
	default:
		return 0
	}
}

func parseInput(input []string) [][]rune {
	res := make([][]rune, len(input))
	for i, l := range input {
		row := make([]rune, len(l))
		for j, c := range l {
			row[j] = c
		}
		res[i] = row
	}
	return res
}
