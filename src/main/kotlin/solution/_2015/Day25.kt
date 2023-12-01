package solution._2015

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day25(val input: String) {
    private val goalRow = input.ints().first()
    private val goalCol = input.ints().last()

    private val start = 20151125L
    private val multiplier = 252533L
    private val divider = 33554393L

    fun solve1(): Long {
        return solve()
    }

    fun solve2():Long {
        return solve()
    }

    private fun solve():Long {
        var row = 1
        var col = 1
        var curVal = start
        while(row != goalRow || col != goalCol) {
            if(row == 1) {
                row = col + 1
                col = 1
            } else {
                row--
                col++
            }

            curVal = curVal * multiplier % divider
        }

        return curVal
    }
}