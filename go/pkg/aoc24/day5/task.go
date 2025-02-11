package day5

import (
	"adventofcode/pkg/aoc24/util"
	"strings"
)

type Solution struct{}

func (s Solution) Part1(input []string) int {
	ruleMap, lists := parseInput(input)
	res := 0
	for _, list := range lists {
		if validList(list, ruleMap) {
			res += list[len(list)/2]
		}
	}
	return res
}

func (s Solution) Part2(input []string) int {
	ruleMap, lists := parseInput(input)
	res := 0
	for _, list := range lists {
		wasInvalid := fixList(list, ruleMap)
		if wasInvalid {
			res += list[len(list)/2]
		}
	}
	return res
}

func validList(list []int, ruleMap map[int][]int) bool {
	seen := map[int]bool{}
	for _, i := range list {
		for _, wantUnseen := range ruleMap[i] {
			if seen[wantUnseen] {
				return false
			}
		}
		seen[i] = true
	}
	return true
}

func fixList(list []int, ruleMap map[int][]int) bool {
	swapCount := swapInvalid(list, ruleMap)
	invalid := swapCount > 0
	for swapCount != 0 {
		swapCount = swapInvalid(list, ruleMap)
	}
	return invalid
}

func swapInvalid(list []int, ruleMap map[int][]int) int {
	seen := map[int]int{}
	swapCount := 0
	for i, num := range list {
		for _, wantBefore := range ruleMap[num] {
			if j, ok := seen[wantBefore]; ok {
				// seen in map, so swap values
				tmp := list[i]
				list[i] = wantBefore
				list[j] = tmp
				seen[wantBefore] = i
				seen[num] = j
				swapCount++
				break
			}
		}
		if list[i] == num {
			seen[num] = i // only set if not swapped
		}
	}
	return swapCount
}

func parseInput(input []string) (map[int][]int, [][]int) {
	// map where key > vals represents a relationship where key must come before all vals
	ruleMap := map[int][]int{}
	var lists [][]int

	parsedRules := false
	for _, l := range input {
		if len(l) == 0 {
			parsedRules = true
			continue
		}

		if !parsedRules {
			split := strings.Split(l, "|")
			before := util.MustConv(split[0])
			after := util.MustConv(split[1])
			ruleMap[before] = append(ruleMap[before], after)
		} else {
			split := strings.Split(l, ",")
			list := make([]int, len(split))
			for i, s := range split {
				list[i] = util.MustConv(s)
			}
			lists = append(lists, list)
		}
	}

	return ruleMap, lists
}
