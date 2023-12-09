package solution._2023

import util.*
import util.longs.product
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

class Day06(val input: List<String>) {
    fun solve1(): Long {
        return solve()
    }

    fun solve2(): Long {
        return solve()
    }

    private fun solve(): Long {
        val times = input.first().longs()
        val distances = input[1].longs()

        return times.zip(distances).map{ (time, recordDistance) ->

            val zeroPoint1 = (time - sqrt(time*time - 4.0 * recordDistance)) / 2.0
            val zeroPoint2 = (time + sqrt(time*time - 4.0 * recordDistance)) / 2.0

            val recordTime1 = floor(zeroPoint1 + 1).toLong()
            val recordTime2 = ceil(zeroPoint2 - 1).toLong()

            recordTime2 - recordTime1 + 1
        }.product()
    }
}