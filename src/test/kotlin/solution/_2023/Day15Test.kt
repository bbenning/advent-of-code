package solution._2023

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 15")
class Day15Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @ParameterizedTest
        @CsvSource(
            "HASH;52",
            "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7;1320",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day15(input).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day15/day15.input").readLines().first()
            val result = Day15(input).solve1()

            println("Day 15 part 1: $result")

            assertThat(result).isEqualTo(520500)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @ParameterizedTest
        @CsvSource(
            "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7;145",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day15(input).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day15/day15.input").readLines().first()
            val result = Day15(input).solve2()

            println("Day 15 part 2: $result")

            assertThat(result).isEqualTo(213097)
        }
    }

}