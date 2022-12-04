package com.oliver.adventofcode.year2018

import spock.lang.Specification

class Day4Test extends Specification {

    private Day4 underTest

    def "run day 4"() {
        given:
            underTest = Day4.fromInput()

        when:
            int firstHalf = underTest.getAnswerUsingStrategyOne()
            int secondHalf = underTest.getAnswerUsingStrategyTwo()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "correctly return answer for part one"() {
        given:
            underTest = new Day4([
                    "[1518-11-01 00:00] Guard #10 begins shift",
                    "[1518-11-02 00:40] falls asleep",
                    "[1518-11-01 00:25] wakes up",
                    "[1518-11-01 00:30] falls asleep",
                    "[1518-11-04 00:46] wakes up",
                    "[1518-11-01 00:55] wakes up",
                    "[1518-11-01 23:58] Guard #99 begins shift",
                    "[1518-11-03 00:24] falls asleep",
                    "[1518-11-03 00:29] wakes up",
                    "[1518-11-02 00:50] wakes up",
                    "[1518-11-04 00:02] Guard #99 begins shift",
                    "[1518-11-04 00:36] falls asleep",
                    "[1518-11-03 00:05] Guard #10 begins shift",
                    "[1518-11-05 00:03] Guard #99 begins shift",
                    "[1518-11-01 00:05] falls asleep",
                    "[1518-11-05 00:45] falls asleep",
                    "[1518-11-05 00:55] wakes up"])

        when:
            int result = underTest.getAnswerUsingStrategyOne()

        then:
            result == 240
    }

    def "correctly return answer for part two"() {
        given:
            underTest = new Day4([
                    "[1518-11-01 00:00] Guard #10 begins shift",
                    "[1518-11-02 00:40] falls asleep",
                    "[1518-11-01 00:25] wakes up",
                    "[1518-11-01 00:30] falls asleep",
                    "[1518-11-04 00:46] wakes up",
                    "[1518-11-01 00:55] wakes up",
                    "[1518-11-01 23:58] Guard #99 begins shift",
                    "[1518-11-03 00:24] falls asleep",
                    "[1518-11-03 00:29] wakes up",
                    "[1518-11-02 00:50] wakes up",
                    "[1518-11-04 00:02] Guard #99 begins shift",
                    "[1518-11-04 00:36] falls asleep",
                    "[1518-11-03 00:05] Guard #10 begins shift",
                    "[1518-11-05 00:03] Guard #99 begins shift",
                    "[1518-11-01 00:05] falls asleep",
                    "[1518-11-05 00:45] falls asleep",
                    "[1518-11-05 00:55] wakes up"])

        when:
            long result = underTest.getAnswerUsingStrategyTwo()

        then:
            result == 4455
    }
}
