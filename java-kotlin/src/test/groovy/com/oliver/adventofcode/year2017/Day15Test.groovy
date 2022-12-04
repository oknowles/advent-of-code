package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.year2017.Day15
import spock.lang.Specification

class Day15Test extends Specification {

    private Day15 underTest

    def "run day 15"() {
        given:
            underTest = new Day15(873, 583)

        when:
            int firstHalf = underTest.matchingPairs(40_000_000)
            int secondHalf = underTest.matchingPairsWithCriteria(5_000_000)

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return number of matching pairs"() {
        given:
            underTest = new Day15(65, 8921)

        when:
            int result = underTest.matchingPairs(5)

        then:
            result == 1
    }

    def "successfully return number of matching pairs with criteria"() {
        given:
            underTest = new Day15(65, 8921)

        when:
            int result = underTest.matchingPairsWithCriteria(5)

        then:
            result == 0
    }
}
