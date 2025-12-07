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

@DisplayName("Day 07")
class Day07Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2025/day07/day07.sample1").readLines()
            val result = Day07(input).solve1()

            assertThat(result).isEqualTo(21L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2025/day07/day07.input").readLines()
            val result = Day07(input).solve1()

            println("Day 07 part 1: $result")

            assertThat(result).isEqualTo(1667L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2025/day07/day07.sample1").readLines()
            val result = Day07(input).solve2()

            assertThat(result).isEqualTo(40L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2025/day07/day07.input").readLines()
            val result = Day07(input).solve2()

            println("Day 07 part 2: $result")

            assertThat(result).isEqualTo(62943905501815L)
        }
    }

}