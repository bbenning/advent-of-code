package solution._2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@DisplayName("Day 09")
class Day09Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2015/day09/day09.sample1").readLines()
            val result = Day09(input).solve1()

            assertThat(result).isEqualTo(605)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day09/day09.input").readLines()
            val result = Day09(input).solve1()

            println("Day 09 part 1: $result")

            assertThat(result).isEqualTo(251)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2015/day09/day09.sample1").readLines()
            val result = Day09(input).solve2()

            assertThat(result).isEqualTo(982)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day09/day09.input").readLines()
            val result = Day09(input).solve2()

            println("Day 09 part 2: $result")

            assertThat(result).isEqualTo(898)
        }
    }

}