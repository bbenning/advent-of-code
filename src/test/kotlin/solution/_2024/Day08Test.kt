package solution._2024

import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 08")
class Day08Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day08/day08.sample1").readLines()
            val result = Day08(input).solve1()

            assertThat(result).isEqualTo(14L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day08/day08.input").readLines()
            val result = Day08(input).solve1()

            println("Day 08 part 1: $result")

            assertThat(result).isEqualTo(244L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2024/day08/day08.sample1").readLines()
            val result = Day08(input).solve2()

            assertThat(result).isEqualTo(34L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day08/day08.input").readLines()
            val result = Day08(input).solve2()

            println("Day 08 part 2: $result")

            assertThat(result).isEqualTo(912)
        }
    }

}