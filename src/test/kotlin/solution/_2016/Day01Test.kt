package solution._2016

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 01")
class Day01Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @ParameterizedTest
        @CsvSource(
            "R2, L3;5",
            "R2, R2, R2;2",
            "R5, L5, R5, R3;12",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Int) {
            val result = Day01(input).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day01/day01.input").readLines().first()
            val result = Day01(input).solve1()

            println("Day 01 part 1: $result")

            assertThat(result).isEqualTo(301)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @ParameterizedTest
        @CsvSource(
            "R8, R4, R4, R8;4",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Int) {
            val result = Day01(input).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day01/day01.input").readLines().first()
            val result = Day01(input).solve2()

            println("Day 01 part 2: $result")

            assertThat(result).isEqualTo(130)
        }
    }

}