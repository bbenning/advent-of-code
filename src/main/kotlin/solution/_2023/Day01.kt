package solution._2023

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day01(val input: List<String>) {
    fun solve1(): Int {
        return input.sumOf { str ->
            val indices = "123456789".flatMapIndexed { numIndex, c ->
                str.indices("$c").map { index -> Pair(index, numIndex + 1) }
            }
            val digit1 = indices.minBy { it.first }.second
            val digit2 = indices.maxBy { it.first }.second
            digit1 * 10 + digit2
        }
    }

    fun solve2():Int {

        val replacements:List<String> = listOf("one", "two", "three", "four", "five","six", "seven", "eight","nine")

        return input.sumOf { str ->
            val indices = "123456789".flatMapIndexed { numIndex, c ->
                str.indices("$c").map { index -> Pair(index, numIndex + 1) }
            }
            val indicesWords : List<Pair<Int, Int>> = replacements.flatMap { replacement ->
                str.indices(replacement).map{Pair(it, (replacements.indexOf(replacement) + 1))}
            }

            val digit1 = (indices + indicesWords).minBy { it.first }.second
            val digit2 = (indices + indicesWords).maxBy { it.first }.second
            digit1 * 10 + digit2
        }
    }
}