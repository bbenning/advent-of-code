package solution._2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@DisplayName("Day 04")
class Day04Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @ParameterizedTest
        @CsvSource(
            "abcdef,609043",
            "pqrstuv,1048970"
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day04(listOf(input)).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day04/day04.input").readLines()
            val result = Day04(input).solve1()

            println("Day 04 part 1: $result")

             assertThat(result).isEqualTo(282749)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day04/day04.input").readLines()
            val result = Day04(input).solve2()

            println("Day 04 part 2: $result")

            assertThat(result).isEqualTo(9962624)
        }
    }
}