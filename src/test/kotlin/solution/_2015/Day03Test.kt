package solution._2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@DisplayName("Day 03")
class Day03Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @ParameterizedTest
        @CsvSource(
            ">, 2",
            "^>v<, 4",
            "^v^v^v^v^v, 2")
        fun sample1(input: String, output: Int) {
            val result = Day03(listOf(input)).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day03/day03.input").readLines()
            val result = Day03(input).solve1()

            println("Day 03 part 1: $result")

            assertThat(result).isEqualTo(2572)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @ParameterizedTest
        @CsvSource(
            "^v, 3",
            "^>v<, 3",
            "^v^v^v^v^v, 11")
        fun sample1(input: String, output: Int) {
            val result = Day03(listOf(input)).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day03/day03.input").readLines()
            val result = Day03(input).solve2()

            println("Day 03 part 2: $result")

            assertThat(result).isEqualTo(2631)
        }
    }

}