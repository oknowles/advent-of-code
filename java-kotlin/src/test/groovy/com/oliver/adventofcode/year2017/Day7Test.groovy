package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.year2017.Day7
import spock.lang.Specification


class Day7Test extends Specification {

    private static final List<String> TEST_INPUT = [
            "pbga (66)",
            "xhth (57)",
            "ebii (61)",
            "havc (66)",
            "ktlj (57)",
            "fwft (72) -> ktlj, cntj, xhth",
            "qoyq (66)",
            "padx (45) -> pbga, havc, qoyq",
            "tknk (41) -> ugml, padx, fwft",
            "jptl (61)",
            "ugml (68) -> gyxo, ebii, jptl",
            "gyxo (61)",
            "cntj (57)"
    ]

    private Day7 underTest

    def "run day 7"() {
        given:
            underTest = Day7.fromInput()

        when:
            String firstHalf = underTest.getBottomProgram()
            int secondHalf = underTest.getCorrectWeight()

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return bottom program"() {
        given:
            underTest = new Day7(TEST_INPUT)

        when:
            String result = underTest.getBottomProgram()

        then:
            result == "tknk"
    }

    def "successfully return correct weight for unbalanced program"() {
        given:
            underTest = new Day7(TEST_INPUT)

        when:
            int result = underTest.getCorrectWeight()

        then:
            result == 60
    }
}
