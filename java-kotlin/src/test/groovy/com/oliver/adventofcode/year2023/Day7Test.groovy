package com.oliver.adventofcode.year2023

import spock.lang.Specification

class Day7Test extends Specification {

    private Day7 underTest

    private def example = [
            "32T3K 765",
            "T55J5 684",
            "KK677 28",
            "KTJJT 220",
            "QQQJA 483",
    ]

    def "run day"() {
        given:
            underTest = Day7.fromInput()

        when:
            long part1 = underTest.part1()
            long part2 = underTest.part2()

        then:
            println "part 1 = $part1"
            println "part 2 = $part2"
    }

    def "example part 1 test"() {
        given:
            underTest = new Day7(example)

        when:
            long result = underTest.part1()

        then:
            result == 6440
    }

    def "example part 2 test"() {
        given:
            underTest = new Day7(example)

        when:
            long result = underTest.part2()

        then:
            result == 5905
    }
}
