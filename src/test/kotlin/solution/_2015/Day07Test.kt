package solution._2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@DisplayName("Day 07")
class Day07Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2015/day07/day07.sample1").readLines()
            val result = Day07(input).runCircuit()

            val expected: Map<String, UShort> = "d: 72,e: 507,f: 492,g: 114,h: 65412,i: 65079,x: 123,y: 456".split(",").map {
                val splitAgain: List<String> = it.split(": ")
                Pair(splitAgain[0], splitAgain[1].toUShort())
            }.toMap()

            assertThat(result).isEqualTo(expected)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day07/day07.input").readLines()
            val result = Day07(input).solve()

            println("Day 07 part 1: $result")

            assertThat(result).isEqualTo(16076.toUShort())
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day07/day07.input2").readLines()
            val result = Day07(input).solve()

            println("Day 07 part 2: $result")

            assertThat(result).isEqualTo(2797.toUShort())
        }
    }

}