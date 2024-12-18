package solution._2024

import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 18")
class Day18Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day18/day18.sample1").readLines()
            val result = Day18(input, 7, 12).solve1()

            assertThat(result).isEqualTo(22)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day18/day18.input").readLines()
            val result = Day18(input, 71, 1024).solve1()

            println("Day 18 part 1: $result")

            assertThat(result).isEqualTo(416)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day18/day18.sample1").readLines()
            val result = Day18(input, 7, 12).solve2()

            assertThat(result).isEqualTo("6,1")
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day18/day18.input").readLines()
            val result = Day18(input, 71, 1024).solve2()

            println("Day 18 part 2: $result")

            assertThat(result).isEqualTo("50,23")
        }
    }
}