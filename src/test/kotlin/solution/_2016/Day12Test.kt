package solution._2016

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 12")
class Day12Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2016/day12/day12.sample1").readLines()
            val result = Day12(input).solve1()

            assertThat(result).isEqualTo(0L)
        }

        @ParameterizedTest
        @CsvSource(
            "0;0",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day12(listOf(input)).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day12/day12.input").readLines()
            val result = Day12(input).solve1()

            println("Day 12 part 1: $result")

            // assertThat(result).isEqualTo(0L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2016/day12/day12.sample1").readLines()
            val result = Day12(input).solve2()

            assertThat(result).isEqualTo(0L)
        }

        @ParameterizedTest
        @CsvSource(
            "0;0",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day12(listOf(input)).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day12/day12.input").readLines()
            val result = Day12(input).solve2()

            println("Day 12 part 2: $result")

            // assertThat(result).isEqualTo(0L)
        }
    }

}