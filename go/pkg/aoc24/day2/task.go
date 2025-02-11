package day2

import (
	"adventofcode/pkg/aoc24/util"
	"strings"
)

type Solution struct{}

func (s Solution) Part1(input []string) int {
	reports := parseInput(input)

	res := 0
	for _, report := range reports {
		if validReport(report) {
			res++
		}
	}
	return res
}

func (s Solution) Part2(input []string) int {
	reports := parseInput(input)

	res := 0
	for _, report := range reports {
		invalid := getInvalidIndex(report, -1)
		if invalid < 0 || getInvalidIndex(report, invalid) < 0 || getInvalidIndex(report, invalid-1) < 0 || getInvalidIndex(report, invalid-2) < 0 {
			res++
		}
	}
	return res
}

func validReport(report []int) bool {
	return getInvalidIndex(report, -1) < 0
}

func getInvalidIndex(report []int, skip int) int {
	head := 1
	tail := 0
	if skip == 0 {
		head = 2
		tail = 1
	}
	prevChange := report[head] - report[tail]
	for i := head; i < len(report); i++ {
		if i == skip {
			continue
		}
		change := report[i] - report[tail]
		sameMovement := (prevChange > 0) == (change > 0)
		validDiff := (-3 <= change && change <= -1) || (1 <= change && change <= 3)
		if !sameMovement || !validDiff {
			return i
		}
		tail++
		if tail == skip {
			tail++
		}
	}
	return -1
}

func parseInput(input []string) [][]int {
	var reports [][]int
	for _, l := range input {
		split := strings.Split(l, " ")
		report := make([]int, len(split))
		for i, s := range split {
			sInt := util.MustConv(s)
			report[i] = sInt
		}
		reports = append(reports, report)
	}

	return reports
}
