package solution._2016

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 09")
class Day09Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @ParameterizedTest
        @CsvSource(
            "ADVENT;6",
            "A(1x5)BC;7",
            "(3x3)XYZ;9",
            "A(2x2)BCD(2x2)EFG;11",
            "(6x1)(1x3)A;6",
            "X(8x2)(3x3)ABCY;18",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day09(input).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day09/day09.input").readLines().first()
            val result = Day09(input).solve1()

            println("Day 09 part 1: $result")

            assertThat(result).isEqualTo(99145L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @ParameterizedTest
        @CsvSource(
            "(3x3)XYZ;9",
            "X(8x2)(3x3)ABCY;20",
            "(27x12)(20x12)(13x14)(7x10)(1x12)A;241920",
            "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN;445",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day09(input).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day09/day09.input").readLines().first()
            val result = Day09(input).solve2()

            println("Day 09 part 2: $result")

            assertThat(result).isEqualTo(10943094568L)
        }
    }

}