package solution._2024

import util.mapping.Coord
import util.mapping.Direction

class Day06(val input: List<String>) {

    private val startGuard: Guard
    private val height = input.size
    private val width = input.first().length

    data class Guard(val coord: Coord, val direction: Direction)

    init {
        val y = input.indexOfFirst { it.any { it !in setOf('.', '#') } }
        val x = input[y].indexOfFirst { it !in setOf('.', '#') }
        val direction = Direction.fromChar(input[y][x])
        startGuard = Guard(Coord(x, y), direction)
    }

    // is slow because of additions to the accumulator.
    private tailrec fun slowWalk(guard: Guard, acc: Set<Guard>, extraObstacle: Coord?): Set<Guard>? {
        val nextCoord = guard.coord.move(guard.direction)
        return if (nextCoord.x !in 0 until width || nextCoord.y !in 0 until height) {
            acc + guard
        } else if (guard in acc) {
            null
        } else if (input[nextCoord.y][nextCoord.x] == '#' || nextCoord == extraObstacle) {
            slowWalk(Guard(guard.coord, guard.direction.turnRight()), acc + guard, extraObstacle)
        } else {
            val nextGuard = Guard(nextCoord, guard.direction)
            slowWalk(nextGuard, acc + guard, extraObstacle)
        }
    }

    private fun fastWalk(guard: Guard, extraObstacle: Coord?): Set<Guard>? {

        val alreadySeen = mutableSetOf<Guard>()
        var curGuard = guard
        while (true) {
            if (curGuard in alreadySeen) {
                return null
            }

            alreadySeen.add(curGuard)

            val nextCoord = curGuard.coord.move(curGuard.direction)
            if (nextCoord.x !in 0 until width || nextCoord.y !in 0 until height) {
                break
            }

            if (input[nextCoord.y][nextCoord.x] == '#' || extraObstacle == nextCoord) {
                val nextDirection = curGuard.direction.turnRight()
                curGuard = Guard(curGuard.coord, nextDirection)
            } else {
                curGuard = Guard(nextCoord, curGuard.direction)
            }
        }

        alreadySeen.add(curGuard)
        return alreadySeen
    }

    fun solve1(): Int {
        return fastWalk(startGuard, null)!!.map { it.coord }.toSet().size
    }

    fun solve2(): Int {
        val regularPath = fastWalk(startGuard, null)!!
        val potentialExtraObstacles = regularPath.map { it.coord.move(it.direction) }.toSet()
        val obstacleCount = potentialExtraObstacles.count {
            it != startGuard.coord && fastWalk(startGuard, it) == null
        }

        return obstacleCount
    }
}
