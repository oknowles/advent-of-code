package com.oliver.adventofcode.year2023

import spock.lang.Specification
import spock.lang.Unroll

class Day1Test extends Specification {

    private Day1 underTest

    def "run day"() {
        given:
            underTest = Day1.fromInput()

        when:
            int part1 = underTest.part1()
            int part2 = underTest.part2()

        then:
            println "part 1 = $part1"
            println "part 2 = $part2"
    }

    def "example part 1 test"() {
        given:
            def instructions = [
                    "1abc2",
                    "pqr3stu8vwx",
                    "a1b2c3d4e5f",
                    "treb7uchet",
            ]
            underTest = new Day1(instructions)

        when:
            int result = underTest.part1()

        then:
            result == 142
    }

    def "example part 2 test"() {
        given:
            def instructions = [
                    "two1nine",
                    "eightwothree",
                    "abcone2threexyz",
                    "xtwone3four",
                    "4nineeightseven2",
                    "zoneight234",
                    "7pqrstsixteen",
            ]
            underTest = new Day1(instructions)

        when:
            int result = underTest.part2()

        then:
            result == 281
    }
}
