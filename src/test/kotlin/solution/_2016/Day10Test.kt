package solution._2016

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 10")
class Day10Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        // by reading the output
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day10/day10.input").readLines()
            val result = Day10(input).solve2()

            println("Day 10 part 2: $result")

            assertThat(result).isEqualTo(23903L)
        }
    }

}