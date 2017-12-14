package com.oliver.adventofcode

import spock.lang.Specification


class Day14Test extends Specification {

    private Day14 underTest

    def "run day 14"() {
        given:
            underTest = new Day14("jxqlasbh")

        when:
            int firstHalf = underTest.getUsedSquares()

        then:
            println "first half = $firstHalf"
    }

    def "successfully return number used squares"() {
        given:
            underTest = new Day14("flqrgnkx")

        when:
            int result = underTest.getUsedSquares()

        then:
            result == 8108
    }

    def "successfully return region count"() {
        given:
            underTest = new Day14("flqrgnkx")

        when:
            int result = underTest.getRegionCount()

        then:
            result == 8
    }
}
