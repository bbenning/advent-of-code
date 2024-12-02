package solution._2024

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.longs.product
import util.mapping.Coord
import util.mapping.Direction

class Day02(val input: List<String>) {
    private val inputNumbers = input.map { it.ints() }

    fun solve1(): Long {
        return inputNumbers.count {
            it.windowed(2).all { (a, b) -> a - b in 1 .. 3 } || it.windowed(2).all { (a, b) -> b - a in 1..3 }
        }.toLong()
    }

    private fun isValid(list: List<Int>, idx1: Int, idx2: Int, usedDampener: Boolean): Boolean {
        if(idx1 < 0) return false
        if(idx2 == list.size) return true

        val a = list[idx1]
        val b = list[idx2]

        return if(b - a in 1..3) {
            isValid(list, idx2, idx2 + 1, usedDampener)
        } else if (!usedDampener) {
            isValid(list, idx1 - 1, idx2, true) ||
            isValid(list, idx1, idx2 + 1, true) ||
            (idx1 == 0 && isValid(list, 1, 2, true))
        } else {
            false
        }
    }

    fun solve2(): Long {
        val result = inputNumbers.count {
            isValid(it, 0, 1, false) ||
            isValid(it.reversed(), 0, 1, false)
        }
        return result.toLong()
    }


}