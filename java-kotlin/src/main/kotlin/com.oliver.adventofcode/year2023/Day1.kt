package com.oliver.adventofcode.year2023

import com.oliver.adventofcode.YEAR_2023
import com.oliver.adventofcode.readFile

class Day1(private val lines: List<String>) {

    private val stringNums = mapOf(
            "0" to 0,
            "1" to 1,
            "2" to 2,
            "3" to 3,
            "4" to 4,
            "5" to 5,
            "6" to 6,
            "7" to 7,
            "8" to 8,
            "9" to 9,
            "zero" to 0,
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
    )

    companion object {
        @JvmStatic
        fun fromInput(): Day1 {
            return Day1(readFile(1, YEAR_2023))
        }
    }

    fun part1(): Int {
        return lines.map { line ->
            val firstNum = line.first { it in '0'..'9' }
            val lastNum = line.last { it in '0'..'9' }
            "$firstNum$lastNum".toInt()
        }.sum()
    }

    fun part2(): Int {
        var result = 0
        for (line in lines) {
            var minIndex = line.length
            var maxIndex = 0
            var firstNum = "0"
            var lastNum = "0"

            for (stringNum in stringNums.keys) {
                val indexes = stringNum.toRegex().findAll(line).map { it.range.first }
                if (indexes.any()) {
                    if (indexes.first() <= minIndex) {
                        minIndex = indexes.first()
                        firstNum = stringNum
                    }

                    if (indexes.last() >= maxIndex) {
                        maxIndex = indexes.last()
                        lastNum = stringNum
                    }
                }
            }
            result += "${stringNums[firstNum]}${stringNums[lastNum]}".toInt()
        }
        return result
    }
}