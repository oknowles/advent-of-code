package com.oliver.adventofcode.year2018

import spock.lang.Specification

class Day3Test extends Specification {

    private Day3 underTest

    def "run day 3"() {
        given:
            underTest = Day3.fromInput()

        when:
            int firstHalf = underTest.squaresWithAtLeastNCounts(2)
            int secondHalf = underTest.findNonOverlappingClaimID()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return number of squares claimed"() {
        given:
            underTest = new Day3(["#1 @ 1,3: 4x4",
                                  "#2 @ 3,1: 4x4",
                                  "#3 @ 5,5: 2x2"])

        when:
            int result = underTest.squaresWithAtLeastNCounts(2)

        then:
            result == 4
    }

    def "successfully return non-overlapping claim ID"() {
        given:
            underTest = new Day3(["#1 @ 1,3: 4x4",
                                  "#2 @ 3,1: 4x4",
                                  "#3 @ 5,5: 2x2"])

        when:
            int result = underTest.findNonOverlappingClaimID()

        then:
            result == 3
    }
}
