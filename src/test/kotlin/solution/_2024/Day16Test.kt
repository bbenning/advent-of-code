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

@DisplayName("Day 16")
class Day16Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day16/day16.sample1").readLines()
            val result = Day16(input).solve1()

            assertThat(result).isEqualTo(7036)
        }

        @Test
        fun sample2() {
            val input = File("./src/test/resources/2024/day16/day16.sample2").readLines()
            val result = Day16(input).solve1()

            assertThat(result).isEqualTo(11048)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day16/day16.input").readLines()
            val result = Day16(input).solve1()

            println("Day 16 part 1: $result")

            assertThat(result).isEqualTo(107468)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day16/day16.sample1").readLines()
            val result = Day16(input).solve2()

            assertThat(result).isEqualTo(45)
        }

        @Test
        fun sample2() {
            val input = File("./src/test/resources/2024/day16/day16.sample2").readLines()
            val result = Day16(input).solve2()

            assertThat(result).isEqualTo(64)
        }


        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day16/day16.input").readLines()
            val result = Day16(input).solve2()

            println("Day 16 part 2: $result")

            assertThat(result).isEqualTo(533)
        }
    }

}