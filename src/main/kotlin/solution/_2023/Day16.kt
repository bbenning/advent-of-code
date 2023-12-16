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

class Day16(val input: List<String>) {
    fun solve1(): Int {
        val start = Pair(Coord(-1,0), Direction.EAST)
        return solve(start)
    }

    fun solve2():Int {
        val fromTop = (0 until input.first().length).map { Pair(Coord(it, -1), Direction.SOUTH) }
        val fromBottom = (0 until input.first().length).map { Pair(Coord(it, input.size), Direction.NORTH) }
        val fromRight = (0 until input.size).map{Pair(Coord(input.first().length, it), Direction.EAST)}
        val fromLeft = (0 until input.size).map{Pair(Coord(-1, it), Direction.WEST)}
        return (fromTop + fromBottom + fromRight + fromLeft).map { solve(it) }.max()
    }

    private fun solve(start: Pair<Coord, Direction>):Int {

        val visited = mutableSetOf<Pair<Coord, Direction>>()
        val arrayDeque = ArrayDeque<Pair<Coord, Direction>>()
        arrayDeque.add(start)

        while(arrayDeque.isNotEmpty()) {
            val (curCoord, curDir) = arrayDeque.removeFirst()

            val nextCoord = curCoord.move(curDir)
            if(nextCoord.x < 0 || nextCoord.y < 0 || nextCoord.x >= input.first().length || nextCoord.y >= input.size) {
                continue // can't continue out of bounds
            }

            val point = input[nextCoord.y][nextCoord.x]

            val directions = when(point) {
                '.' -> listOf(curDir)
                '/' -> {
                    when(curDir){
                        Direction.EAST -> listOf(Direction.NORTH)
                        Direction.NORTH -> listOf(Direction.EAST)
                        Direction.SOUTH -> listOf(Direction.WEST)
                        Direction.WEST -> listOf(Direction.SOUTH)
                    }
                }
                '\\' -> {
                    when(curDir){
                        Direction.EAST -> listOf(Direction.SOUTH)
                        Direction.NORTH -> listOf(Direction.WEST)
                        Direction.SOUTH -> listOf(Direction.EAST)
                        Direction.WEST -> listOf(Direction.NORTH)
                    }
                }
                '-' ->
                    when(curDir){
                        Direction.EAST -> listOf(curDir)
                        Direction.WEST -> listOf(curDir)
                        Direction.NORTH -> listOf(Direction.EAST, Direction.WEST)
                        Direction.SOUTH -> listOf(Direction.EAST, Direction.WEST)
                    }
                '|' -> {
                    when(curDir){
                        Direction.EAST -> listOf(Direction.SOUTH, Direction.NORTH)
                        Direction.WEST -> listOf(Direction.SOUTH, Direction.NORTH)
                        Direction.NORTH -> listOf(curDir)
                        Direction.SOUTH -> listOf(curDir)
                    }
                }
                else -> throw IllegalStateException("Should never happen")
            }

            val nextBeams = directions.map { Pair(nextCoord, it) }.filter{it !in visited}

            visited.addAll(nextBeams)
            arrayDeque.addAll(nextBeams)
        }

        return visited.map { it.first }.toSet().size
    }
}