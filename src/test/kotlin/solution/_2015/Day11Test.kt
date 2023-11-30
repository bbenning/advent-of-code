package solution._2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@DisplayName("Day 11")
class Day11Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @ParameterizedTest
        @CsvSource(
            "hijklmmn,false",
            "abbceffg, false",
            "abbcegjk, false",
            "abcdefgh, false",
            "abcdffaa, true",
            "ghijklmn, false",
            "ghjaabcc, true"
        )
        fun checkValidityOfPassword(input: String, output: Boolean) {
            val result = Day11(input).isValidPassword(input)

            assertThat(result).isEqualTo(output)
        }

        @ParameterizedTest
        @CsvSource(
            "abcdefgh, abcdffaa",
            "ghijklmn, ghjaabcc"
        )
        fun findNextPassword(input: String, output: String) {
            val result = Day11(input).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day11/day11.input").readLines().first()
            val result = Day11(input).solve1()

            println("Day 11 part 1: $result")

            assertThat(result).isEqualTo("hepxxyzz")
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day11/day11.input").readLines().first()
            val result = Day11("hepxxyzz").solve2()

            println("Day 11 part 2: $result")

            // assertThat(result).isEqualTo(0)
        }
    }

}