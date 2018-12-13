package com.oliver.adventofcode.year2018

import com.oliver.adventofcode.readFile

class Day1(private val instructions: List<Int>) {
    companion object {
        @JvmStatic
        fun fromInput(): Day1 {
            val instructions = readFile(1).map {
                val value = Integer.parseInt(it.substring(1))
                if (it[0] == '+') value else value * -1
            }
            return Day1(instructions)
        }
    }

    fun getResultFrequency(): Long {
        var frequency = 0L
        instructions.forEach { frequency += it }
        return frequency
    }

    fun getFirstRepeatedFrequency(): Long {
        val seenFrequencies = hashSetOf(0L)
        var currentFrequency = 0L
        var instructionPointer = 0
        while (true) {
            currentFrequency += instructions[instructionPointer]
            if (seenFrequencies.contains(currentFrequency)) {
                return currentFrequency
            }
            seenFrequencies.add(currentFrequency)
            instructionPointer = (instructionPointer + 1) % instructions.size
        }
    }
}