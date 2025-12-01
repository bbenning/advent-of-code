package solution._2025

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 01")
class Day01Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2025/day01/day01.sample1").readLines()
            val result = Day01(input).solve1()

            assertThat(result).isEqualTo(3L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2025/day01/day01.input").readLines()
            val result = Day01(input).solve1()

            println("Day 01 part 1: $result")

            assertThat(result).isEqualTo(1089)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2025/day01/day01.sample1").readLines()
            val result = Day01(input).solve2()

            assertThat(result).isEqualTo(6)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2025/day01/day01.input").readLines()
            val result = Day01(input).solve2()

            println("Day 01 part 2: $result")

            assertThat(result).isEqualTo(6530)
        }
    }

}