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

@DisplayName("Day 18")
class Day18Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2015/day18/day18.sample1").readLines()
            val result = Day18(input).solve1(4)

            assertThat(result).isEqualTo(4)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day18/day18.input").readLines()
            val result = Day18(input).solve1(100)

            println("Day 18 part 1: $result")

            assertThat(result).isEqualTo(814)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2015/day18/day18.sample2").readLines()
            val result = Day18(input).solve2(5)

            assertThat(result).isEqualTo(17)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day18/day18.input2").readLines()
            val result = Day18(input).solve2(100)

            println("Day 18 part 2: $result")

            assertThat(result).isEqualTo(924)
        }
    }

}