package solution._2024

import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 15")
class Day15Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day15/day15.sample1").readLines()
            val result = Day15(input).solve1()

            assertThat(result).isEqualTo(10092)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day15/day15.input").readLines()
            val result = Day15(input).solve1()

            println("Day 15 part 1: $result")

            assertThat(result).isEqualTo(1492518)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day15/day15.sample1").readLines()
            val result = Day15(input, true).solve2()

            assertThat(result).isEqualTo(9021)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day15/day15.input").readLines()
            val result = Day15(input, true).solve2()

            println("Day 15 part 2: $result")

            assertThat(result).isEqualTo(1512860)
        }
    }

}