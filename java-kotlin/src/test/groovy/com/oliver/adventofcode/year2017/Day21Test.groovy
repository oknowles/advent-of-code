package com.oliver.adventofcode.year2017

import com.oliver.adventofcode.Utils
import spock.lang.Specification
import spock.lang.Unroll

class Day21Test extends Specification {
    
    private Day21 underTest

    @Unroll
    def "run day 21"() {
        given:
            underTest = new Day21(Utils.readFile(21))

        when:
            underTest.runIterations(iterations)
            int result = underTest.getNumberElementsOn()

        then:
            println "$description = $result"

        where:
            description   | iterations
            "first half"  | 5
            "second half" | 18
    }
    
    def "successfully return number of pixels on"() {
        given:
            underTest = new Day21(["../.# => ##./#../...",
                                   ".#./..#/### => #..#/..../..../#..#"])

        when:
            underTest.runIterations(2)
            int result = underTest.getNumberElementsOn()

        then:
            result == 12
    }
}
