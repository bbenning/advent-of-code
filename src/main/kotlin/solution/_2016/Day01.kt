package solution._2016

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.mapping.Coord
import util.mapping.Direction
import kotlin.math.abs

class Day01(val input: String) {

    fun solve1(): Int {
        return solve().last().manhattenDistance()
    }

    fun solve2():Int {
        return solve().findFirstDuplicate()!!.manhattenDistance()
    }

    private fun solve():List<Coord> {
        return input.split(", ").fold(Pair(listOf(Coord(0,0)), Direction.NORTH)){acc, i ->
            val turn = i.first()
            val steps = i.substring(1).toInt()

            val newDirection = acc.second.turn(turn)
            Pair(acc.first + acc.first.last().move(newDirection, steps), newDirection)
        }.first
    }
}