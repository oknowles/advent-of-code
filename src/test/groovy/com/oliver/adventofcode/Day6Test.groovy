package com.oliver.adventofcode

import spock.lang.Specification


class Day6Test extends Specification {

    private Day6 underTest

    def "successfully return redistribution cycles"() {
        given:
            underTest = new Day6([0,2,7,0])

        when:
            int result = underTest.getRedistributionCycles()

        then:
            result == 5
    }
}
