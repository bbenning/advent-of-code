package solution._2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@DisplayName("Day 08")
class Day08Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @ParameterizedTest
        @CsvSource(
            "\"\",2",
            "\"abc\",2",
            "\"aaa\\\"aaa\",3",
            "\"\\x27\",5"
        )
        fun sample1Parameterized(input: String, output: Int) {
            val result = Day08(listOf(input)).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day08/day08.input").readLines()
            val result = Day08(input).solve1()

            println("Day 08 part 1: $result")

            assertThat(result).isEqualTo(1342)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @ParameterizedTest
        @CsvSource(
            "\"\",4",
            "\"abc\",4",
            "\"aaa\\\"aaa\",6",
            "\"\\x27\",5"
        )
        fun sample1Parameterized(input: String, output: Int) {
            val result = Day08(listOf(input)).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day08/day08.input").readLines()
            val result = Day08(input).solve2()

            println("Day 08 part 2: $result")
            assertThat(result).isEqualTo(2074)
        }
    }
}