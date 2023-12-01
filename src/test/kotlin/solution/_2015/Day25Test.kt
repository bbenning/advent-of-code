package solution._2015

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 25")
class Day25Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @ParameterizedTest
        @CsvSource(
            "To continue, please consult the code grid in the manual.  Enter the code at row 6, column 2.;6796745",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day25(input).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day25/day25.input").readLines().first()
            val result = Day25(input).solve1()

            println("Day 25 part 1: $result")

            assertThat(result).isEqualTo(8997277)
        }
    }
}