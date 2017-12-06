package com.oliver.adventofcode

import spock.lang.Specification


class Day6Test extends Specification {

    private Day6 underTest

    def "run day 6"() {
        given:
            underTest = Day6.fromInput()

        when:
            int firstHalf = underTest.getTotalRedistributionCycles()
            int secondHalf = underTest.getLoopRedistributionCycles()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return total redistribution cycles"() {
        given:
            underTest = new Day6([0,2,7,0])

        when:
            int result = underTest.getTotalRedistributionCycles()

        then:
            result == 5
    }

    def "successfully return loop redistribution cycles"() {
        given:
            underTest = new Day6([0,2,7,0])

        when:
            int result = underTest.getLoopRedistributionCycles()

        then:
            result == 4
    }
}
