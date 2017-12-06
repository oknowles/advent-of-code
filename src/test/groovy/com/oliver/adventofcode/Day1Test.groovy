package com.oliver.adventofcode

import spock.lang.Specification
import spock.lang.Unroll

class Day1Test extends Specification {

    private Day1 underTest

    def "run day one"() {
        given:
            underTest = Day1.fromInput()

        when:
            long firstHalf = underTest.getSumAdjacentMatches()
            long secondHalf = underTest.getSumHalfMatches()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    @Unroll
    def "successfully return answer for first half for input #stringInput"() {
        given:
            underTest = new Day1(stringInput)

        when:
            long result = underTest.getSumAdjacentMatches()

        then:
            result == expectedResult

        where:
            stringInput | expectedResult
            "1122"      | 3
            "1111"      | 4
            "1234"      | 0
            "91212129"  | 9
    }

    @Unroll
    def "successfully return answer for second half for input #stringInput"() {
        given:
            underTest = new Day1(stringInput)

        when:
            long result = underTest.getSumHalfMatches()

        then:
            result == expectedResult

        where:
            stringInput | expectedResult
            "1212"      | 6
            "1221"      | 0
            "123425"    | 4
            "123123"    | 12
            "12131415"  | 4
    }
}
