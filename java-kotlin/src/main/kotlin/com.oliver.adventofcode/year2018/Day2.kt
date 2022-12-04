package com.oliver.adventofcode.year2018

import com.oliver.adventofcode.readFile

class Day2(private val boxIDs: List<String>) {
    companion object {
        @JvmStatic
        fun fromInput(): Day2 {
            return Day2(readFile(2))
        }
    }

    fun getChecksum(): Int {
        var idsWithTwoCharacters = 0
        var idsWithThreeCharacters = 0
        boxIDs.forEach {
            val charCounts = it.getCharCounts()
            idsWithTwoCharacters += if (charCounts.contains(2)) 1 else 0
            idsWithThreeCharacters += if (charCounts.contains(3)) 1 else 0
        }
        return idsWithTwoCharacters * idsWithThreeCharacters
    }

    fun getCommonLetters(): String {
        for (i in 0 until boxIDs.size) {
            for (j in i until boxIDs.size) {
                val matchingChars = removeDifferingChars(boxIDs[i], boxIDs[j])
                if (matchingChars.length == boxIDs[0].length - 1) {
                    return matchingChars
                }
            }
        }
        return ""
    }

    private fun String.getCharCounts(): Set<Int> {
        val charCounts = mutableMapOf<Char, Int>()
        this.forEach {
            charCounts[it] = charCounts.getOrDefault(it, 0) + 1
        }
        return charCounts.values.toSet()
    }

    private fun removeDifferingChars(boxID1: String, boxID2: String): String {
        val stringBuilder = StringBuilder()
        for (i in 0 until boxID1.length) {
            if (boxID1[i] == boxID2[i]) stringBuilder.append(boxID1[i])
        }
        return stringBuilder.toString()
    }
}