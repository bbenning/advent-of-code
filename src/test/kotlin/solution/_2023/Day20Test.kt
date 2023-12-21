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

@DisplayName("Day 20")
class Day20Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day20/day20.sample1").readLines()
            val result = Day20(input).solve1()

            assertThat(result).isEqualTo(32000000L)
        }

        @Test
        fun sample2() {
            val input = File("./src/test/resources/2023/day20/day20.sample2").readLines()
            val result = Day20(input).solve1()

            assertThat(result).isEqualTo(11687500L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day20/day20.input").readLines()
            val result = Day20(input).solve1()

            println("Day 20 part 1: $result")

            assertThat(result).isEqualTo(925955316L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day20/day20.input").readLines()
            val result = Day20(input).solve2()

            println("Day 20 part 2: $result")

            assertThat(result).isEqualTo(241528477694627L)
        }
    }

}