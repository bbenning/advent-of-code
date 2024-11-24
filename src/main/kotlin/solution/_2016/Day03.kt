package solution._2016

import util.*

class Day03(val input: List<String>) {
    fun solve1(): Long {
        return solveHorizontal()
    }

    fun solve2(): Long {
        return solveVertical()
    }

    private fun solveHorizontal(): Long {
        val numbers = input.map { it.ints() }
        val possible = numbers.count {
            val (a, b, c) = it.sorted()
            a + b > c
        }
        return possible.toLong()
    }

    private fun solveVertical(): Long {
        val numbers = input.chunked(3).flatMap { listOfStrings ->
            val ints = listOfStrings.flatMap { it.ints() }
            (0..2).map { listOf(ints[it], ints[it + 3], ints[it + 6]) }
        }
        val possible = numbers.count {
            val (a, b, c) = it.sorted()
            a + b > c
        }
        return possible.toLong()
    }
}