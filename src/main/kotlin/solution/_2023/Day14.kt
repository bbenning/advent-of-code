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

class Day14(val input: List<String>) {
    fun solve1(): Long {

        val result = roll(roundRocks, Direction.NORTH)

        return result.sumOf { input.size - it.y  }.toLong()
    }

    fun solve2(repeats: Int):Long {

        val memoization = mutableMapOf<Set<Coord>, Pair<Set<Coord>, Int>>()

        var count = 0
        var curRoundRocks = roundRocks
        while (count < repeats) {
            val memoized = memoization[curRoundRocks]
            curRoundRocks = if(memoized == null) {
                val resNorth = roll(curRoundRocks, Direction.NORTH)
                val resWest = roll(resNorth, Direction.WEST)
                val resSouth = roll(resWest, Direction.SOUTH)
                val resEast = roll(resSouth, Direction.EAST)

                memoization[curRoundRocks] = Pair(resEast, count)
                count++
                resEast
            } else {
                val loopSize = count - memoized.second
                val loopCount = (repeats - memoized.second + 1) / loopSize
                val newCount = memoized.second + loopSize * (loopCount - 1) + 1
                count = newCount
                memoization.clear()
                memoized.first
            }
        }

        return curRoundRocks.sumOf { input.size - it.y  }.toLong()
    }

    val roundRocks = input.flatMapIndexed{y, row ->
        row.mapIndexedNotNull { x, c -> if(c == 'O') Coord(x, y) else null }
    }.toSet()
    val cubeRocks = input.flatMapIndexed{y, row ->
        row.mapIndexedNotNull { x, c -> if(c == '#') Coord(x, y) else null }
    }.toSet()

    private fun roll(roundRocks: Set<Coord>, direction: Direction): Set<Coord> {

        val curStateRoundRocks = roundRocks.toMutableSet()

        var changed: Boolean
        do {
            changed = false
            val roundRockCoordsNorth1: Set<Pair<Coord, Coord>> =
                curStateRoundRocks.map { Pair(it, it.move(direction)) }
                    .filter{it.second.y >= 0 && it.second.x >= 0 && it.second.y < input.size && it.second.x < input.first().length}
                    .toSet()

            roundRockCoordsNorth1.forEach {
                if(it.second !in cubeRocks && it.second !in curStateRoundRocks) {
                    curStateRoundRocks.remove(it.first)
                    curStateRoundRocks.add(it.second)
                    changed = true
                }
            }

        } while (changed)

        return curStateRoundRocks
    }

    private fun solve():Long {
        return 0
    }
}