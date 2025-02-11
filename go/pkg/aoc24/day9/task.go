package day9

import (
	"adventofcode/pkg/aoc24/util"
)

type Solution struct{}

func (s Solution) Part1(input []string) int {
	fs := getFileSystem(input[0])

	head := 0
	tail := len(fs) - 1
	for head <= tail {
		if fs[head] != -1 {
			head++
			continue
		}
		if fs[tail] == -1 {
			tail--
			continue
		}
		fs[head] = fs[tail]
		fs[tail] = -1
		head++
		tail--
	}

	return getChecksum(fs)
}

func getChecksum(fs []int) int {
	res := 0
	for i, j := range fs {
		if j < 0 {
			break
		}
		res += i * j
	}
	return res
}

func (s Solution) Part2(input []string) int {
	fs := getFileSystem(input[0])

	//printFs(fs)

	head := 0
	tail := len(fs) - 1
	movedBlocks := make(map[int]bool)
	for tail > 0 && head < len(fs) {
		if fs[head] != -1 {
			head++
			continue
		}
		alreadyMoved := movedBlocks[fs[tail]]
		if fs[tail] == -1 || alreadyMoved {
			tail--
			continue
		}

		tmpTail := fs[tail]
		moved := tryToMoveBlock(head, tail, fs)
		movedBlocks[tmpTail] = true
		// move tail away from current block
		for range moved {
			tail--
		}
		//if moved > 0 {
		//	printFs(fs)
		//}
	}

	res := 0
	for i, j := range fs {
		if j < 0 {
			//print(".")
			continue
		}
		//print(j)
		res += i * j
	}
	return res
}

func printFs(fs []int) {
	for _, j := range fs {
		if j < 0 {
			print(".")
			continue
		}
		print(j)
	}
	println()
}

func tryToMoveBlock(head, tail int, fs []int) int {
	// try to move a block
	lengthOfBlock := 1
	tmpTail := tail
	for tmpTail > 0 && fs[tmpTail] == fs[tmpTail-1] {
		tmpTail--
		lengthOfBlock++
	}

	canMoveBlock := true
	for i := 0; i < lengthOfBlock; i++ {
		if head == len(fs)-i || fs[head+i] != -1 {
			canMoveBlock = false
			break
		}
	}

	if canMoveBlock && head < tail {
		//println(fmt.Sprintf("can move block! gap start: %v, tail block start: %v, block end: %v block length: %v, val: %v", head, tmpTail, tail, lengthOfBlock, fs[tmpTail]))
		movedVal := fs[tail]
		for i := 0; i < lengthOfBlock; i++ {
			fs[head+i] = movedVal
			fs[tail-i] = -1
		}
		return lengthOfBlock
	}

	// move head to next free space
	for head < len(fs) && fs[head] == -1 {
		head++
	}
	for head < len(fs) && fs[head] != -1 {
		head++
	}
	if head == len(fs) {
		return 0
	}
	return tryToMoveBlock(head, tail, fs)
}

func getFileSystem(input string) []int {
	var fs []int
	freeSpace := false
	curID := 0
	for _, c := range input {
		val := util.MustConv(string(c))
		toAppend := curID
		if freeSpace {
			toAppend = -1
		} else {
			curID++
		}
		for range val {
			fs = append(fs, toAppend)
		}
		freeSpace = !freeSpace
	}
	return fs
}
