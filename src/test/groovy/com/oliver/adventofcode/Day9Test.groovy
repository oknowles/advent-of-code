package com.oliver.adventofcode

import spock.lang.Specification
import spock.lang.Unroll


class Day9Test extends Specification {

    private Day9 underTest

    def setup() {
        underTest = new Day9()
    }

    def "run day 9"() {
        when:
            Day9.Result result = underTest.getScore(Utils.readFile(9)[0])

        then:
            println "first half = ${result.getScore()}"
            println "second half = ${result.getCharsInGarbage()}"
    }

    @Unroll
    def "successfully return score for input #input"() {
        when:
            Day9.Result result = underTest.getScore(input)

        then:
            result.getScore() == expectedResult

        where:
            input                           | expectedResult
            "{}"                            | 1
            "{{{}}}"                        | 6
            "{{},{}}"                       | 5
            "{{{},{},{{}}}}"                | 16
            "{<a>,<a>,<a>,<a>}"             | 1
            "{{<ab>},{<ab>},{<ab>},{<ab>}}" | 9
            "{{<!!>},{<!!>},{<!!>},{<!!>}}" | 9
            "{{<a!>},{<a!>},{<a!>},{<ab>}}" | 3
    }

    @Unroll
    def "successfully return garbage count for input #input"() {
        when:
            Day9.Result result = underTest.getScore(input)

        then:
            result.getCharsInGarbage() == expectedResult

        where:
            input                 | expectedResult
            "<>"                  | 0
            "<random characters>" | 17
            "<<<<>"               | 3
            "<{!>}>"              | 2
            "<!!>"                | 0
            "<!!!>>"              | 0
            "<{o'i!a,<{i<a>"      | 10
    }
}
