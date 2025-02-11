package com.oliver.adventofcode.year2023

import com.oliver.adventofcode.YEAR_2023
import com.oliver.adventofcode.readFile
import java.util.*
import kotlin.math.max
import kotlin.math.min

class Day8(private val lines: List<String>) {

    companion object {
        @JvmStatic
        fun fromInput(): Day8 {
            return Day8(readFile(8, YEAR_2023))
        }
    }

    private fun buildGraph(): MutableMap<String, Pair<String, String>> {
        val graph = mutableMapOf<String, Pair<String, String>>()
        for (i in 2 until lines.size) {
            val split = lines[i].split(" = ")
            val leftRight = split[1].replace(Regex("[()]"), "").split(", ")
            graph[split[0]] = leftRight[0] to leftRight[1]
        }
        return graph
    }

    fun part1(): Int {
        val graph = buildGraph()
        var cur = "AAA"
        var steps = 0
        while (true) {
            lines[0].forEach { char ->
                steps++
                cur = if (char == 'L') {
                    graph[cur]!!.first
                } else {
                    graph[cur]!!.second
                }
                if (cur == "ZZZ") {
                    return steps
                }
            }
        }
    }

    fun part2(): Long {
        val graph = buildGraph()
        val current = LinkedList(graph.keys.filter { it.endsWith("A") })
        var steps = 0L
        val stepsInvolved = mutableListOf<Long>()
        while (current.isNotEmpty()) {
            lines[0].forEach { char ->
                steps++
                for (i in current.indices) {
                    var cur = current.pop()
                    cur = if (char == 'L') {
                        graph[cur]!!.first
                    } else {
                        graph[cur]!!.second
                    }
                    if (cur.endsWith("Z")) {
                        stepsInvolved.add(steps)
                    } else {
                        current.add(cur)
                    }
                }
            }
        }
        return lowestCommonMultiple(stepsInvolved)
    }

    private fun lowestCommonMultiple(nums: List<Long>): Long {
        if (nums.size == 1) {
            return nums.first()
        }
        return lowestCommonMultiple(nums.first(), lowestCommonMultiple(nums.drop(1)))
    }

    private fun lowestCommonMultiple(a: Long, b: Long): Long {
        return a / greatestCommonDivisor(a, b) * b
    }

    private fun greatestCommonDivisor(a: Long, b: Long): Long {
        if (a == 0L) return b
        if (b == 0L) return a
        val largest = max(a, b)
        val smallest = min(a, b)
        val remainder = largest % smallest
        return greatestCommonDivisor(smallest, remainder)
    }
}