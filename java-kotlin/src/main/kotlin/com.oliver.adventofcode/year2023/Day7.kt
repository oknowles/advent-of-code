package com.oliver.adventofcode.year2023

import com.oliver.adventofcode.YEAR_2023
import com.oliver.adventofcode.readFile

class Day7(private val lines: List<String>) {

    companion object {
        @JvmStatic
        fun fromInput(): Day7 {
            return Day7(readFile(7, YEAR_2023))
        }
    }

    private enum class Rank {
        FIVE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, THREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD
    }

    private val handRanks = listOf(
            Rank.FIVE_OF_A_KIND,
            Rank.FOUR_OF_A_KIND,
            Rank.FULL_HOUSE,
            Rank.THREE_OF_A_KIND,
            Rank.TWO_PAIR,
            Rank.ONE_PAIR,
            Rank.HIGH_CARD
    )
    private val cardRanksPart1 = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    private val cardRanksPart2 = listOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J')

    private fun getRank(hand: String, wildcard: Char? = null): Rank {
        val charCount = mutableMapOf<Char, Int>()
        for (char in hand) {
            if (char != wildcard) {
                charCount[char] = charCount.getOrDefault(char, 0) + 1
            }
        }
        val wildcardCount = hand.count { it == wildcard }
        val sortedCounts = charCount.values.sorted().reversed()
        return when {
            ((sortedCounts.firstOrNull() ?: 0) + wildcardCount) == 5 -> Rank.FIVE_OF_A_KIND
            (sortedCounts[0] + wildcardCount) == 4 -> Rank.FOUR_OF_A_KIND
            (sortedCounts[0] + sortedCounts[1] + wildcardCount) == 5 -> Rank.FULL_HOUSE
            (sortedCounts[0] + wildcardCount) == 3 -> Rank.THREE_OF_A_KIND
            (sortedCounts[0] + sortedCounts[1] + wildcardCount) == 4 -> Rank.TWO_PAIR
            (sortedCounts[0] + wildcardCount) == 2 -> Rank.ONE_PAIR
            else -> Rank.HIGH_CARD
        }
    }

    private fun getWinnings(cardRanks: List<Char>, wildcard: Char? = null): Int {
        val ranks = mutableMapOf<Rank, MutableList<Pair<String, Int>>>()
        lines.forEach {
            val parts = it.split(' ')
            val rank = getRank(parts[0], wildcard)
            val rankHands = ranks.getOrDefault(rank, mutableListOf())
            rankHands.add(parts[0] to parts[1].toInt())
            ranks[rank] = rankHands
        }

        var result = 0
        var curRank = lines.size
        for (rank in handRanks) {
            val hands = ranks[rank] ?: continue
            val sorted = hands.sortedWith(Comparator { o1, o2 ->
                val (hand1, hand2) = o1.first to o2.first
                for (i in hand1.indices) {
                    if (hand1[i] != hand2[i]) {
                        return@Comparator cardRanks.indexOf(hand1[i]).compareTo(cardRanks.indexOf(hand2[i]))
                    }
                }
                return@Comparator 0
            })
            for (hand in sorted) {
                result += hand.second * curRank
                curRank--
            }
        }
        return result
    }

    fun part1(): Int {
        return getWinnings(cardRanksPart1)
    }

    fun part2(): Int {
        return getWinnings(cardRanksPart2, 'J')
    }
}