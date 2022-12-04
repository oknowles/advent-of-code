package com.oliver.adventofcode.year2018

import com.oliver.adventofcode.readFile

class Day3(claimStrings: List<String>) {
    companion object {
        @JvmStatic
        fun fromInput(): Day3 {
            return Day3(readFile(3))
        }
    }

    private val claims = claimStrings.map { it.toClaim() }
    private var squareClaimCount = getClaimCountPerSquare()

    private fun getClaimCountPerSquare(): HashMap<Pair<Int, Int>, Int> {
        val claimCount = HashMap<Pair<Int, Int>, Int>()
        claims.forEach {
            for (i in it.startCoordinates.first until it.startCoordinates.first + it.squareSize.first) {
                for (j in it.startCoordinates.second until it.startCoordinates.second + it.squareSize.second) {
                    val curSquare = Pair(i, j)
                    claimCount[curSquare] = claimCount.getOrDefault(curSquare, 0) + 1
                }
            }
        }
        return claimCount
    }

    fun squaresWithAtLeastNCounts(n: Int): Int {
        return squareClaimCount.values.count { it >= n }
    }

    fun findNonOverlappingClaimID(): Int {
        claims.forEach {
            if (!hasOverlaps(it.startCoordinates, it.squareSize)) {
                return it.claimID
            }
        }
        return 0
    }

    private fun hasOverlaps(startCoordinates: Pair<Int, Int>, squareSize: Pair<Int, Int>): Boolean {
        for (i in startCoordinates.first until startCoordinates.first + squareSize.first) {
            for (j in startCoordinates.second until startCoordinates.second + squareSize.second) {
                if (squareClaimCount[Pair(i, j)]!! > 1) {
                    return true
                }
            }
        }
        return false
    }

    private fun String.toClaim(): Claim {
        val parts = this.split(" ")
        val claimID = Integer.parseInt(parts[0].trim('#'))
        val startCoordinates: Pair<Int, Int> = parts[2].trimEnd(':')
                .split(",")
                .map { Integer.parseInt(it) }
                .zipWithNext()[0]
        val squareSize = parts[3].split("x")
                .map { Integer.parseInt(it) }
                .zipWithNext()[0]
        return Claim(claimID, startCoordinates, squareSize)
    }

    data class Claim(val claimID: Int,
                     val startCoordinates: Pair<Int, Int>,
                     val squareSize: Pair<Int, Int>)
}