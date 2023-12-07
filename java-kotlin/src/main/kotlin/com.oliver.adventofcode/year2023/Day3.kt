package com.oliver.adventofcode.year2023

import com.oliver.adventofcode.YEAR_2023
import com.oliver.adventofcode.readFile

class Day3(private val lines: List<String>) {

    companion object {
        @JvmStatic
        fun fromInput(): Day3 {
            return Day3(readFile(3, YEAR_2023))
        }
    }

    private fun Char.isNumber() = (this in '0'..'9')
    private fun Char.isSymbol() = !this.isNumber() && this != '.'

    fun part1(): Int {
        // Mark what digits are part numbers
        val partNumbers = Array(lines.size) { Array(lines[0].length) { false } }
        for (i in lines.indices) {
            for (j in lines[0].indices) {
                val curChar = lines[i][j]
                if (curChar.isSymbol()) {
                    markNumbers(lines, partNumbers, i - 1, j)
                    markNumbers(lines, partNumbers, i + 1, j)
                    markNumbers(lines, partNumbers, i, j - 1)
                    markNumbers(lines, partNumbers, i, j + 1)
                    markNumbers(lines, partNumbers, i - 1, j - 1)
                    markNumbers(lines, partNumbers, i - 1, j + 1)
                    markNumbers(lines, partNumbers, i + 1, j - 1)
                    markNumbers(lines, partNumbers, i + 1, j + 1)
                }
            }
        }

        // Sum up all part numbers
        var result = 0
        var curNum = 0
        for (i in lines.indices) {
            result += curNum
            curNum = 0
            for (j in lines[0].indices) {
                if (!partNumbers[i][j]) {
                    result += curNum
                    curNum = 0
                } else {
                    if (curNum > 0) {
                        curNum *= 10
                    }
                    curNum += lines[i][j].toInt() - '0'.toInt()
                }
            }
        }
        return result
    }

    private fun markNumbers(lines: List<String>, markedNumbers: Array<Array<Boolean>>, i: Int, j: Int) {
        if (!markedNumbers.inRange(i, j) || markedNumbers[i][j] || !lines[i][j].isNumber()) {
            return
        }
        markedNumbers[i][j] = true
        // Need to mark all digit in the number so add left and right
        markNumbers(lines, markedNumbers, i, j - 1)
        markNumbers(lines, markedNumbers, i, j + 1)
    }

    private fun Array<Array<Boolean>>.inRange(i: Int, j: Int) = i in this.indices && j in this[0].indices
    private fun List<String>.inRange(i: Int, j: Int) = i in this.indices && j in this[0].indices

    fun part2(): Int {
        var result = 0
        val markedNumbers = Array(lines.size) { Array(lines[0].length) { false } }
        for (i in lines.indices) {
            for (j in lines[0].indices) {
                val curChar = lines[i][j]
                if (curChar.isSymbol()) {
                    println("Symbol at ($i,$j) - ${lines[i][j]} has adjacent num count ${adjacentNums(i, j)}")
                }
                if (curChar.isSymbol() && adjacentNums(i, j) == 2) {
                    println("Symbol at ($i,$j) has 2 adjacent nums")
                    result += listOf(
                    lines.getRatio(markedNumbers, i - 1, j),
                    lines.getRatio(markedNumbers, i + 1, j),
                    lines.getRatio(markedNumbers, i, j - 1),
                    lines.getRatio(markedNumbers, i, j + 1),
                    lines.getRatio(markedNumbers, i - 1, j - 1),
                    lines.getRatio(markedNumbers, i - 1, j + 1),
                    lines.getRatio(markedNumbers, i + 1, j - 1),
                    lines.getRatio(markedNumbers, i + 1, j + 1)
                    ).reduce { a, b -> a * b }
                }
            }
        }
        return result
    }

    private fun adjacentNums(i: Int, j: Int): Int {
        val seenDigits = Array(lines.size) { Array(lines[0].length) { false } }
        var count = 0
        for (x in i - 1 .. i + 1) {
            for (y in j - 1 .. j + 1) {
                if ((x == i && y == j) || !lines.inRange(x, y) || !lines[x][y].isNumber() || seenDigits[x][y]) {
                    continue
                }
                count++
                lines.getNumber(seenDigits, x, y)
            }
        }
        return count
    }

    private fun List<String>.getRatio(seenDigits: Array<Array<Boolean>>, i: Int, j: Int): Int {
        val ratio = getNumber(seenDigits, i, j)
        return if (ratio == "0" || ratio == "") 1 else ratio.toInt()
    }

    private fun List<String>.getNumber(seenDigits: Array<Array<Boolean>>, i: Int, j: Int): String {
        if (!this.inRange(i, j) || seenDigits[i][j] || !lines[i][j].isNumber()) {
            return ""
        }
        seenDigits[i][j] = true
        // Need to mark all digit in the number so add left and right
        return getNumber(seenDigits, i, j - 1) + this[i][j] + getNumber(seenDigits, i, j + 1)
    }
}