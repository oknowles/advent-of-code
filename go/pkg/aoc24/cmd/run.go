package main

import (
	"adventofcode/pkg/aoc24/day9"
	"adventofcode/pkg/aoc24/input"
	"fmt"
)

type Solution interface {
	Part1(input []string) int
	Part2(input []string) int
}

func runDay(day int, sol Solution) {
	println(fmt.Sprintf("day %v", day))
	i := input.ReadInput(day)
	println(fmt.Sprintf("part 1: %v", sol.Part1(i)))
	println(fmt.Sprintf("part 2: %v", sol.Part2(i)))
	println()
}

func main() {
	//runDay(1, day1.Solution{})
	//runDay(2, day2.Solution{})
	//runDay(3, day3.Solution{})
	//runDay(4, day4.Solution{})
	//runDay(5, day5.Solution{})
	//runDay(6, day6.Solution{})
	//runDay(7, day7.Solution{})
	//runDay(8, day8.Solution{})
	runDay(9, day9.Solution{})
}
