package com.oliver.adventofcode.year2023

import spock.lang.Specification

class Day3Test extends Specification {

    private Day3 underTest

    private def example = [
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...\$.*....",
            ".664.598..",
    ]

    def "run day"() {
        given:
            underTest = Day3.fromInput()

        when:
            int part1 = underTest.part1()
            int part2 = underTest.part2()

        then:
            println "part 1 = $part1"
            println "part 2 = $part2"
    }

    def "example part 1 test"() {
        given:
            underTest = new Day3(example)

        when:
            int result = underTest.part1()

        then:
            result == 4361
    }

    def "example part 2 test"() {
        given:
            underTest = new Day3(example)

        when:
            int result = underTest.part2()

        then:
            result == 467835
    }
}
