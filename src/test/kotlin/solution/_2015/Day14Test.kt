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

@DisplayName("Day 14")
class Day14Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1a() {
            val input = File("./src/test/resources/2015/day14/day14.sample1").readLines()
            val result = Day14(input).solve1(1)

            assertThat(result).isEqualTo(16)
        }

        @Test
        fun sample1b() {
            val input = File("./src/test/resources/2015/day14/day14.sample1").readLines()
            val result = Day14(input).solve1(10)

            assertThat(result).isEqualTo(160)
        }

        @Test
        fun sample1c() {
            val input = File("./src/test/resources/2015/day14/day14.sample1").readLines()
            val result = Day14(input).solve1(1000)

            assertThat(result).isEqualTo(1120)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day14/day14.input").readLines()
            val result = Day14(input).solve1(2503)

            println("Day 14 part 1: $result")

            assertThat(result).isEqualTo(2660)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1a() {
            val input = File("./src/test/resources/2015/day14/day14.sample1").readLines()
            val result = Day14(input).solve2(1)

            assertThat(result).isEqualTo(1)
        }

        @Test
        fun sample1b() {
            val input = File("./src/test/resources/2015/day14/day14.sample1").readLines()
            val result = Day14(input).solve2(140)

            assertThat(result).isEqualTo(139)
        }

        @Test
        fun sample1c() {
            val input = File("./src/test/resources/2015/day14/day14.sample1").readLines()
            val result = Day14(input).solve2(1000)

            assertThat(result).isEqualTo(689)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day14/day14.input").readLines()
            val result = Day14(input).solve2(2503)

            println("Day 14 part 2: $result")

            assertThat(result).isEqualTo(1256)
        }
    }

}