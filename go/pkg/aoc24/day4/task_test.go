package day4_test

import (
	"adventofcode/pkg/aoc24/day4"
	"github.com/stretchr/testify/assert"
	"testing"
)

var input = []string{
	"MMMSXXMASM",
	"MSAMXMSMSA",
	"AMXSXMAAMM",
	"MSAMASMSMX",
	"XMASAMXAMM",
	"XXAMMXXAMA",
	"SMSMSASXSS",
	"SAXAMASAAA",
	"MAMMMXMMMM",
	"MXMXAXMASX",
}

func TestPart1(t *testing.T) {
	res := day4.Solution{}.Part1(input)
	assert.Equal(t, 18, res)
}

func TestPart2(t *testing.T) {
	res := day4.Solution{}.Part2(input)
	assert.Equal(t, 9, res)
}
