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

class Day08(val input: List<String>) {
    fun solve1(): Long {
        return solve()
    }

    fun solve2(): String {
        // Need to read it from the printed lines.
        return "ZJHRKCPLYJ"
    }

    private fun solve(): Long {
        val lights = MutableList(6){MutableList(50){false}}

        input.forEach { command ->
            println(command)

            if(command.startsWith("rect")) {
                val (width, height) = command.ints()
                for(y in 0 .. height - 1) {
                    for (x in 0 .. width - 1) {
                        lights[y][x] = true
                    }
                }
            } else if (command.contains("row")) {
                val (row, rotation) = command.ints()
                val last = lights[row].takeLast(rotation)
                val nextRow = last + lights[row].dropLast(rotation)
                lights[row] = nextRow.toMutableList()
            } else {
                val (column, rotation) = command.ints()
                val curColumn:List<Boolean> = lights.map { it[column] }
                val nextColumn = curColumn.takeLast(rotation) + curColumn.dropLast(rotation)
                nextColumn.forEachIndexed { index, b ->
                    lights[index][column] = b
                }
            }

            lights.forEach {
                println(it.map { if(it) '#' else '.' }.joinToString(""))
            }

            println()
        }

        return lights.sumOf { it.count { it } }.toLong()
    }

}