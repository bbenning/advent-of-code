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

@DisplayName("Day 02")
class Day02Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2016/day02/day02.sample1").readLines()
            val result = Day02(input).solve1()

            assertThat(result).isEqualTo("1985")
        }

        @ParameterizedTest
        @CsvSource(
            "ULL;1",
            "RRDDD;9",
            "LURDL;4",
            "UUUUD;5",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: String) {
            val result = Day02(listOf(input)).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day02/day02.input").readLines()
            val result = Day02(input).solve1()

            println("Day 02 part 1: $result")

            assertThat(result).isEqualTo("18843")
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2016/day02/day02.sample1").readLines()
            val result = Day02(input).solve2()

            assertThat(result).isEqualTo("5DB3")
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day02/day02.input").readLines()
            val result = Day02(input).solve2()

            println("Day 02 part 2: $result")

            // assertThat(result).isEqualTo(0)
        }
    }

}