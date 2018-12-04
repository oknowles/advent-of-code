package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.Utils
import com.oliver.adventofcode.year2017.Day11
import spock.lang.Specification
import spock.lang.Unroll


class Day11Test extends Specification {

    private Day11 underTest

    def "run day 11"() {
        given:
            underTest = new Day11()

        when:
            Day11.Result result = underTest.getFewestSteps(Arrays.asList(Utils.readFile(11)[0].split(",")))

        then:
            println "first half = ${result.getShortestSteps()}"
            println "second half = ${result.getMaxSteps()}"
    }

    @Unroll
    def "return fewest number of steps successfully"() {
        given:
            underTest = new Day11()

        when:
            int result = underTest.getFewestSteps(input).getShortestSteps()

        then:
            result == expectedResult

        where:
            input                          | expectedResult
            ["ne", "ne", "ne"]             | 3
            ["ne", "ne", "sw", "sw"]       | 0
            ["ne", "ne", "s", "s"]         | 2
            ["se", "sw", "se", "sw", "sw"] | 3
    }
}
