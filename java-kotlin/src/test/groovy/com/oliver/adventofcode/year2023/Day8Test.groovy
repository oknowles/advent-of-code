package com.oliver.adventofcode.year2023

import spock.lang.Specification

class Day8Test extends Specification {

    private Day8 underTest

    private def example = [
    ]

    def "run day"() {
        given:
            underTest = Day8.fromInput()

        when:
            long part1 = underTest.part1()
            long part2 = underTest.part2()

        then:
            println "part 1 = $part1"
            println "part 2 = $part2"
    }

    def "example part 1 test"() {
        given:
            underTest = new Day8(example)

        when:
            long result = underTest.part1()

        then:
            result == -1
    }

    def "example part 2 test"() {
        given:
            underTest = new Day8(example)

        when:
            long result = underTest.part2()

        then:
            result == -1
    }
}
