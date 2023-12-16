package solution._2023

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 12")
class Day12Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day12/day12.sample1").readLines()
            val result = Day12(input).solve1()

            assertThat(result).isEqualTo(21)
        }

        @ParameterizedTest
        @CsvSource(
            "???.### 1,1,3;1",
            ".??..??...?##. 1,1,3;4",
            "?#?#?#?#?#?#?#? 1,3,1,6;1",
            "????.#...#... 4,1,1;1",
            "????.######..#####. 1,6,5;4",
            "?###???????? 3,2,1;10",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day12(listOf(input)).solve1()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day12/day12.input").readLines()
            val result = Day12(input).solve1()

            println("Day 12 part 1: $result")

            assertThat(result).isEqualTo(8180)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day12/day12.sample1").readLines()
            val result = Day12(input).solve2()

            assertThat(result).isEqualTo(525152)
        }

        @ParameterizedTest
        @CsvSource(
            "???.### 1,1,3;1",
            ".??..??...?##. 1,1,3;16384",
            "?#?#?#?#?#?#?#? 1,3,1,6;1",
            "????.#...#... 4,1,1;16",
            "????.######..#####. 1,6,5;2500",
            "?###???????? 3,2,1;506250",
            delimiter = ';'
        )
        fun sample1Parameterized(input: String, output: Long) {
            val result = Day12(listOf(input)).solve2()

            assertThat(result).isEqualTo(output)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day12/day12.input").readLines()
            val result = Day12(input).solve2()

            println("Day 12 part 2: $result")

            assertThat(result).isEqualTo(620189727003627L)
        }
    }

}