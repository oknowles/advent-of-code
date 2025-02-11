package com.oliver.adventofcode.year2023

import spock.lang.Specification

class Day8Test extends Specification {

    private Day8 underTest

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
            def example = [
                    "RL",
                    "",
                    "AAA = (BBB, CCC)",
                    "BBB = (DDD, EEE)",
                    "CCC = (ZZZ, GGG)",
                    "DDD = (DDD, DDD)",
                    "EEE = (EEE, EEE)",
                    "GGG = (GGG, GGG)",
                    "ZZZ = (ZZZ, ZZZ)",
            ]
            underTest = new Day8(example)

        when:
            long result = underTest.part1()

        then:
            result == 2
    }

    def "example part 2 test"() {
        given:
            def example = [
                    "LR",
                    "",
                    "11A = (11B, XXX)",
                    "11B = (XXX, 11Z)",
                    "11Z = (11B, XXX)",
                    "22A = (22B, XXX)",
                    "22B = (22C, 22C)",
                    "22C = (22Z, 22Z)",
                    "22Z = (22B, 22B)",
                    "XXX = (XXX, XXX)",
            ]
            underTest = new Day8(example)

        when:
            long result = underTest.part2()

        then:
            result == 6
    }
}
