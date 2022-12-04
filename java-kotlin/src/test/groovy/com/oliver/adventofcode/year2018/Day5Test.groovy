package com.oliver.adventofcode.year2018

import spock.lang.Specification

class Day5Test extends Specification {

    private Day5 underTest

    def "run day 5"() {
        given:
            underTest = Day5.fromInput()

        when:
            int firstHalf = underTest.getUnitCountAfterFolding()
            int secondHalf = underTest.findSmallestPolymer()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "correctly return answer for part one"() {
        given:
            underTest = new Day5("dabAcCaCBAcCcaDA")

        when:
            String result = underTest.collapsePolymer()

        then:
            result == "dabCBAcaDA"
    }

    def "correctly return answer for part twop"() {
        given:
            underTest = new Day5("dabAcCaCBAcCcaDA")

        when:
            int result = underTest.findSmallestPolymer()

        then:
            result == 4
    }
}
