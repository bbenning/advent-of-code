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

@DisplayName("Day 20")
class Day20Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @ParameterizedTest
        @CsvSource(
            "10,1",
            "70,4"
        )
        fun sample1Parameterized(input: String, output: Int) {
            val result = Day20(input.toInt()).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day20/day20.input").readLines().first()
            val result = Day20(input.toInt()).solve1()

            println("Day 20 part 1: $result")

            // assertThat(result).isEqualTo(0)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {


        // 1884960 too high
        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day20/day20.input").readLines().first()
            val result = Day20(input.toInt()).solve2()

            println("Day 20 part 2: $result")

            // assertThat(result).isEqualTo(0)
        }
    }

}