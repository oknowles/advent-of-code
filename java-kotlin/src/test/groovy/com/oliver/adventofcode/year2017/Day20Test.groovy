package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.Utils
import spock.lang.Specification

class Day20Test extends Specification {

    private static final int ITERATIONS = 1_000

    private Day20 underTest

    def "run day 20 part 1"() {
        given:
            underTest = new Day20(Utils.readFile(20))
            underTest.runIterations(ITERATIONS, false)

        when:
            int firstHalf = underTest.getClosestParticle()

        then:
            println "first half = $firstHalf"
    }

    def "run day 20 part 2"() {
        given:
            underTest = new Day20(Utils.readFile(20))
            underTest.runIterations(ITERATIONS, true)

        when:
            int secondHalf = underTest.getRemainingParticles()

        then:
            println "second half = $secondHalf"
    }

    def "successfully return particle closest to zero"() {
        given:
            underTest = new Day20([
                    "p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>",
                    "p=< 4,0,0>, v=< 0,0,0>, a=<-2,0,0>"])
            underTest.runIterations(ITERATIONS, false)

        when:
            int result = underTest.getClosestParticle()

        then:
            result == 0
    }

    def "successfully return number of remaining particles"() {
        given:
            underTest = new Day20([
                    "p=<-6,0,0>, v=< 3,0,0>, a=< 0,0,0>",
                    "p=<-4,0,0>, v=< 2,0,0>, a=< 0,0,0>",
                    "p=<-2,0,0>, v=< 1,0,0>, a=< 0,0,0>",
                    "p=< 3,0,0>, v=<-1,0,0>, a=< 0,0,0>"])
            underTest.runIterations(ITERATIONS, true)

        when:
            int result = underTest.getRemainingParticles()

        then:
            result == 1

    }
}
