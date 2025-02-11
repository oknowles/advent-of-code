package day7

import (
	"adventofcode/pkg/aoc24/util"
	"strings"
)

type Solution struct{}

type equation struct {
	value  int
	inputs []int
}

func (s Solution) Part1(input []string) int {
	return sumValidEquations(parseInput(input), add, mult)
}

func (s Solution) Part2(input []string) int {
	return sumValidEquations(parseInput(input), add, mult, concat)
}

func sumValidEquations(equations []equation, operators ...func(int, int) int) int {
	res := 0
	for _, eq := range equations {
		if validResult(eq.value, 0, eq.inputs, operators...) {
			res += eq.value
		}
	}
	return res
}

func validResult(target, current int, values []int, operators ...func(int, int) int) bool {
	if len(values) == 0 {
		return current == target
	}

	result := false
	nextValues := values[1:]
	for _, op := range operators {
		result = result || validResult(target, op(current, values[0]), nextValues, operators...)
	}
	return result
}

func add(x, y int) int {
	return x + y
}

func mult(x, y int) int {
	if x == 0 {
		// none of the input values are 0 and we want to avoid beginning by multiplying by 0
		return y
	}
	return x * y
}

func concat(x, y int) int {
	// e.g. 15 || 25 = 1525
	// we want to basically do x * 10^(# digits in y) + y
	xMultiplier := 1
	yCopy := y
	for yCopy > 0 {
		xMultiplier *= 10
		yCopy = yCopy / 10
	}
	return (x * xMultiplier) + y
}

func parseInput(input []string) []equation {
	result := make([]equation, len(input))
	for i, l := range input {
		eq := equation{}
		split := strings.Split(l, ":")
		eq.value = util.MustConv(split[0])
		stringInputs := strings.Split(strings.TrimSpace(split[1]), " ")
		inputs := make([]int, len(stringInputs))
		for j, s := range stringInputs {
			inputs[j] = util.MustConv(s)
		}
		eq.inputs = inputs
		result[i] = eq
	}
	return result
}
