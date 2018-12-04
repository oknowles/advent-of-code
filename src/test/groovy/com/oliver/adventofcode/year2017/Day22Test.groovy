package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.Utils
import com.oliver.adventofcode.year2017.Day22
import spock.lang.Specification
import spock.lang.Unroll

class Day22Test extends Specification {

    private Day22 underTest

    def "run day 22 part 1"() {
        given:
            underTest = new Day22(Utils.readFile(22))

        when:
            int result = underTest.runBinaryBursts(10_000)

        then:
            println "first half = $result"
    }

    def "run day 22 part 2"() {
        given:
            underTest = new Day22(Utils.readFile(22))

        when:
            int result = underTest.runFourStateBursts(10_000_000)

        then:
            println "second half = $result"
    }

    @Unroll
    def "successfully return infection count when 2 states"() {
        given:
            underTest = new Day22(["..#",
                                   "#..",
                                   "..."])

        when:
            int result = underTest.runBinaryBursts(iterations)

        then:
            result == expectedResult

        where:
            iterations | expectedResult
            70         | 41
            10_000     | 5587
    }

    @Unroll
    def "successfully return infection count when 4 states"() {
        given:
            underTest = new Day22(["..#",
                                   "#..",
                                   "..."])

        when:
            int result = underTest.runFourStateBursts(iterations)

        then:
            result == expectedResult

        where:
            iterations | expectedResult
            100        | 26
            10_000_000 | 2511944
    }
}
