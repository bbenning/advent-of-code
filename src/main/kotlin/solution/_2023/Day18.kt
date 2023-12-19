package solution._2023

import util.mapping.Coord
import util.mapping.Direction
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.round

class Day18(val input: List<String>) {
    fun solve1(): Long {
        val instructions = input.map{
            val (directionStr, stepsStr) = it.split(" ")
            val steps = stepsStr.toInt()
            val direction = Direction.fromChar(directionStr.first(), "URDL")
            Pair(steps, direction)
        }

        return solve(instructions)
    }

    fun solve2(): Long {
        val instructions = input.map{
            val hex = it.split(" ").last().drop(2).dropLast(1)
            val directionStr = hex.last().let { Direction.fromChar(it, "3012") }
            val steps = hex.dropLast(1).toInt(16)

            Pair(steps, directionStr)
        }

        return solve(instructions)
    }

    fun solve(instructions: List<Pair<Int, Direction>>): Long {
        val path = createPath(instructions)
        val innerSize = calculateInnerSize(path)
        val pathLength = pathLength(path)

        // not exactly right, since the path itself has a width, so you cut off too much from inner, but not enough from path
        // Correct it:
        return (innerSize + ceil(pathLength / 2.0)).toLong() + 1
    }

    // Only the endpoints necessary
    private fun createPath(instructions: List<Pair<Int, Direction>>): List<Coord> {
        fun draw(path: List<Coord>, instructions: List<Pair<Int, Direction>>): List<Coord> {

            if(instructions.isEmpty()) {
                return path
            }

            val (steps, direction) = instructions.first()

            val unitMovement = Coord.origin().move(direction)

            val x = path.last().x + steps * unitMovement.x
            val y = path.last().y + steps * unitMovement.y
            val nextCoord = Coord(x, y)

            return draw(path + nextCoord, instructions.drop(1))
        }

        return draw(listOf(Coord(0,0)), instructions)
    }

    private fun calculateInnerSize(path: List<Coord>): Long {

        // according to the shoelace theorem, we can take 2 points in order
        // then create a matrix and calculate its determinant and sum them up ..
        // what the fuck??

        return abs(path.windowed(2, 1).sumOf { coords ->
            val (c1, c2) = coords

            val determinant = c1.x.toLong() * c2.y - c1.y.toLong() * c2.x
            determinant
        }) / 2
    }

    private fun pathLength(path: List<Coord>): Long {
        return path.windowed(2, 1).fold(0L) { distance, (c1, c2) ->
            distance + c1.manhattenDistance(c2)
        }
    }

}