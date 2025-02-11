package day8_test

import (
	"adventofcode/pkg/aoc24/day8"
	"github.com/stretchr/testify/assert"
	"testing"
)

var input = []string{
	"............",
	"........0...",
	".....0......",
	".......0....",
	"....0.......",
	"......A.....",
	"............",
	"............",
	"........A...",
	".........A..",
	"............",
	"............",
}

func TestPart1(t *testing.T) {
	res := day8.Solution{}.Part1(input)
	assert.Equal(t, 14, res)
}

func TestPart2(t *testing.T) {
	res := day8.Solution{}.Part2(input)
	assert.Equal(t, 34, res)
}
