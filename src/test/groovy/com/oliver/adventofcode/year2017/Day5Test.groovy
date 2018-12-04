package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.year2017.Day5
import spock.lang.Specification


class Day5Test extends Specification {

    private Day5 underTest

    def "run day 5"() {
        given:
            underTest = Day5.fromInput()

        when:
            int firstHalf = underTest.getFirstHalfJumps()
            int secondHalf = underTest.getSecondHalfJumps()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return number of jumps for first half"() {
        given:
            underTest = new Day5([0, 3, 0, 1, -3] as int[])

        when:
            int result = underTest.getFirstHalfJumps()

        then:
            result == 5
    }

    def "successfully return number of jumps for second half"() {
        given:
            underTest = new Day5([0, 3, 0, 1, -3] as int[])

        when:
            int result = underTest.getSecondHalfJumps()

        then:
            result == 10
    }
}
