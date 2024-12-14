package solution._2024

import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 14")
class Day14Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day14/day14.sample1").readLines()
            val result = Day14(input, 11, 7).solve1()

            assertThat(result).isEqualTo(12)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day14/day14.input").readLines()
            val result = Day14(input, 101, 103).solve1()

            println("Day 14 part 1: $result")

            assertThat(result).isEqualTo(226236192)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day14/day14.input").readLines()
            val result = Day14(input, 101,103).solve2()

            println("Day 14 part 2: $result")

            assertThat(result).isEqualTo(8168)
        }
    }
}