package com.oliver.adventofcode.year2023

import spock.lang.Specification

class Day9Test extends Specification {

    private Day9 underTest

    def "run day"() {
        given:
            underTest = Day9.fromInput()

        when:
            long part1 = underTest.part1()
            long part2 = underTest.part2()

        then:
            println "part 1 = $part1"
            println "part 2 = $part2"
    }

    def "example part 1 test"() {
        given:
            def example = [
                    "0 3 6 9 12 15",
                    "1 3 6 10 15 21",
                    "10 13 16 21 30 45",
            ]
            underTest = new Day9(example)

        when:
            long result = underTest.part1()

        then:
            result == 114
    }

    def "example part 2 test"() {
        given:
            def example = [
                    "0 3 6 9 12 15",
                    "1 3 6 10 15 21",
                    "10 13 16 21 30 45",
            ]
            underTest = new Day9(example)

        when:
            long result = underTest.part2()

        then:
            result == 2
    }
}
