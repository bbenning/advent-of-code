package solution._2024

import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 20")
class Day20Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day20/day20.sample1").readLines()
            val result = Day20(input).solve1(1)

            assertThat(result).isEqualTo(44)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day20/day20.input").readLines()
            val result = Day20(input).solve1(100)

            println("Day 20 part 1: $result")

            assertThat(result).isEqualTo(1378)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day20/day20.sample1").readLines()
            val result = Day20(input).solve2(50, 20)

            assertThat(result).isEqualTo(285)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day20/day20.input").readLines()
            val result = Day20(input).solve2(100, 20)

            println("Day 20 part 2: $result")

            assertThat(result).isEqualTo(975379)
        }
    }

}