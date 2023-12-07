package com.oliver.adventofcode.year2023

import com.oliver.adventofcode.YEAR_2023
import com.oliver.adventofcode.readFile

class Day5(private val lines: List<String>) {

    private val almanac = parseInput()

    companion object {
        @JvmStatic
        fun fromInput(): Day5 {
            return Day5(readFile(5, YEAR_2023))
        }
    }

    data class Almanac(val seeds: List<Long>, val ranges: List<List<Range>>)

    data class Range(val sourceStart: Long, val destinationStart: Long, val rangeLength: Long) {
        private val diff: Long = destinationStart - sourceStart

        fun inRange(index: Long): Boolean {
            return sourceStart <= index && index < sourceStart + rangeLength
        }

        fun getDestination(source: Long): Long {
            return source + diff
        }
    }

    private fun parseInput(): Almanac {
        val seeds = lines[0].split(": ")[1].split(" ").map { it.toLong() }
        val ranges = mutableListOf<List<Range>>()
        var curRanges = mutableListOf<Range>()

        for (lineIndex in 2 until lines.size) {
            val curLine = lines[lineIndex]
            if (curLine.isEmpty()) {
                ranges.add(curRanges)
                curRanges = mutableListOf()
            } else if (!curLine.endsWith("map:")) {
                val nums = curLine.split(" ")
                val destinationStart = nums[0].toLong()
                val sourceStart = nums[1].toLong()
                val rangeLength = nums[2].toLong()
                curRanges.add(Range(sourceStart, destinationStart, rangeLength))
                if (lineIndex == lines.size - 1) {
                    ranges.add(curRanges)
                }
            }
        }
        return Almanac(seeds, ranges)
    }

    private fun getLocation(seed: Long, almanac: Almanac, minLocation: Long): Long {
        var newMinLocation = minLocation
        var current = seed
        almanac.ranges.forEach { ranges ->
            for (range in ranges) {
                if (range.inRange(current)) {
                    current = range.getDestination(current)
                    break
                }
            }
        }
        if (current < newMinLocation) {
            newMinLocation = current
        }
        return newMinLocation
    }

    fun part1(): Long {
        var minLocation = Long.MAX_VALUE
        almanac.seeds.forEach { minLocation = getLocation(it, almanac, minLocation) }
        return minLocation
    }

    fun part2(): Long {
        var minLocation = Long.MAX_VALUE
        for (index in 0 until almanac.seeds.size / 2) {
            val seedRangeStart = almanac.seeds[index * 2]
            val seedRangeLength = almanac.seeds[index * 2 + 1]
            for (seed in seedRangeStart until seedRangeStart + seedRangeLength) {
                minLocation = getLocation(seed, almanac, minLocation)
            }
        }
        return minLocation
    }
}