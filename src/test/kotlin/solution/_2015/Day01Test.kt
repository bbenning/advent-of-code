package solution._2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@DisplayName("Day 01")
class Day01Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @ParameterizedTest
        @CsvSource(
            "(()), 0",
            "()(), 0",
            "(((, 3",
            "(()(()(, 3",
            "))(((((, 3",
            "()), -1",
            "))(, -1",
            "))), -3",
            ")())()), -3"
        )
        fun samples(input: String, expected: Int) {
            val result = Day01(listOf(input)).solve1()

            assertThat(result).isEqualTo(expected)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day01/day01.input").readLines()
            val result = Day01(input).solve1()

            println("Day 01 part 1: $result")

             assertThat(result).isEqualTo(74)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @ParameterizedTest
        @CsvSource("), 1", "()()), 5")
        fun samples(input: String, expected: Int) {
            val result = Day01(listOf(input)).solve2()

            assertThat(result).isEqualTo(expected)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day01/day01.input").readLines()
            val result = Day01(input).solve2()

            println("Day 01 part 2: $result")

            assertThat(result).isEqualTo(1795)
        }
    }

}