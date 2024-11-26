package solution._2016

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Day 08")
class Day08Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day08/day08.input").readLines()
            val result = Day08(input).solve1()

            println("Day 08 part 1: $result")

            assertThat(result).isEqualTo(110L)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2016/day08/day08.input").readLines()
            val result = Day08(input).solve2()

            println("Day 08 part 2: $result")

            assertThat(result).isEqualTo("ZJHRKCPLYJ")
        }
    }
}