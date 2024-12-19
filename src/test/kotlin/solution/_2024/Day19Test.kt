package solution._2024

import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 19")
class Day19Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day19/day19.sample1").readLines()
            val result = Day19(input).solve1()

            assertThat(result).isEqualTo(6)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day19/day19.input").readLines()
            val result = Day19(input).solve1()

            println("Day 19 part 1: $result")

            assertThat(result).isEqualTo(315)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day19/day19.sample1").readLines()
            val result = Day19(input).solve2()

            assertThat(result).isEqualTo(16)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day19/day19.input").readLines()
            val result = Day19(input).solve2()

            println("Day 19 part 2: $result")

            assertThat(result).isEqualTo(625108891232249L)
        }
    }

}