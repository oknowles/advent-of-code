package input

import (
	"fmt"
	"os"
	"strings"
)

// ReadInput assumes that the file context will easily fit into memory so we can read the entire contents in one
// go. This avoids us having to worry about closing the file as the os method handles this for us. This assumption
// should be valid for all AOC problems.
func ReadInput(day int) []string {
	file, err := os.ReadFile(fmt.Sprintf("pkg/aoc24/input/day%v.txt", day))
	if err != nil {
		panic(err)
	}

	return strings.Split(string(file), "\n")
}
