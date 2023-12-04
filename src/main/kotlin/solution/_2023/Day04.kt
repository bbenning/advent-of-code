package solution._2023

import util.*
import kotlin.math.pow

class Day04(val input: List<String>) {
    fun solve1(): Long {
        return input.sumOf {
            val splitString = it.split("|")
            val winningNumbers = splitString[0].ints().drop(1).toSet()
            val myNumbers = splitString[1].ints()

            val winners = myNumbers.count { myNumber -> myNumber in winningNumbers }

            if (winners == 0) 0L else (2.0.pow(winners - 1).toLong())
        }
    }

    fun solve2():Int {

        return input.foldIndexed(List(input.size){1}) { idx, acc, str ->
            val splitString = str.split("|")
            val winningNumbers = splitString[0].ints().drop(1).toSet()
            val myNumbers = splitString[1].ints()

            val winners = myNumbers.count { myNumber -> myNumber in winningNumbers }

            acc.mapIndexed { index, i -> if(index in idx + 1 .. idx + winners) i + acc[idx] else i }
        }.sum()
    }
}