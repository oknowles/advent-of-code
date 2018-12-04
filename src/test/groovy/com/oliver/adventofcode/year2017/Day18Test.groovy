package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.Utils
import com.oliver.adventofcode.year2017.Day18
import spock.lang.Specification


class Day18Test extends Specification {

    private Day18 underTest

    def "run day 18"() {
        given:
            underTest = new Day18()

        when:
            long firstHalf = underTest.getFirstPositiveRecoveredSound(Utils.readFile(18))
            long secondHalf = underTest.runParallelPrograms(Utils.readFile(18))

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }

    def "successfully return first non zero recovered sound"() {
        given:
            underTest = new Day18()

        when:
            long result = underTest.getFirstPositiveRecoveredSound(["set a 1",
                                                      "add a 2",
                                                      "mul a a",
                                                      "mod a 5",
                                                      "snd a",
                                                      "set a 0",
                                                      "rcv a",
                                                      "jgz a -1",
                                                      "set a 1",
                                                      "jgz a -2"])

        then:
            result == 4
    }

    def "successfully return number of times program 1 sends value"() {
        given:
            underTest = new Day18()

        when:
            int result = underTest.runParallelPrograms(["snd 1",
                                                        "snd 2",
                                                        "snd p",
                                                        "rcv a",
                                                        "rcv b",
                                                        "rcv c",
                                                        "rcv d"])

        then:
            result == 3
    }
}
