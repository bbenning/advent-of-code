package solution._2024

import util.*
import util.mapping.Coord
import kotlin.math.abs

class Day20(val input: List<String>) {
    // Only need the fastest path and then measure possible "jumps" since it's a unique completely covering path.

    fun solve1(timeToSave: Int): Int {
        return solve(timeToSave, 2)
    }

    fun solve2(timeToSave: Int, cheatSize: Int): Int {
        return solve(timeToSave, cheatSize)
    }

    private val start = input.locateChar('S')
    private val end = input.locateChar('E')

    private val width = input.first().length
    private val height = input.size

    private fun solve(timeToSave: Int, cheatSize: Int): Int {

        val fastestPath = findPath()

        var count = 0

        for(sourceIdx in 0 until fastestPath.size - timeToSave) {
            val sourceCoord = fastestPath[sourceIdx]
            for(targetIdx in sourceIdx + 1 + timeToSave  until fastestPath.size) {
                val targetCoord = fastestPath[targetIdx]

                val distanceCoord = targetCoord - sourceCoord
                val distance = abs(distanceCoord.x) + abs(distanceCoord.y)
                val originalDistance = targetIdx - sourceIdx

                if(originalDistance - distance >= timeToSave && distance <= cheatSize) {
                    count++
                }
            }
        }

        return count
    }

    private fun findPath(): List<Coord> {
        val toProcess = mutableListOf(listOf(start))
        while(toProcess.isNotEmpty()) {
            val curPath = toProcess.removeFirst()
            val curLocation = curPath.last()

            if(curLocation == end) {
                return curPath
            }

            curLocation.neighbors().filter {
                it.x in 0 until width &&
                it.y in 0 until height &&
                input[it.y][it.x] != '#' &&
                it !in curPath }.forEach {
                toProcess.add(curPath + it)
            }
        }

        return emptyList()
    }
}