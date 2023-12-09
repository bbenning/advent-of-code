package solution._2023

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

class Day09(val input: List<String>) {
    fun solve1(): Long {
        return input.sumOf { solve(it.longs()) }
    }

    fun solve2():Long {
        return input.sumOf { solve(it.longs().reversed()) }
    }

    private fun solve(numbers: List<Long>): Long {
        if(numbers.all { it == 0L}) return 0

        val newSeries = numbers.windowed(2).map { (left, right) ->
            right - left
        }
        return solve(newSeries) + numbers.last()
    }
}