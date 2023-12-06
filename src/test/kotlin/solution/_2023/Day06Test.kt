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

@DisplayName("Day 06")
class Day06Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day06/day06.sample1").readLines()
            val result = Day06(input).solve1()

            assertThat(result).isEqualTo(288L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day06/day06.input").readLines()
            val result = Day06(input).solve1()

            println("Day 06 part 1: $result")

            assertThat(result).isEqualTo(2269432L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day06/day06.sample2").readLines()
            val result = Day06(input).solve2()

            assertThat(result).isEqualTo(71503L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day06/day06.input2").readLines()
            val result = Day06(input).solve2()

            println("Day 06 part 2: $result")

            assertThat(result).isEqualTo(35865985L)
        }
    }

}