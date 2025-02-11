package day9_test

import (
	"adventofcode/pkg/aoc24/day9"
	"github.com/stretchr/testify/assert"
	"testing"
)

var input = []string{"2333133121414131402"}

func TestPart1(t *testing.T) {
	res := day9.Solution{}.Part1(input)
	assert.Equal(t, 1928, res)
}

func TestPart2(t *testing.T) {
	res := day9.Solution{}.Part2(input)
	assert.Equal(t, 2858, res)
}
