package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.year2017.Day14
import spock.lang.Specification


class Day14Test extends Specification {

    private Day14 underTest

    def "run day 14"() {
        given:
            underTest = new Day14("jxqlasbh")

        when:
            int firstHalf = underTest.getUsedSquares()
            int secondHalf = underTest.getRegionCount()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
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
            result == 1242
    }
}
