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

@DisplayName("Day 10")
class Day10Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day10/day10.sample1").readLines()
            val result = Day10(input).solve1()

            assertThat(result).isEqualTo(4L)
        }

        @Test
        fun sample2() {
            val input = File("./src/test/resources/2023/day10/day10.sample2").readLines()
            val result = Day10(input).solve1()

            assertThat(result).isEqualTo(8L)
        }


        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day10/day10.input").readLines()
            val result = Day10(input).solve1()

            println("Day 10 part 1: $result")

            assertThat(result).isEqualTo(6738L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample3() {
            val input = File("./src/test/resources/2023/day10/day10.sample3").readLines()
            val result = Day10(input).solve2()

            assertThat(result).isEqualTo(4L)
        }

        @Test
        fun sample4() {
            val input = File("./src/test/resources/2023/day10/day10.sample4").readLines()
            val result = Day10(input).solve2()

            assertThat(result).isEqualTo(4L)
        }

        @Test
        fun sample5() {
            val input = File("./src/test/resources/2023/day10/day10.sample5").readLines()
            val result = Day10(input).solve2()

            assertThat(result).isEqualTo(8L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day10/day10.input").readLines()
            val result = Day10(input).solve2()

            println("Day 10 part 2: $result")

            // assertThat(result).isEqualTo(0L)
        }
    }

}