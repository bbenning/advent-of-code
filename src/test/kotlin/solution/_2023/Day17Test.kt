package solution._2023

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 17")
class Day17Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day17/day17.sample1").readLines()
            val result = Day17(input).solve1()

            assertThat(result).isEqualTo(102)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day17/day17.input").readLines()
            val result = Day17(input).solve1()

            println("Day 17 part 1: $result")

            assertThat(result).isEqualTo(767)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day17/day17.sample1").readLines()
            val result = Day17(input).solve2()

            assertThat(result).isEqualTo(94)
        }

        @Test
        @Disabled("My solution runs for too long")
        fun answer() {
            val input = File("./src/test/resources/2023/day17/day17.input").readLines()
            val result = Day17(input).solve2()

            println("Day 17 part 2: $result")

            // assertThat(result).isEqualTo(0L)
        }
    }

}
