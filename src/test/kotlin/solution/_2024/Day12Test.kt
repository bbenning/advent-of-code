package solution._2024

import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 12")
class Day12Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day12/day12.sample1").readLines()
            val result = Day12(input).solve1()

            assertThat(result).isEqualTo(1930L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day12/day12.input").readLines()
            val result = Day12(input).solve1()

            println("Day 12 part 1: $result")

            assertThat(result).isEqualTo(1431440L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day12/day12.sample1").readLines()
            val result = Day12(input).solve2()

            assertThat(result).isEqualTo(1206L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day12/day12.input").readLines()
            val result = Day12(input).solve2()

            println("Day 12 part 2: $result")

            assertThat(result).isEqualTo(869070L)
        }
    }

}