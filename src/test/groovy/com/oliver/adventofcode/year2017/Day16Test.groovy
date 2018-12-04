package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.Utils
import com.oliver.adventofcode.year2017.Day16
import spock.lang.Specification
import spock.lang.Unroll


class Day16Test extends Specification {

    private static final List<String> INPUT = Arrays.asList(Utils.readFile(16)[0].split(","))

    private Day16 underTest

    def "run day 16 first half"() {
        given:
            underTest = new Day16(16)
            underTest.executeInstructions(INPUT, 1)

        when:
            String firstHalf = underTest.getProgramOrderString()

        then:
            println "first half = $firstHalf"
    }

    def "run day 16 second half"() {
        given:
            underTest = new Day16(16)
            underTest.executeInstructions(INPUT, 999_999_999)

        when:
            String secondHalf = underTest.getProgramOrderString()

        then:
            println "second half = $secondHalf"
    }

    @Unroll
    def "successfully return final order after #numIterations iterations of instructions"() {
        given:
            underTest = new Day16( 5)
            underTest.executeInstructions(["s1", "x3/4", "pe/b"], numIterations)

        when:
            String result = underTest.getProgramOrderString()

        then:
            result == expectedResult

        where:
            numIterations | expectedResult
            1             | "baedc"
            2             | "ceadb"
    }
}
