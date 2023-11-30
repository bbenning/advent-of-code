package solution._2015

import util.*
import util.mapping.Coord

class Day06(val input: List<String>) {
    fun solve1(): Int {
        return switchLights1().sumOf { it.sum() }
    }

    fun solve2():Int {
        return switchLights2().sumOf { it.sum () }
    }

    private fun switchLights1():List<List<Int>> {
        val size = 1000
        val lightsOn = List(size){ List(size){0} }

        return input.fold(lightsOn){acc, str ->
            val numbers = str.ints()
            acc.applyToRectangle(Coord(numbers[0], numbers[1]), Coord(numbers[2],numbers[3])) {light ->
                if(str.startsWith("turn on")) {
                    1
                } else if (str.startsWith("toggle")) {
                    if(light == 1) 0 else 1
                } else if (str.startsWith("turn off")) {
                    0
                } else {
                    throw IllegalArgumentException("Invalid input")
                }
            }
        }
    }

    private fun switchLights2():List<List<Int>> {
        val size = 1000
        val lightsOn = List(size){ List(size){0} }

        return input.fold(lightsOn){acc, str ->
            val numbers = str.ints()
            acc.applyToRectangle(Coord(numbers[0], numbers[1]), Coord(numbers[2],numbers[3])) {light ->
                if(str.startsWith("turn on")) {
                    light + 1
                } else if (str.startsWith("toggle")) {
                    light + 2
                } else if (str.startsWith("turn off")) {
                    maxOf(0, light - 1)
                } else {
                    throw IllegalArgumentException("Invalid input")
                }
            }
        }
    }
}