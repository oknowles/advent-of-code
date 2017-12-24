package com.oliver.adventofcode

import spock.lang.Specification


class Day24Test extends Specification {

    private Day24 underTest

    def "run day 24"() {
        given:
            underTest = new Day24(Utils.readFile(24))

        when:
            int firstHalf = underTest.getStrongestBridge()
            int secondHalf = underTest.getLongestBridgeStrength()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return strongest bridge"() {
        given:
            underTest = new Day24(["0/2", "2/2", "2/3", "3/4", "3/5", "0/1", "10/1", "9/10"])

        when:
            int result = underTest.getStrongestBridge()

        then:
            result == 31
    }

    def "successfully return longest bridge strength"() {
        given:
            underTest = new Day24(["0/2", "2/2", "2/3", "3/4", "3/5", "0/1", "10/1", "9/10"])

        when:
            int result = underTest.getLongestBridgeStrength()

        then:
            result == 19

    }
}
