package com.oliver.adventofcode.year2018

import spock.lang.Specification
import spock.lang.Unroll

class Day1Test extends Specification {

    private Day1 underTest

    def "run day 1"() {
        given:
            underTest = Day1.fromInput()

        when:
            long firstHalf = underTest.getResultFrequency()
            long secondHalf = underTest.getFirstRepeatedFrequency()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    @Unroll
    def "successfully return answer for second half for input #instructions"() {
        given:
            underTest = new Day1(instructions)

        when:
            long result = underTest.getFirstRepeatedFrequency()

        then:
            result == expectedResult

        where:
            instructions         | expectedResult
            [+1, -1]             | 0
            [+3, +3, +4, -2, -4] | 10
            [-6, +3, +8, +5, -6] | 5
            [+7, +7, -2, -7, -4] | 14
    }
}
