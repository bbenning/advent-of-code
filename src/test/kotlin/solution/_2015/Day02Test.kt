package solution._2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@DisplayName("Day 02")
class Day02Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @ParameterizedTest
        @CsvSource("2x3x4, 58", "1x1x10, 43")
        fun samples(input: String, answer: Int) {
            val result = Day02(listOf(input)).solve1()

            assertThat(result).isEqualTo(answer)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day02/day02.input").readLines()
            val result = Day02(input).solve1()

            println("Day 02 part 1: $result")

             assertThat(result).isEqualTo(1588178)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @ParameterizedTest
        @CsvSource("2x3x4, 34", "1x1x10, 14")
        fun samples(input: String, answer: Int) {
            val result = Day02(listOf(input)).solve2()

            assertThat(result).isEqualTo(answer)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day02/day02.input").readLines()
            val result = Day02(input).solve2()

            println("Day 02 part 2: $result")

            assertThat(result).isEqualTo(3783758)
        }
    }

}