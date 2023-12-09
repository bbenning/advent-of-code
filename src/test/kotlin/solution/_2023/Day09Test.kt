package solution._2023

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 09")
class Day09Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day09/day09.sample1").readLines()
            val result = Day09(input).solve1()

            assertThat(result).isEqualTo(114L)
        }

        @ParameterizedTest
        @CsvSource(
            "0 3 6 9 12 15;18",
            "1 3 6 10 15 21;28",
            "10 13 16 21 30 45;68",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day09(listOf(input)).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day09/day09.input").readLines()
            val result = Day09(input).solve1()

            println("Day 09 part 1: $result")

            assertThat(result).isEqualTo(1868368343L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day09/day09.sample1").readLines()
            val result = Day09(input).solve2()

            assertThat(result).isEqualTo(2L)
        }

        @ParameterizedTest
        @CsvSource(
            "0 3 6 9 12 15;-3",
            "1 3 6 10 15 21;0",
            "10 13 16 21 30 45;5",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day09(listOf(input)).solve2()

            assertThat(result).isEqualTo(output)
        }


        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day09/day09.input").readLines()
            val result = Day09(input).solve2()

            println("Day 09 part 2: $result")

            assertThat(result).isEqualTo(1022L)
        }
    }

}