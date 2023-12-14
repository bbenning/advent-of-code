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

@DisplayName("Day 14")
class Day14Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day14/day14.sample1").readLines()
            val result = Day14(input).solve1()

            assertThat(result).isEqualTo(136L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day14/day14.input").readLines()
            val result = Day14(input).solve1()

            println("Day 14 part 1: $result")

            assertThat(result).isEqualTo(109424L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day14/day14.sample1").readLines()
            val result = Day14(input).solve2(1_000_000_000)

            assertThat(result).isEqualTo(64L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day14/day14.input").readLines()
            val result = Day14(input).solve2(1_000_000_000)

            println("Day 14 part 2: $result")

            assertThat(result).isEqualTo(102509L)
        }
    }

}