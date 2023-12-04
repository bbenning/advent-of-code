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

@DisplayName("Day 04")
class Day04Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day04/day04.sample1").readLines()
            val result = Day04(input).solve1()

            assertThat(result).isEqualTo(13L)
        }

        @ParameterizedTest
        @CsvSource(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53;8",
                    "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19;2",
                    "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1;2",
                    "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83;1",
                    "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36;0",
                    "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11;0",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day04(listOf(input)).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day04/day04.input").readLines()
            val result = Day04(input).solve1()

            println("Day 04 part 1: $result")
            assertThat(result).isEqualTo(25651)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day04/day04.sample1").readLines()
            val result = Day04(input).solve2()

            assertThat(result).isEqualTo(30)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day04/day04.input").readLines()
            val result = Day04(input).solve2()

            println("Day 04 part 2: $result")

            assertThat(result).isEqualTo(19499881)
        }
    }

}