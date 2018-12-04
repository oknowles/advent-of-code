package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.Utils
import spock.lang.Specification

class Day8Test extends Specification {

    private static List<String> TEST_INPUT = [
            "b inc 5 if a > 1",
            "a inc 1 if b < 5",
            "c dec -10 if a >= 1",
            "c inc -20 if c == 10"
    ]

    private Day8 underTest

    def "run day 8"() {
        given:
            underTest = new Day8()

        when:
            int secondHalf = underTest.loadInstructions(Utils.readFile(8))
            int firstHalf = underTest.getLargestRegisterValue()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return largest register value"() {
        given:
            underTest = new Day8()
            underTest.loadInstructions(TEST_INPUT)

        when:
            int result = underTest.getLargestRegisterValue()

        then:
            result == 1
    }

    def "successfully return largest register value while loading instructions"() {
        given:
            underTest = new Day8()

        when:
            int result = underTest.loadInstructions(TEST_INPUT)

        then:
            result == 10
    }
}
