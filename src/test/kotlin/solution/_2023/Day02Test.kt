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

@DisplayName("Day 02")
class Day02Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day02/day02.sample1").readLines()
            val result = Day02(input).solve1()

            assertThat(result).isEqualTo(8)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day02/day02.input").readLines()
            val result = Day02(input).solve1()

            println("Day 02 part 1: $result")

            assertThat(result).isEqualTo(2600)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @ParameterizedTest
        @CsvSource(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green|48",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue|12",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red|1560",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red|630",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green|36",
            delimiter = '|'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day02(listOf(input)).solve2()

            assertThat(result).isEqualTo(output)
        }

        //24572 wrong
        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day02/day02.input").readLines()
            val result = Day02(input).solve2()

            println("Day 02 part 2: $result")

            assertThat(result).isEqualTo(86036)
        }
    }

}