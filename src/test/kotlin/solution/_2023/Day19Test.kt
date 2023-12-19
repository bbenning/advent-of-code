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

@DisplayName("Day 19")
class Day19Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day19/day19.sample1").readLines()
            val result = Day19(input).solve1()

            assertThat(result).isEqualTo(19114)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day19/day19.input").readLines()
            val result = Day19(input).solve1()

            println("Day 19 part 1: $result")

            assertThat(result).isEqualTo(402185L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day19/day19.sample1").readLines()
            val result = Day19(input).solve2()

            assertThat(result).isEqualTo(167409079868000L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day19/day19.input").readLines()
            val result = Day19(input).solve2()

            println("Day 19 part 2: $result")

            assertThat(result).isEqualTo(130291480568730L)
        }
    }

}