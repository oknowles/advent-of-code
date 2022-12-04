package com.oliver.adventofcode.year2018

import spock.lang.Specification

class Day2Test extends Specification {

    private Day2 underTest

    def "run day 2"() {
        given:
            underTest = Day2.fromInput()

        when:
            int firstHalf = underTest.getChecksum()
            String secondHalf = underTest.getCommonLetters()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return answer for first half"() {
        given:
            underTest = new Day2(["abcdef","bababc","abbcde","abcccd","aabcdd","abcdee","ababab"])

        when:
            int result = underTest.getChecksum()

        then:
            result == 12
    }

    def "successfully return answer for second half"() {
        given:
            underTest = new Day2(["abcde","fghij","klmno","pqrst","fguij","axcye","wvxyz"])

        when:
            String result = underTest.getCommonLetters()

        then:
            result == "fgij"
    }
}
