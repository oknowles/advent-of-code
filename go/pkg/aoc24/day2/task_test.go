package day2_test

import (
	"adventofcode/pkg/aoc24/day2"
	"github.com/stretchr/testify/assert"
	"testing"
)

var input = []string{
	"7 6 4 2 1",
	"1 2 7 8 9",
	"9 7 6 2 1",
	"1 3 2 4 5",
	"8 6 4 4 1",
	"1 3 6 7 9",
	"3 1 2 4 5",
	"3 1 2 4 4",
	"1 3 2 4 4",
}

func TestPart1(t *testing.T) {
	res := day2.Solution{}.Part1(input)
	assert.Equal(t, 2, res)
}

func TestPart2(t *testing.T) {
	res := day2.Solution{}.Part2(input)
	assert.Equal(t, 5, res)
}
