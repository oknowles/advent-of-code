package com.oliver.adventofcode

import spock.lang.Specification

class UtilsTest extends Specification {

    def "string successfully transformed into int array"() {
        when:
            int[] result = Utils.stringToIntArray("123456")
            int[] expected = [1, 2, 3, 4, 5, 6]

        then:
            result == expected
    }
}
