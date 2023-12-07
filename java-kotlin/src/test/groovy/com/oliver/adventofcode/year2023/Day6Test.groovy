package com.oliver.adventofcode.year2023

import spock.lang.Specification

class Day6Test extends Specification {

    private Day6 underTest

    private def example = [
            "Time:      7  15   30",
            "Distance:  9  40  200",
    ]

    def "run day"() {
        given:
            underTest = Day6.fromInput()

        when:
            long part1 = underTest.part1()
            long part2 = underTest.part2()

        then:
            println "part 1 = $part1"
            println "part 2 = $part2"
    }

    def "example part 1 test"() {
        given:
            underTest = new Day6(example)

        when:
            long result = underTest.part1()

        then:
            result == 288
    }

    def "example part 2 test"() {
        given:
            underTest = new Day6(example)

        when:
            long result = underTest.part2()

        then:
            result == 71503
    }
}
