package solution._2024

import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 05")
class Day05Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day05/day05.sample1").readLines()
            val result = Day05(input).solve1()

            assertThat(result).isEqualTo(143L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day05/day05.input").readLines()
            val result = Day05(input).solve1()

            println("Day 05 part 1: $result")

            assertThat(result).isEqualTo(3608)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day05/day05.sample1").readLines()
            val result = Day05(input).solve2()

            assertThat(result).isEqualTo(123)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day05/day05.input").readLines()
            val result = Day05(input).solve2()

            println("Day 05 part 2: $result")

            assertThat(result).isEqualTo(4922)
        }
    }
}