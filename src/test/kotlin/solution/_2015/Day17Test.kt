package solution._2015

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
            val input = File("./src/test/resources/2015/day17/day17.sample1").readLines()
            val result = Day17(input).solve1(25)

            assertThat(result).isEqualTo(4)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day17/day17.input").readLines()
            val result = Day17(input).solve1(150)

            println("Day 17 part 1: $result")

            assertThat(result).isEqualTo(654)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day17/day17.input").readLines()
            val result = Day17(input).solve2(150)

            println("Day 17 part 2: $result")

            assertThat(result).isEqualTo(57)
        }
    }

}