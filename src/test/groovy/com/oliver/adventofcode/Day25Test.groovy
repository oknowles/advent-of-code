package com.oliver.adventofcode

import spock.lang.Specification


class Day25Test extends Specification {

    private Day25 underTest

    def "run day 25"() {
        given:
            underTest = new Day25(Utils.readFile(25))

        when:
            int firstHalf = underTest.getDiagnosticChecksum()

        then:
            println "first half = $firstHalf"
    }

    def "successfully return diagnostic checksum"() {
        given:
            underTest = new Day25([
                    "Begin in state A.",
                    "Perform a diagnostic checksum after 6 steps.",
                    "",
                    "In state A:",
                    "  If the current value is 0:",
                    "    - Write the value 1.",
                    "    - Move one slot to the right.",
                    "    - Continue with state B.",
                    "  If the current value is 1:",
                    "    - Write the value 0.",
                    "    - Move one slot to the left.",
                    "    - Continue with state B.",
                    "",
                    "In state B:",
                    "  If the current value is 0:",
                    "    - Write the value 1.",
                    "    - Move one slot to the left.",
                    "    - Continue with state A.",
                    "  If the current value is 1:",
                    "    - Write the value 1.",
                    "    - Move one slot to the right.",
                    "    - Continue with state A.",
            ])

        when:
            int result = underTest.getDiagnosticChecksum()

        then:
            result == 3
    }
}
