package com.oliver.adventofcode

import spock.lang.Specification


class Day20Test extends Specification {

    private Day20 underTest

    def "run day 20"() {
        given:
            underTest = new Day20(Utils.readFile(20))

        when:
            int firstHalf = underTest.getClosestParticle()

        then:
            println "first half = $firstHalf"
    }

    def "successfully return particle closest to zero"() {
        given:
            underTest = new Day20([
                    "p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>",
                    "p=< 4,0,0>, v=< 0,0,0>, a=<-2,0,0>"])

        when:
            int result = underTest.getClosestParticle()

        then:
            result == 0
    }
}
