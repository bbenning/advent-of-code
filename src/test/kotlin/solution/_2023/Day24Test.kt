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

@DisplayName("Day 24")
class Day24Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day24/day24.sample1").readLines()
            val result = Day24(input).solve1(7L, 27L)

            assertThat(result).isEqualTo(2)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day24/day24.input").readLines()
            val result = Day24(input).solve1(200000000000000L, 400000000000000L)

            println("Day 24 part 1: $result")

            // assertThat(result).isEqualTo(0L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day24/day24.sample1").readLines()
            val result = Day24(input).solve2()

            assertThat(result).isEqualTo(0L)
        }

        @ParameterizedTest
        @CsvSource(
            "0;0",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day24(listOf(input)).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day24/day24.input").readLines()
            val result = Day24(input).solve2()

            println("Day 24 part 2: $result")

            // assertThat(result).isEqualTo(0L)
        }
    }

}