package com.oliver.adventofcode.year2018

import com.oliver.adventofcode.YEAR_2018
import com.oliver.adventofcode.readFile

class Day5(private val polymer: String) {
    companion object {
        @JvmStatic
        fun fromInput(): Day5 {
            return Day5(readFile(5, YEAR_2018)[0])
        }
        private const val CHAR_DIFF = 'a' - 'A'
    }

    fun getUnitCountAfterFolding(): Int {
        return collapsePolymer(polymer).length
    }

    fun collapsePolymer(polymer: String): String {
        var result = polymer
        var charIndex = 0
        while (charIndex < result.length - 1) {
            if (Math.abs(result[charIndex] - result[charIndex + 1]) == CHAR_DIFF) {
                result = result.removeRange(charIndex, charIndex + 2)
                if (charIndex > 0) {
                    charIndex--
                }
            } else {
                charIndex++
            }
        }
        return result
    }

    fun findSmallestPolymer(): Int {
        var smallestPolymer = Integer.MAX_VALUE
        for (char in 'A' .. 'Z') {
            var temp = polymer
            var charIndex = 0
            while (charIndex < temp.length) {
                if (temp[charIndex] == char || temp[charIndex] == char + CHAR_DIFF) {
                    temp = temp.removeRange(charIndex, charIndex + 1)
                } else {
                    charIndex++
                }
            }
            val collapsedLength = collapsePolymer(temp).length
            if (collapsedLength < smallestPolymer) {
                smallestPolymer = collapsedLength
            }
        }
        return smallestPolymer
    }
}