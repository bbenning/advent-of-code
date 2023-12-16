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

@DisplayName("Day 16")
class Day16Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day16/day16.sample1").readLines()
            val result = Day16(input).solve1()

            assertThat(result).isEqualTo(46)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day16/day16.input").readLines()
            val result = Day16(input).solve1()

            println("Day 16 part 1: $result")

            assertThat(result).isEqualTo(7074)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day16/day16.sample1").readLines()
            val result = Day16(input).solve2()

            assertThat(result).isEqualTo(51)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day16/day16.input").readLines()
            val result = Day16(input).solve2()

            println("Day 16 part 2: $result")

            assertThat(result).isEqualTo(7530)
        }
    }

}