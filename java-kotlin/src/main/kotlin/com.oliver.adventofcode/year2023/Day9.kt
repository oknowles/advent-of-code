package com.oliver.adventofcode.year2023

import com.oliver.adventofcode.YEAR_2023
import com.oliver.adventofcode.readFile
import java.util.*
import kotlin.math.max
import kotlin.math.min

class Day9(private val lines: List<String>) {

    companion object {
        @JvmStatic
        fun fromInput(): Day9 {
            return Day9(readFile(9, YEAR_2023))
        }
    }

    fun part1(): Int {
        return lines.map {
            val diffs = getDiffs(it.toVals())
            diffs.sumBy(List<Int>::last)
        }.sum()
    }

    fun part2(): Int {
        return lines.map {
            val diffs = getDiffs(it.toVals().reversed())
            diffs.sumBy(List<Int>::last)
        }.sum()
    }

    private fun String.toVals() = this.split(" ").map { it.toInt() }

    private fun getDiffs(vals: List<Int>): List<List<Int>> {
        val allDiffs = mutableListOf(vals)
        var zerod = false
        while (!zerod) {
            zerod = true
            val diffs = mutableListOf<Int>()
            val prevDiffs = allDiffs.last()
            for (i in 1 until prevDiffs.size) {
                val change = prevDiffs[i] - prevDiffs[i - 1]
                if (change != 0) {
                    zerod = false
                }
                diffs.add(change)
            }
            allDiffs.add(diffs)
        }
        return allDiffs
    }
}