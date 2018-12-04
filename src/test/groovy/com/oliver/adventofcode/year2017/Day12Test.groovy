package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.Utils
import com.oliver.adventofcode.year2017.Day12
import spock.lang.Specification


class Day12Test extends Specification {

    public static final List<String> TEST_INPUT = [
            "0 <-> 2",
            "1 <-> 1",
            "2 <-> 0, 3, 4",
            "3 <-> 2, 4",
            "4 <-> 2, 3, 6",
            "5 <-> 6",
            "6 <-> 4, 5"
    ]

    private Day12 underTest

    def "run day 12"() {
        given:
            underTest = new Day12(Utils.readFile(12))

        when:
            int firstHalf = underTest.getGroupSize(0)
            int secondHalf = underTest.getNumberOfGroups()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return number of connections for program 0"() {
        given:
            underTest = new Day12(TEST_INPUT)

        when:
            int result = underTest.getGroupSize(0)

        then:
            result == 6
    }

    def "successfully return number of groups"() {
        given:
            underTest = new Day12(TEST_INPUT)

        when:
            int result = underTest.getNumberOfGroups()

        then:
            result == 2
    }
}
