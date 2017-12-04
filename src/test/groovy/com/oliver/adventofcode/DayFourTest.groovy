package com.oliver.adventofcode

import spock.lang.Specification
import spock.lang.Unroll


class DayFourTest extends Specification {

    private DayFour underTest

    def "run day four"() {
        given:
            underTest = DayFour.fromInput()

        when:
            long firstHalf = underTest.getUniquePassphraseCount()
            long secondHalf = underTest.getNonAnagramPassphraseCount()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    @Unroll
    def "successfully return valid input when input = #input"() {
        given:
            underTest = new DayFour([input as String[]])

        when:
            long result = underTest.getUniquePassphraseCount()

        then:
            result == expectedResult

        where:
            input                           | expectedResult
            ["aa", "bb", "cc", "dd", "ee"]  | 1
            ["aa", "bb", "cc", "dd", "aa"]  | 0
            ["aa", "bb", "cc", "dd", "aaa"] | 1
    }


    @Unroll
    def "successfully return #expectedResult for non anagram count when = #input"() {
        given:
            underTest = new DayFour([input as String[]])

        when:
            long result = underTest.getNonAnagramPassphraseCount()

        then:
            result == expectedResult

        where:
            input                                    | expectedResult
            ["abcdef", "fghij"]                      | 1
            ["abcde", "xyz", "ecdab"]                | 0
            ["a", "ab", "abc", "abd", "abf", "abj"]  | 1
            ["iiii", "oiii", "ooii", "oooi", "oooo"] | 1
            ["iiii", "oiii", "ooii", "oooi", "oooo"] | 1
            ["oiii", "ioii", "iioi", "iiio"]         | 0
    }
}
