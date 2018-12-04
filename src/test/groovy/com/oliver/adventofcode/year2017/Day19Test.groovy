package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.Utils
import spock.lang.Specification

class Day19Test extends Specification {

    private Day19 underTest

    def "run day 19"() {
        given:
            underTest = new Day19(Utils.readFile(19))

        when:
            Day19.Result result = underTest.walkPath()

        then:
            println "first half = ${result.getPathLetters()}"
            println "second half = ${result.getPathLength()}"
    }

    def "successfully return result from test input"() {
        given:
            underTest = new Day19(["     |          ",
                                   "     |  +--+    ",
                                   "     A  |  C    ",
                                   " F---|----E|--+ ",
                                   "     |  |  |  D ",
                                   "     +B-+  +--+ "])

        when:
            Day19.Result result = underTest.walkPath()

        then:
            result.getPathLetters() == "ABCDEF"
            result.getPathLength() == 38
    }
}
