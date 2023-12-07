package com.oliver.adventofcode.year2023

import com.oliver.adventofcode.YEAR_2023
import com.oliver.adventofcode.readFile

class Day4(private val lines: List<String>) {

    companion object {
        @JvmStatic
        fun fromInput(): Day4 {
            return Day4(readFile(4, YEAR_2023))
        }
    }

    fun part1(): Int {
        return lines.map { line ->
            val winningNumbers = winningNumbers(line)
            if (winningNumbers == 0) {
                return@map 0
            }
            var result = 1
            for (i in 1 until winningNumbers) {
                result *= 2
            }
            result
        }.sum()
    }

    fun part2(): Int {
        val cardCounts = mutableMapOf<Int, Int>()
        var result = 0
        lines.mapIndexed { cardIndex, line ->
            val copyCount = cardCounts.getOrDefault(cardIndex, 1)
            val winningNumbers = winningNumbers(line)
            for (card in cardIndex + 1 until cardIndex + 1 + winningNumbers) {
                if (card >= lines.size) {
                    break
                }
                val curCount = cardCounts.getOrDefault(card, 1)
                cardCounts[card] = curCount + copyCount
            }
            result += cardCounts.getOrDefault(cardIndex, 1)
        }
        return result
    }

    private fun winningNumbers(line: String): Int {
        val parts = line.split(": ")[1].split(" | ")
        val winningNumbers = parts[0].trim().split("\\s+".toRegex()).map { it.toInt() }.toSet()
        val cardNums = parts[1].trim().split("\\s+".toRegex()).map { it.toInt() }
        var count = 0
        for (num in cardNums) {
            if (winningNumbers.contains(num)) {
                count++
            }
        }
        return count
    }
}