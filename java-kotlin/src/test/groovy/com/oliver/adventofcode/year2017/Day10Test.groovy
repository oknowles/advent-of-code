package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.Utils
import spock.lang.Specification
import spock.lang.Unroll

class Day10Test extends Specification {

    private static final String DAY_10_INPUT = Utils.readFile(10)[0]

    private Day10 underTest

    def "run day 10 first half"() {
        given:
            int[] input = Utils.stringToArray(DAY_10_INPUT, ",")
            underTest = new Day10(256, input)

        when:
            int result = underTest.getFirstRoundMultiple()

        then:
            println "first half = $result"
    }

    def "run day 10 second half"() {
        given:
            underTest = Day10.fromString(DAY_10_INPUT)

        when:
            String result = underTest.getKnotHash()

        then:
            println "second half = $result"
    }

    def "successfully run first round multiple"() {
        given:
            underTest = new Day10(5, [3,4,1,5] as int[])

        when:
            int result = underTest.getFirstRoundMultiple()

        then:
            result == 12
    }

    @Unroll
    def "successfully get knot hash for input #input"() {
        given:
            underTest = Day10.fromString(input)

        when:
            String result = underTest.getKnotHash()

        then:
            result == expectedResult

        where:
            input      | expectedResult
            ""         | "a2582a3a0e66e6e86e3812dcb672a272"
            "AoC 2017" | "33efeb34ea91902bb2f59c9920caa6cd"
            "1,2,3"    | "3efbe78a8d82f29979031a4aa0b16a9d"
            "1,2,4"    | "63960835bcdc130f0b66d7ff4f6a5a8e"
    }
}
