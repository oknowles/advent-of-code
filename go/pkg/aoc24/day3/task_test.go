package day3_test

import (
	"adventofcode/pkg/aoc24/day3"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestPart1(t *testing.T) {
	input := []string{"xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"}
	res := day3.Solution{}.Part1(input)
	assert.Equal(t, 161, res)
}

func TestPart2(t *testing.T) {
	input := []string{"xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"}
	res := day3.Solution{}.Part2(input)
	assert.Equal(t, 48, res)
}
