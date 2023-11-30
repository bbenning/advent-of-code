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

@DisplayName("Day 19")
class Day19Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2015/day19/day19.sample1").readLines()
            val result = Day19(input).solve1()

            assertThat(result).isEqualTo(4)
        }

        @Test
        fun sample2() {
            val input = File("./src/test/resources/2015/day19/day19.sample2").readLines()
            val result = Day19(input).solve1()

            assertThat(result).isEqualTo(7)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day19/day19.input").readLines()
            val result = Day19(input).solve1()

            println("Day 19 part 1: $result")

            assertThat(result).isEqualTo(518)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample3() {
            val input = File("./src/test/resources/2015/day19/day19.sample3").readLines()
            val result = Day19(input).solve2()

            assertThat(result).isEqualTo(3)
        }

        @Test
        fun sample4() {
            val input = File("./src/test/resources/2015/day19/day19.sample4").readLines()
            val result = Day19(input).solve2()

            assertThat(result).isEqualTo(6)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day19/day19.input").readLines()
            val result = Day19(input).solve2()

            println("Day 19 part 2: $result")

            // assertThat(result).isEqualTo(0)
        }
    }
}