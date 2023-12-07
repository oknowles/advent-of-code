package com.oliver.adventofcode.year2023

import com.oliver.adventofcode.YEAR_2023
import com.oliver.adventofcode.readFile

class Day6(private val lines: List<String>) {

    companion object {
        @JvmStatic
        fun fromInput(): Day6 {
            return Day6(readFile(6, YEAR_2023))
        }
    }

    private fun parsePart1Input(): Map<Long, Long> {
        val times = lines[0].split(Regex("Time:\\s+"))[1].split(Regex("\\s+")).map { it.toLong() }
        val records = lines[1].split(Regex("Distance:\\s+"))[1].split(Regex("\\s+")).map { it.toLong() }
        val result = mutableMapOf<Long, Long>()
        for (i in times.indices) {
            result[times[i]] = records[i]
        }
        return result
    }

    private fun parsePart2Input(): Pair<Long, Long> {
        val time = lines[0].split(Regex("Time:\\s+"))[1].replace(" ", "").toLong()
        val record = lines[1].split(Regex("Distance:\\s+"))[1].replace(" ", "").toLong()
        return time to record
    }

    private fun waysToWin(time: Long, record: Long): Long {
        var waysToWin: Long = 0
        for (waitTime in 1 until time - 1) {
            val distance = waitTime * (time - waitTime)
            if (distance > record) {
                waysToWin++
            }
        }
        return waysToWin
    }

    fun part1(): Long {
        val map = parsePart1Input()
        var result: Long = 1
        for ((time, record) in map) {
            result *= waysToWin(time, record)
        }
        return result
    }

    fun part2(): Long {
        val (time, record) = parsePart2Input()
        return waysToWin(time, record)
    }
}