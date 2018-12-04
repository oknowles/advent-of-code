package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.Utils
import com.oliver.adventofcode.year2017.Day13
import spock.lang.Specification


class Day13Test extends Specification {

    public static final List<String> TEST_INPUT = ["0: 3", "1: 2", "4: 4", "6: 4"]

    private Day13 underTest

    def "run day 13"() {
        given:
            underTest = new Day13(Utils.readFile(13))

        when:
            int firstHalf = underTest.getSeverityOfTrip()
            int secondHalf = underTest.getUndetectedDelay()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return severity of trip"() {
        given:
            underTest = new Day13(TEST_INPUT)

        when:
            int result = underTest.getSeverityOfTrip()

        then:
            result == 24
    }

    def "successfully return minimum delay to go undetected"() {
        given:
            underTest = new Day13(TEST_INPUT)

        when:
            int result = underTest.getUndetectedDelay()

        then:
            result == 10
    }
}
