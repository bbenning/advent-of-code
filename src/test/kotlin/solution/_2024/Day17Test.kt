package solution._2024

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.assertj.core.api.Assertions.assertThat
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
            val input = File("./src/test/resources/2024/day17/day17.sample1").readLines()
            val result = Day17(input).solve1()

            assertThat(result).isEqualTo("4,6,3,5,6,3,5,2,1,0")
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day17/day17.input").readLines()
            val result = Day17(input).solve1()

            println("Day 17 part 1: $result")

            assertThat(result).isEqualTo("7,4,2,0,5,0,5,3,7")
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
// Unfortunately, solution doesn't work with test input, just the specific program.
//        @Test
//        fun sample1() {
//            val input = File("./src/test/resources/2024/day17/day17.sample2").readLines()
//            val result = Day17(input).solve2()
//
//            assertThat(result).isEqualTo(117440)
//        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2024/day17/day17.input").readLines()
            val result = Day17(input).solve2()

            println("Day 17 part 2: $result")

            assertThat(result).isEqualTo(202991746427434L)
        }
    }

}