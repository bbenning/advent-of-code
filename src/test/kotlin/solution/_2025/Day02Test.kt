package solution._2025

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File
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

        @ParameterizedTest
        @CsvSource(
            "9-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                    "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                    "824824821-824824827,2121212118-2121212124;1227775554",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day02(input).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2025/day02/day02.input").readLines().first()
            val result = Day02(input).solve1()

            println("Day 02 part 1: $result")
            assertThat(result).isEqualTo(12850231731L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @ParameterizedTest
        @CsvSource(
            "9-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                    "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                    "824824821-824824827,2121212118-2121212124;4174379265",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day02(input).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2025/day02/day02.input").readLines().first()
            val result = Day02(input).solve2()

            println("Day 02 part 2: $result")

            assertThat(result).isEqualTo(24774350322L)
        }
    }

}