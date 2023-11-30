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

@DisplayName("Day 23")
class Day23Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2015/day23/day23.sample1").readLines()
            val result = Day23(input).solve1()

            assertThat(result).isEqualTo(2)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day23/day23.input").readLines()
            val result = Day23(input).solve1()

            println("Day 23 part 1: $result")

            assertThat(result).isEqualTo(170)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day23/day23.input").readLines()
            val result = Day23(input).solve2()

            println("Day 23 part 2: $result")

            assertThat(result).isEqualTo(247)
        }
    }

}