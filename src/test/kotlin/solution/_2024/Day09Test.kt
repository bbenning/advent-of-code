package solution._2024

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File
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

        @ParameterizedTest
        @CsvSource(
            "2333133121414131402;1928",
            "233313312141413140256;3383",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day09(input).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day09/day09.input").readLines().first()
            val result = Day09(input).solve1()

            println("Day 09 part 1: $result")

            assertThat(result).isEqualTo(6201130364722L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @ParameterizedTest
        @CsvSource(
            "2333133121414131402;2858",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day09(input).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day09/day09.input").readLines().first()
            val result = Day09(input).solve2()

            println("Day 09 part 2: $result")

            assertThat(result).isEqualTo(6221662795602L)
        }
    }

}