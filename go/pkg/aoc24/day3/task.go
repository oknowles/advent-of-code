package day3

import (
	"adventofcode/pkg/aoc24/util"
	"regexp"
	"strings"
)

type Solution struct{}

var (
	multRegex      = regexp.MustCompile("mul\\((\\d+),(\\d+)\\)")
	startMultRegex = regexp.MustCompile("^mul\\((\\d+),(\\d+)\\)")
	enableRegex    = regexp.MustCompile("^do\\(\\)")
	disableRegex   = regexp.MustCompile("^don't\\(\\)")
)

func (s Solution) Part1(input []string) int {
	parsed := parseInput(input)
	matches := multRegex.FindAllStringSubmatch(parsed, -1)
	res := 0
	for _, m := range matches {
		res += getMult(m)
	}
	return res
}

func (s Solution) Part2(input []string) int {
	parsed := parseInput(input)
	res := 0
	enabled := true
	for i := 0; i < len(parsed)-4; i++ {
		// this shouldn't actually be as bad as it first seems as it'll just create a
		// new pointer rather than do any actual copying of the string
		substring := parsed[i:]
		if enableRegex.MatchString(substring) {
			enabled = true
		} else if disableRegex.MatchString(substring) {
			enabled = false
		} else if enabled {
			m := startMultRegex.FindStringSubmatch(substring)
			if len(m) > 0 {
				res += getMult(m)
			}
		}
	}
	return res
}

func getMult(match []string) int {
	// match will be of the format ["mul(2,4)", "2", "4"]
	left := util.MustConv(match[1])
	right := util.MustConv(match[2])
	return left * right
}

func parseInput(input []string) string {
	return strings.Join(input, "")
}
