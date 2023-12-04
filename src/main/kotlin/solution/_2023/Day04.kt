package solution._2023

import util.*
import kotlin.math.pow

class Day04(val input: List<String>) {
    fun solve1(): Long {
        return getWinners().sumOf {
            if (it == 0) 0L else (2.0.pow(it - 1).toLong())
        }
    }

    fun solve2(): Int {
        return getWinners().foldIndexed(List(input.size){1}) { idx, acc, winners ->
            acc.mapIndexed { index, i -> if(index in idx + 1 .. idx + winners) i + acc[idx] else i }
        }.sum()
    }

    private fun getWinners(): List<Int> {
        return input.map {
            val splitString = it.split("|")
            val winningNumbers = splitString[0].ints().drop(1).toSet()
            val myNumbers = splitString[1].ints()

            myNumbers.count { myNumber -> myNumber in winningNumbers }
        }
    }
}