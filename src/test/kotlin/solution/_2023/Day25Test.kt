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

@DisplayName("Day 25")
class Day25Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day25/day25.sample1").readLines()
            val result = Day25(input).solve1()

            assertThat(result).isEqualTo(54L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day25/day25.input").readLines()
            val result = Day25(input).solve1()

            println("Day 25 part 1: $result")

            // assertThat(result).isEqualTo(0L)
        }
    }
}