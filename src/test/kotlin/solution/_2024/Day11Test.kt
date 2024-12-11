package solution._2024

import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 11")
class Day11Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @ParameterizedTest
        @CsvSource(
            "125 17;6;22",
            "125 17;25;55312",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, blinks: Int, output: Long) {
            val result = Day11(input).solve(blinks)

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day11/day11.input").readLines().first()
            val result = Day11(input).solve(25)

            println("Day 11 part 1: $result")

            assertThat(result).isEqualTo(233875L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day11/day11.input").readLines().first()
            val result = Day11(input).solve(75)

            println("Day 11 part 2: $result")

            assertThat(result).isEqualTo(277444936413293L)
        }
    }
}