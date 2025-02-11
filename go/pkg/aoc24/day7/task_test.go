package day7_test

import (
	"adventofcode/pkg/aoc24/day7"
	"github.com/stretchr/testify/assert"
	"testing"
)

var input = []string{
	"190: 10 19",
	"3267: 81 40 27",
	"83: 17 5",
	"156: 15 6",
	"7290: 6 8 6 15",
	"161011: 16 10 13",
	"192: 17 8 14",
	"21037: 9 7 18 13",
	"292: 11 6 16 20",
}

func TestPart1(t *testing.T) {
	res := day7.Solution{}.Part1(input)
	assert.Equal(t, 3749, res)
}

func TestPart2(t *testing.T) {
	res := day7.Solution{}.Part2(input)
	assert.Equal(t, 11387, res)
}
