package com.oliver.adventofcode.year2023

import spock.lang.Specification

class Day4Test extends Specification {

    private Day4 underTest

    private def example = [
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
    ]

    def "run day"() {
        given:
            underTest = Day4.fromInput()

        when:
            int part1 = underTest.part1()
            int part2 = underTest.part2()

        then:
            println "part 1 = $part1"
            println "part 2 = $part2"
    }

    def "example part 1 test"() {
        given:
            underTest = new Day4(example)

        when:
            int result = underTest.part1()

        then:
            result == 13
    }

    def "example part 2 test"() {
        given:
            underTest = new Day4(example)

        when:
            int result = underTest.part2()

        then:
            result == 30
    }
}
