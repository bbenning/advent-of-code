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

@DisplayName("Day 05")
class Day05Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @ParameterizedTest
        @CsvSource(
            "abc;18f47a30",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: String) {
            val result = Day05(input).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day05/day05.input").readLines().first()
            val result = Day05(input).solve1()

            println("Day 05 part 1: $result")

            assertThat(result).isEqualTo("1a3099aa")
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @ParameterizedTest
        @CsvSource(
            "abc;05ace8e3",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: String) {
            val result = Day05(input).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day05/day05.input").readLines().first()
            val result = Day05(input).solve2()

            println("Day 05 part 2: $result")

            assertThat(result).isEqualTo("694190cd")
        }
    }
}