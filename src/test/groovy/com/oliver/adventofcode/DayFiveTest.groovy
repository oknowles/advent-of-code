package com.oliver.adventofcode

import spock.lang.Specification


class DayFiveTest extends Specification {

    private DayFive underTest

    def "run day five"() {
        given:
            underTest = DayFive.fromInput()

        when:
            int firstHalf = underTest.getFirstHalfJumps()
            int secondHalf = underTest.getSecondHalfJumps()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return number of jumps for first half"() {
        given:
            underTest = new DayFive([0, 3, 0, 1, -3] as int[])

        when:
            int result = underTest.getFirstHalfJumps()

        then:
            result == 5
    }

    def "successfully return number of jumps for second half"() {
        given:
            underTest = new DayFive([0, 3, 0, 1, -3] as int[])

        when:
            int result = underTest.getSecondHalfJumps()

        then:
            result == 10
    }
}
