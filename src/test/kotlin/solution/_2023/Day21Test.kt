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

@DisplayName("Day 21")
class Day21Test {

    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val input = File("./src/test/resources/2023/day21/day21.sample1").readLines()
            val result = Day21(input).solve1(6)

            assertThat(result).isEqualTo(16)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day21/day21.input").readLines()
            val result = Day21(input).solve1(64)

            println("Day 21 part 1: $result")

            assertThat(result).isEqualTo(3642)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun sample1a() {
            val input = File("./src/test/resources/2023/day21/day21.sample1").readLines()
            val result = Day21(input).solve2(6)

            assertThat(result).isEqualTo(16L)
        }

        @Test
        fun sample1b() {
            val input = File("./src/test/resources/2023/day21/day21.sample1").readLines()
            val result = Day21(input).solve2(10)

            assertThat(result).isEqualTo(50L)
        }

        @Test
        fun sample1c() {
            val input = File("./src/test/resources/2023/day21/day21.sample1").readLines()
            val result = Day21(input).solve2(50)

            assertThat(result).isEqualTo(1594L)
        }

        @Test
        fun sample1d() {
            val input = File("./src/test/resources/2023/day21/day21.sample1").readLines()
            val result = Day21(input).solve2(100)

            assertThat(result).isEqualTo(6536L)
        }

        @Test
        fun sample1e() {
            val input = File("./src/test/resources/2023/day21/day21.sample1").readLines()
            val result = Day21(input).solve2(500)

            assertThat(result).isEqualTo(167004L)
        }

        @Test
        fun sample1f() {
            val input = File("./src/test/resources/2023/day21/day21.sample1").readLines()
            val result = Day21(input).solve2(1000)

            assertThat(result).isEqualTo(668697L)
        }

        @Test
        fun sample1g() {
            val input = File("./src/test/resources/2023/day21/day21.sample1").readLines()
            val result = Day21(input).solve2(5000)

            assertThat(result).isEqualTo(16733044L)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2023/day21/day21.input").readLines()
            // TODO
//            val result = Day21(input).solve2(26_501_365) .. no clue for now
            val result = Day21(input).solve2(0)

            println("Day 21 part 2: $result")

            // assertThat(result).isEqualTo(0L)
        }
    }

}