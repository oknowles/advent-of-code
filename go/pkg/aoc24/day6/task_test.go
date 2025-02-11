package day6_test

import (
	"adventofcode/pkg/aoc24/day6"
	"github.com/stretchr/testify/assert"
	"testing"
)

var input = []string{
	"....#.....",
	".........#",
	"..........",
	"..#.......",
	".......#..",
	"..........",
	".#..^.....",
	"........#.",
	"#.........",
	"......#...",
}

func TestPart1(t *testing.T) {
	res := day6.Solution{}.Part1(input)
	assert.Equal(t, 41, res)
}

func TestPart2(t *testing.T) {
	res := day6.Solution{}.Part2(input)
	assert.Equal(t, 6, res)
}
