package com.oliver.adventofcode

import spock.lang.Specification
import spock.lang.Unroll

class UtilsTest extends Specification {

    def "string successfully transformed into single digit int array"() {
        when:
            int[] result = Utils.stringToSingleDigitArray("123456")
            int[] expected = [1, 2, 3, 4, 5, 6]

        then:
            result == expected
    }

    @Unroll
    def "string successfully transformed into int array"() {
        when:
            int[] result = Utils.stringToArray(input, separatorRegex)

        then:
            result == expected as int[]

        where:
            input              | separatorRegex | expected
            "123456"           | "4"            | [123, 56]
            "1\t2\t3\t4\t5\t6" | "\t"           | [1, 2, 3, 4, 5, 6]
    }
}
