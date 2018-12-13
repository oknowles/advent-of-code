package com.oliver.adventofcode.year2018

import com.oliver.adventofcode.readFile
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Day4(recordStrings: List<String>) {
    companion object {
        @JvmStatic
        fun fromInput(): Day4 {
            return Day4(readFile(4))
        }
    }
    private val records = parseRecordStrings(recordStrings)

    private fun parseRecordStrings(recordStrings: List<String>): List<Record> {
        val records = ArrayList<Record>()
        var currentGuardID = -1
        recordStrings.sortedBy { it.substring(0, 18) }.forEach {
            val parts = it.split(' ')
            val dateTimeString = "${parts[0].trim('[')}T${parts[1].trimEnd(']')}:00"
            val dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME)
            if (it.contains("Guard")) {
                currentGuardID = Integer.parseInt(parts[3].trim('#'))
            }
            records.add(Record(dateTime, currentGuardID, it.contains("falls asleep")))
        }
        return records
    }

    fun getAnswerUsingStrategyOne(): Int {
        val chosenGuard = getGuardMinuteSleepCount().maxBy { it.value.sum() }!!
        return chosenGuard.key * chosenGuard.value.indices.maxBy { chosenGuard.value[it] }!!
    }

    fun getAnswerUsingStrategyTwo(): Int {
        val chosenGuard = getGuardMinuteSleepCount().maxBy { it.value.max()!! }!!
        return chosenGuard.key * chosenGuard.value.indices.maxBy { chosenGuard.value[it] }!!
    }

    private fun getGuardMinuteSleepCount(): Map<Int, Array<Int>> {
        val guardSleepCount = HashMap<Int, Array<Int>>()
        var hasBeenSleeping = false
        for (i in 0 until records.size) {
            if (records[i].sleeping) {
                // don't do anything yet
                hasBeenSleeping = true
                continue
            }
            if (hasBeenSleeping) {
                var dateTime = records[i - 1].dateTime
                val guardID = records[i].guardID
                val minuteSleepCount = guardSleepCount.getOrDefault(guardID, Array(60) { 0 })
                while (dateTime != records[i].dateTime) {
                    minuteSleepCount[dateTime.minute]++
                    dateTime = dateTime.plusMinutes(1)
                }
                guardSleepCount[guardID] = minuteSleepCount
                hasBeenSleeping = false
            }
        }
        return guardSleepCount
    }

    data class Record(val dateTime: LocalDateTime,
                      val guardID: Int,
                      val sleeping: Boolean)
}