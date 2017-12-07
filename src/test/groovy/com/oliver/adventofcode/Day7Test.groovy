package com.oliver.adventofcode

import spock.lang.Specification


class Day7Test extends Specification {

    private Day7 underTest

    def "run day 7"() {
        given:
            underTest = Day7.fromInput()

        when:
            String firstHalf = underTest.getBottomProgram()

        then:
            println "first half = $firstHalf"
    }

    def "successfully return bottom program"() {
        given:
            underTest = new Day7([
                    new Day7.Instruction("pbga", 66),
                    new Day7.Instruction("xhth", 57),
                    new Day7.Instruction("ebii", 61),
                    new Day7.Instruction("havc", 66),
                    new Day7.Instruction("ktlj", 57),
                    new Day7.Instruction("fwft", 72, ["ktlj", "cntj", "xhth"]),
                    new Day7.Instruction("qoyq", 66),
                    new Day7.Instruction("padx", 45, ["pbga", "havc", "qoyq"]),
                    new Day7.Instruction("tknk", 41, ["ugml", "padx", "fwft"]),
                    new Day7.Instruction("jptl", 61),
                    new Day7.Instruction("ugml", 68, ["gyxo", "ebii", "jptl"]),
                    new Day7.Instruction("gyxo", 61),
                    new Day7.Instruction("cntj", 57)])

        when:
            String result = underTest.getBottomProgram()

        then:
            result == "tknk"
    }
}
