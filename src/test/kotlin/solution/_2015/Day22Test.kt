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

@DisplayName("Day 22")
class Day22Test {


    @Nested
    @DisplayName("Solve 1")
    inner class Solve1 {
        @Test
        fun sample1() {
            val hero = Day22.Hero(10, 250)
            val boss = Day22.Boss(13, 8)
            val result = Day22(hero, boss).solve1()

            assertThat(result).isEqualTo(173 + 53)
        }

        @Test
        fun sample2() {
            val hero = Day22.Hero(10, 250)
            val boss = Day22.Boss(14, 8)
            val result = Day22(hero, boss).solve1()

            assertThat(result).isEqualTo(229 + 113 + 73 + 173 + 53)
        }

        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day22/day22.input").readLines()
            val (hp, damage) = input.flatMap { it.ints() }
            val boss = Day22.Boss(hp, damage)
            val hero = Day22.Hero(50, 500)
            val result = Day22(hero, boss).solve1()

            println("Day 22 part 1: $result")

            assertThat(result).isEqualTo(1824)
        }
    }

    @Nested
    @DisplayName("Solve 2")
    inner class Solve2 {
        @Test
        fun answer() {
            val input = File("./src/test/resources/2015/day22/day22.input").readLines()
            val (hp, damage) = input.flatMap { it.ints() }
            val boss = Day22.Boss(hp, damage)
            val hero = Day22.Hero(50, 500)
            val result = Day22(hero, boss).solve2()

            println("Day 22 part 2: $result")

            assertThat(result).isEqualTo(1937)
        }
    }

}