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

@DisplayName("Day 11")
class Day11Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day11/day11.sample1").readLines()
            val result = Day11(input).solve1()

            assertThat(result).isEqualTo(374L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day11/day11.input").readLines()
            val result = Day11(input).solve1()

            println("Day 11 part 1: $result")

            assertThat(result).isEqualTo(9545480L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day11/day11.sample1").readLines()
            val result = Day11(input).solve2(10)

            assertThat(result).isEqualTo(1030L)
        }

        @Test
        fun sample1_2() {
            val input = File("./src/test/resources/2023/day11/day11.sample1").readLines()
            val result = Day11(input).solve2(100)

            assertThat(result).isEqualTo(8410L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day11/day11.input").readLines()
            val result = Day11(input).solve2()

            println("Day 11 part 2: $result")
            assertThat(result).isEqualTo(406725732046L)
        }
    }

}