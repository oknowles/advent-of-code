package com.oliver.adventofcode

import spock.lang.Specification


class Day23Test extends Specification {

    private Day23 underTest

    def "run day 23"() {
        given:
            underTest = new Day23()

        when:
            int firstHalf = underTest.getMultInvocations(Utils.readFile(23))
            int secondHalf = underTest.getNonPrimeCount(109300, 126300)

        then:
            println "first half = $firstHalf"
            println "second half = $secondHalf"
    }
}
