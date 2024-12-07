package solution._2024

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 04")
class Day04Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day04/day04.sample1").readLines()
            val result = Day04(input).solve1()

            assertThat(result).isEqualTo(18)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day04/day04.input").readLines()
            val result = Day04(input).solve1()

            println("Day 04 part 1: $result")

            assertThat(result).isEqualTo(2397)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day04/day04.sample1").readLines()
            val result = Day04(input).solve2()

            assertThat(result).isEqualTo(9L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day04/day04.input").readLines()
            val result = Day04(input).solve2()

            println("Day 04 part 2: $result")

            assertThat(result).isEqualTo(1824)
        }
    }

}