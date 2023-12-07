package com.oliver.adventofcode.year2023

import com.oliver.adventofcode.YEAR_2023
import com.oliver.adventofcode.readFile

class Day2(private val lines: List<String>) {

    companion object {
        @JvmStatic
        fun fromInput(): Day2 {
            return Day2(readFile(2, YEAR_2023))
        }
    }

    fun part1(): Int {
        val validLines = mutableListOf<Int>()
        lines.forEachIndexed { index, line ->
            var valid = true
            val maxCounts = mutableMapOf("red" to 12, "green" to 13, "blue" to 14)
            val stripped = line.substring(line.indexOfFirst { it == ':' } + 1)
            val sets = stripped.split(";")
            for (set in sets) {
                val cubeChoices = set.trim().split(", ")
                for (choice in cubeChoices) {
                    val split = choice.split(" ")
                    val count = split[0].toInt()
                    val colour = split[1]
                    val remaining = maxCounts.getOrDefault(colour, 0)
                    if (remaining < count) {
                        valid = false
                        break
                    }
                }
            }
            if (valid) {
                validLines.add(index + 1)
            }
        }
        return validLines.sum()
    }

    fun part2(): Int {
        return lines.map { line ->
            val minCounts = mutableMapOf<String, Int>()
            val stripped = line.substring(line.indexOfFirst { it == ':' } + 1)
            val sets = stripped.split(";")
            for (set in sets) {
                val cubeChoices = set.trim().split(", ")
                for (choice in cubeChoices) {
                    val split = choice.split(" ")
                    val count = split[0].toInt()
                    val colour = split[1]
                    val curMin = minCounts.getOrDefault(colour, 0)
                    if (curMin < count) {
                        minCounts[colour] = count
                    }
                }
            }
            minCounts.values.reduce { acc, element ->
                acc * element
            }
        }.sum()
    }
}