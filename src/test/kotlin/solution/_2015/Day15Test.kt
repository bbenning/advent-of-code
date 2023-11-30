package solution._2015

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 15")
class Day15Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2015/day15/day15.sample1").readLines()
            val result = Day15(input).solve1()

            assertThat(result).isEqualTo(62842880L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day15/day15.input").readLines()
            val result = Day15(input).solve1()

            println("Day 15 part 1: $result")

            assertThat(result).isEqualTo(21367368L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2015/day15/day15.sample1").readLines()
            val result = Day15(input).solve2()

            assertThat(result).isEqualTo(57600000L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day15/day15.input").readLines()
            val result = Day15(input).solve2()

            println("Day 15 part 2: $result")

            assertThat(result).isEqualTo(1766400L)
        }
    }
}