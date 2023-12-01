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

@DisplayName("Day 24")
class Day24Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2015/day24/day24.sample1").readLines()
            val result = Day24(input.map{it.toInt()}).solve1()

            assertThat(result).isEqualTo(99)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day24/day24.input").readLines()
            val result = Day24(input.map{it.toInt()}).solve1()

            println("Day 24 part 1: $result")

            assertThat(result).isEqualTo(10723906903L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2015/day24/day24.sample1").readLines()
            val result = Day24(input.map{it.toInt()}).solve2()

            assertThat(result).isEqualTo(44)
        }


        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day24/day24.input").readLines()
            val result = Day24(input.map{it.toInt()}).solve2()

            println("Day 24 part 2: $result")

            assertThat(result).isEqualTo(74850409)
        }
    }

}