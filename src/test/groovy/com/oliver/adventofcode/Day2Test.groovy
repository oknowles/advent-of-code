package com.oliver.adventofcode

import spock.lang.Specification

class Day2Test extends Specification {

    private Day2 underTest

    def "run day two"() {
        given:
            underTest = Day2.fromInput()

        when:
            long firstHalf = underTest.getChecksum()
            long secondHalf = underTest.getDivisibleSum()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return answer for first half"() {
        given:
            List<int[]> input = [[5,1,9,5] as int[],
                                 [7,5,3] as int[],
                                 [2,4,6,8] as int[]]
            underTest = new Day2(input)

        when:
            long result = underTest.getChecksum()

        then:
            result == 18
    }

    def "successfully return answer for second half"() {
        given:
            List<int[]> input = [[5,9,2,8] as int[],
                                 [9,4,7,3] as int[],
                                 [3,8,6,5] as int[]]
            underTest = new Day2(input)

        when:
            long result = underTest.getDivisibleSum()

        then:
            result == 9
    }
}
