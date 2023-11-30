package solution._2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@DisplayName("Day 06")
class Day06Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @ParameterizedTest
        @CsvSource(
            "turn on 0,0 through 999,999; 1000000",
            "toggle 0,0 through 999,0; 1000",
            "turn off 499,499 through 500,500; 0",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Int) {
            val result = Day06(input.lines()).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun sample1() {
            val input = File("./src/test/resources/2015/day06/day06.sample1").readLines()
            val result = Day06(input).solve1()

            assertThat(result).isEqualTo(999000)
        }

        @Test
        fun sample2() {
            val input = File("./src/test/resources/2015/day06/day06.sample2").readLines()
            val result = Day06(input).solve1()

            assertThat(result).isEqualTo(998996)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day06/day06.input").readLines()
            val result = Day06(input).solve1()

            println("Day 06 part 1: $result")

            assertThat(result).isEqualTo(543903)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @ParameterizedTest
        @CsvSource(
            "turn on 0,0 through 0,0;1",
            "toggle 0,0 through 999,999;2000000",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Int) {
            val result = Day06(listOf(input)).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day06/day06.input").readLines()
            val result = Day06(input).solve2()

            println("Day 06 part 2: $result")

            assertThat(result).isEqualTo(14687245)
        }
    }

}