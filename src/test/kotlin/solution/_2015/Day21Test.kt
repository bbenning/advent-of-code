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
import util.ints

@DisplayName("Day 21")
class Day21Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val hero = Day21.Character(8, 5, 5)
            val boss = Day21.Character(12, 7, 2)
            val result = Day21(hero, boss).heroWins(hero, boss)

            assertThat(result).isEqualTo(true)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day21/day21.input").readLines()
            val (hp, damage, armor) = input.flatMap { it.ints() }
            val result = Day21(Day21.Character(100, 0, 0), Day21.Character(hp, damage, armor)).solve1()

            println("Day 21 part 1: $result")

            assertThat(result).isEqualTo(121)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day21/day21.input").readLines()
            val (hp, damage, armor) = input.flatMap { it.ints() }
            val result = Day21(Day21.Character(100, 0, 0), Day21.Character(hp, damage, armor)).solve2()

            println("Day 21 part 2: $result")

            assertThat(result).isEqualTo(201)
        }
    }

}