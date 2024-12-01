package solution._2024

import kotlin.math.abs
import util.*

class Day01(val input: List<String>) {
    fun solve1(): Long {
        val (listLeft, listRight) = input.fold(Pair(emptyList<Int>(), emptyList<Int>())){ acc, s ->
            val (a, b) = s.ints()
            Pair(acc.first + a, acc.second + b)
        }

        val sortedListLeft = listLeft.sorted()
        val sortedListRight = listRight.sorted()

        val sumDiffs = sortedListLeft.zip(sortedListRight).sumOf { (a, b) -> abs(a - b) }
        return sumDiffs.toLong()
    }

    fun solve2(): Long {
        val (listLeft, listRight) = input.fold(Pair(emptyList<Int>(), emptyList<Int>())){ acc, s ->
            val (a, b) = s.ints()
            Pair(acc.first + a, acc.second + b)
        }

        val result = listLeft.sumOf { leftNumber -> leftNumber.toLong() * listRight.count{rightNumber -> leftNumber == rightNumber}}

        return result
    }

}