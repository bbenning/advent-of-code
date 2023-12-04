package solution._2023

import util.*

class Day01(val input: List<String>) {
    fun solve1(): Int {
        return input.sumOf { str ->
            val numberString = str.numbers().joinToString("")
            "${numberString.first()}${numberString.last()}".toInt()
        }
    }

    fun solve2():Int {
        val numberWords:List<String> = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

        return input.sumOf { str ->
            val indices = "123456789".flatMapIndexed { numIndex, c ->
                str.indices("$c").map { index -> Pair(index, numIndex + 1) }
            }
            val indicesWords : List<Pair<Int, Int>> = numberWords.flatMap { replacement ->
                str.indices(replacement).map{Pair(it, (numberWords.indexOf(replacement) + 1))}
            }

            val digit1 = (indices + indicesWords).minBy { it.first }.second
            val digit2 = (indices + indicesWords).maxBy { it.first }.second
            digit1 * 10 + digit2
        }
    }
}