package solution._2024

import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day18(val input: List<String>, val size: Int, private val numBytes: Int) {

    fun solve1(): Int {
        val bytes = input.take(numBytes).map {
            val (x, y) = it.ints()
            Coord(x, y)
        }.toSet()

        return solve(bytes).size - 1 // steps, not coords
    }

    fun solve2(): String {
        val curBytes = mutableSetOf<Coord>()
        var solution = solve(curBytes)
        for(n in 0 .. input.size) {
            val curInput = input[n]
            val byte = Coord(curInput.ints()[0], curInput.ints()[1])

            curBytes.add(byte)
            if(byte in solution) {
                // only recalculate once a byte fell onto the current path
                solution = solve(curBytes)
                if(solution.isEmpty()) {
                    return curInput
                }
            }
        }

        return "failed"
    }

    private fun solve(bytes: Set<Coord>): List<Coord> {
        val toExplore = mutableListOf(Pair(listOf(Coord.origin()), Direction.EAST), Pair(listOf(Coord.origin()), Direction.SOUTH))
        val explored = mutableSetOf(Coord.origin())

        while(toExplore.isNotEmpty()) {
            val (curPath, curDirection) = toExplore.removeFirst()
            val curCoord = curPath.last()

            if(curCoord == Coord(size - 1, size - 1)) {
                return curPath
            }

            listOf(curDirection, curDirection.turnRight(), curDirection.turnLeft()).map {
                Pair(curCoord.move(it), it)
            }.filter {
                it.first.x in 0 until size && it.first.y in 0 until size && it.first !in explored && it.first !in bytes
            }.forEach {
                toExplore.add(Pair(curPath + it.first, it.second))
                explored.add(it.first)
            }
        }

        return emptyList()
    }
}