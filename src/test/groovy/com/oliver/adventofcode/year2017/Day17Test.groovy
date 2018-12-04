package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.year2017.Day17
import spock.lang.Specification

class Day17Test extends Specification {

    private Day17 underTest

    def "run day 17"() {
        given:
            underTest = new Day17(359)

        when:
            int firstHalf = underTest.getValueAfter(2017)
            int secondHalf = underTest.getValueAfterZero(50_000_000)

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return value after inserted value"() {
        given:
            underTest = new Day17(3)

        when:
            int result = underTest.getValueAfter(2017)

        then:
            result == 638
    }

}
