package com.oliver.adventofcode.year2023

import spock.lang.Specification

class Day5Test extends Specification {

    private Day5 underTest

    private def example = [
            "seeds: 79 14 55 13",
            "",
            "seed-to-soil map:",
            "50 98 2",
            "52 50 48",
            "",
            "soil-to-fertilizer map:",
            "0 15 37",
            "37 52 2",
            "39 0 15",
            "",
            "fertilizer-to-water map:",
            "49 53 8",
            "0 11 42",
            "42 0 7",
            "57 7 4",
            "",
            "water-to-light map:",
            "88 18 7",
            "18 25 70",
            "",
            "light-to-temperature map:",
            "45 77 23",
            "81 45 19",
            "68 64 13",
            "",
            "temperature-to-humidity map:",
            "0 69 1",
            "1 0 69",
            "",
            "humidity-to-location map:",
            "60 56 37",
            "56 93 4",
    ]

    def "run day"() {
        given:
            underTest = Day5.fromInput()

        when:
            long part1 = underTest.part1()
            long part2 = underTest.part2()

        then:
            println "part 1 = $part1"
            println "part 2 = $part2"
    }

    def "example part 1 test"() {
        given:
            underTest = new Day5(example)

        when:
            long result = underTest.part1()

        then:
            result == 35
    }

    def "example part 2 test"() {
        given:
            underTest = new Day5(example)

        when:
            long result = underTest.part2()

        then:
            result == 46
    }
}
