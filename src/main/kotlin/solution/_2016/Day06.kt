package solution._2016

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

class Day06(val input: List<String>) {
    fun solve1(): String {
        return solve(true)
    }

    fun solve2(): String {
        return solve(false)
    }

    private fun solve(max: Boolean): String {
        val listOfCharMaps = List(input.first().length){ mutableMapOf<Char, Int>() }

        input.forEach{ str ->
            str.forEachIndexed { index, c ->
                val count = listOfCharMaps[index][c]?:0
                listOfCharMaps[index][c] = count + 1
            }
        }

        val maxCharPerPos = listOfCharMaps.map {
            if(max) {
                it.maxBy { it.value }.key
            } else {
                it.minBy { it.value }.key
            }
        }

        return maxCharPerPos.joinToString("")
    }
}