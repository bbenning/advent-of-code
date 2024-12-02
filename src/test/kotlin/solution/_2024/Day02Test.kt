package solution._2024

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 02")
class Day02Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day02/day02.sample1").readLines()
            val result = Day02(input).solve1()

            assertThat(result).isEqualTo(2L)
        }

        @ParameterizedTest
        @CsvSource(
            "1 2 3;1",
            "3 2 1;1",
            "3 1 2;0",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day02(listOf(input)).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day02/day02.input").readLines()
            val result = Day02(input).solve1()

            println("Day 02 part 1: $result")

            assertThat(result).isEqualTo(660L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day02/day02.sample1").readLines()
            val result = Day02(input).solve2()

            assertThat(result).isEqualTo(4L)
        }

        @ParameterizedTest
        @CsvSource(
            "1 2 3 4;1",
            "4 3 2 1;1",
            "4 2 3 1;1",
            "1 2 6 3;1",
            "1 2 6 10 3;0",
            "27 29 30 33 34 35 37 35;1",
            "35 27 29 30 33 34 35 37;1",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day02(listOf(input)).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day02/day02.input").readLines()
            val result = Day02(input).solve2()

            println("Day 02 part 2: $result")

            assertThat(result).isEqualTo(689L)
        }
    }

}