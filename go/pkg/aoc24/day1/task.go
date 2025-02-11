package day1

import (
	"adventofcode/pkg/aoc24/util"
	"sort"
	"strings"
)

type Solution struct{}

func (s Solution) Part1(input []string) int {
	list1, list2 := parseInput(input)
	sort.Ints(list1)
	sort.Ints(list2)

	res := 0
	for i := range list1 {
		dist := list1[i] - list2[i]
		if dist < 0 {
			dist = dist * -1
		}
		res += dist

	}
	return res
}

func (s Solution) Part2(input []string) int {
	list1, list2 := parseInput(input)

	counts := make(map[int]int)
	for _, i := range list2 {
		counts[i]++
	}

	res := 0
	for _, i := range list1 {
		res += i * counts[i]
	}
	return res
}

func parseInput(input []string) ([]int, []int) {
	var list1, list2 []int
	for _, l := range input {
		s := strings.Split(l, "   ")
		list1 = append(list1, util.MustConv(s[0]))
		list2 = append(list2, util.MustConv(s[1]))
	}

	return list1, list2
}
