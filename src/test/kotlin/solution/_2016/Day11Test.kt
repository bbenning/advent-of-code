package solution._2016

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
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
            val input = File("./src/test/resources/2016/day11/day11.sample1").readLines()
            val result = Day11(input).solve1()

            assertThat(result).isEqualTo(11L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day11/day11.input").readLines()
            val result = Day11(input).solve1()

            println("Day 11 part 1: $result")

            assertThat(result).isEqualTo(47L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        @Disabled("My solution runs for too long")
        fun answer() {
            val input = File("./src/test/resources/2016/day11/day11.input").readLines()

            val firstLine = input.first() + " elerium-compatible microchip elerium generator dilithium generator dilithium-compatible microchip"

            val correctInput = listOf(firstLine) + input.drop(1)

            val result = Day11(correctInput).solve2()

            println("Day 11 part 2: $result")

            assertThat(result).isEqualTo(71L)
        }
    }

}
