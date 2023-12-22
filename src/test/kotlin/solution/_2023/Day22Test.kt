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

@DisplayName("Day 22")
class Day22Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day22/day22.sample1").readLines()
            val result = Day22(input).solve1()

            assertThat(result).isEqualTo(5)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day22/day22.input").readLines()
            val result = Day22(input).solve1()

            println("Day 22 part 1: $result")

            assertThat(result).isEqualTo(416)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day22/day22.sample1").readLines()
            val result = Day22(input).solve2()

            assertThat(result).isEqualTo(7)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day22/day22.input").readLines()
            val result = Day22(input).solve2()

            println("Day 22 part 2: $result")

            assertThat(result).isEqualTo(60963)
        }
    }

}