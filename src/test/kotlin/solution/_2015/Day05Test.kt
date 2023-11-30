package solution._2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@DisplayName("Day 05")
class Day05Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @ParameterizedTest
        @CsvSource(
            "ugknbfddgicrmopn,1",
            "aaa, 1",
            "jchzalrnumimnmhp, 0",
            "haegwjzuvuyypxyu, 0",
            "dvszwmarrgswjxmb, 0"
        )
        fun sample1Parameterized(input: String, output: Int) {
            val result = Day05(listOf(input)).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day05/day05.input").readLines()
            val result = Day05(input).solve1()

            println("Day 05 part 1: $result")

            assertThat(result).isEqualTo(238)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @ParameterizedTest
        @CsvSource(
            "qjhvhtzxzqqjkmpb, 1",
            "xxyxx, 1",
            "uurcxstgmygtbstg, 0",
            "ieodomkazucvgmuy, 0"
        )
        fun sample1Parameterized(input: String, output: Int) {
            val result = Day05(listOf(input)).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day05/day05.input").readLines()
            val result = Day05(input).solve2()

            println("Day 05 part 2: $result")

            assertThat(result).isEqualTo(69)
        }
    }

}