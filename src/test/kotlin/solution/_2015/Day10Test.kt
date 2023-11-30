package solution._2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@DisplayName("Day 10")
class Day10Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @ParameterizedTest
        @CsvSource(
            "1,11",
            "11, 21",
            "1211, 111221",
            "111221, 312211"
        )
        fun sample1Parameterized(input: String, output: String) {
            val result = Day10(input).iterate(input)

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day10/day10.input").readLines().first()
            val result = Day10(input).solve1()

            println("Day 10 part 1: $result")

            assertThat(result).isEqualTo(360154)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day10/day10.input").readLines().first()
            val result = Day10(input).solve2()

            println("Day 10 part 2: $result")

            assertThat(result).isEqualTo(5103798)
        }
    }

}