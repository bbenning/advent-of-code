package solution._2024

import kotlin.math.abs
import util.*

class Day01(val input: List<String>) {
    fun solve1(): Long {
        val (listLeft, listRight) = input.map { it.longs() }.transpose().map { it.sorted() }

        val sumDiffs = listLeft.zip(listRight).sumOf { (a, b) -> abs(a - b) }
        return sumDiffs
    }

    fun solve2(): Long {
        val (listLeft, listRight) = input.map { it.longs() }.transpose()

        val result = listLeft.sumOf { leftNumber ->
            leftNumber * listRight.count{rightNumber -> leftNumber == rightNumber}
        }
        return result
    }
}