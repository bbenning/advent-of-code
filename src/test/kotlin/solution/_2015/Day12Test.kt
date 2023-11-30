package solution._2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@DisplayName("Day 12")
class Day12Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @ParameterizedTest
        @CsvSource(
            "[1,2,3]; 6",
            "{\"a\":2,\"b\":4}; 6",
            "[[[3]]]; 3",
            "{\"a\":{\"b\":4},\"c\":-1}; 3",
            "{\"a\":[-1,1]}; 0",
            "[-1,{\"a\":1}]; 0",
            "[]; 0",
            "{}; 0",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Int) {
            val result = Day12(input).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day12/day12.input").readLines().first()
            val result = Day12(input).solve1()

            println("Day 12 part 1: $result")

            assertThat(result).isEqualTo(191164)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @ParameterizedTest
        @CsvSource(
            "[1,2,3]; 6",
            "[1,{\"c\":\"red\",\"b\":2},3]; 4",
            "{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}; 0",
            "[1,\"red\",5]; 6",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Int) {
            val result = Day12(input).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day12/day12.input").readLines().first()
            val result = Day12(input).solve2()

            println("Day 12 part 2: $result")

            assertThat(result).isEqualTo(87842)
        }
    }

}