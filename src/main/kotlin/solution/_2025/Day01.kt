package solution._2025

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

class Day01(val input: List<String>) {
    fun solve1(): Int {
        val output = input.fold(listOf(50)) { acc, str ->
            val left = str.first() == 'L'

            acc.plus((acc.last() + str.drop(1).toInt() * if(left) -1 else 1).mod(100))
        }

        return output.count { it == 0 }
    }

    fun solve2(): Int {
        val output = input.fold(Pair(0, 50)) { acc, str ->
            val left = str.first() == 'L'

            val rotation = str.drop(1).toInt()

            // Not really the nicest.
            val howMuchToMoveForPast0 = if(acc.second == 0) {
                100
            } else if(left) {
                acc.second
            } else {
                100 - acc.second
            }

            val newCount = acc.first +
                    if(rotation >= howMuchToMoveForPast0) { 1 + (rotation - howMuchToMoveForPast0) / 100 } else 0

            val newValue = (acc.second + rotation * if(left) -1 else 1).mod(100)
            Pair(newCount, newValue)
        }

        return output.first
    }

}