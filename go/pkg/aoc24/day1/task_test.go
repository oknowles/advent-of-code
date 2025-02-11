package day1_test

import (
	"adventofcode/pkg/aoc24/day1"
	"github.com/stretchr/testify/assert"
	"testing"
)

var input = []string{
	"3   4",
	"4   3",
	"2   5",
	"1   3",
	"3   9",
	"3   3",
}

func TestPart1(t *testing.T) {
	res := day1.Solution{}.Part1(input)
	assert.Equal(t, 11, res)
}

func TestPart2(t *testing.T) {
	res := day1.Solution{}.Part2(input)
	assert.Equal(t, 31, res)
}
