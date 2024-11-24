package solution._2023

import util.mapping.Coord

class Day21(val input: List<String>) {
    fun solve1(steps: Int): Int {
        return solve(steps).size
    }

    fun solve2(steps: Int): Long {
        return solveInfinite(steps)
    }

    private val startY= input.indexOfFirst { it.contains("S") }
    private val startX = input[startY].indexOf('S')
    private val start = Coord(startX, startY)

    private fun solve(steps: Int): Set<Coord> {
        val possibleSteps = (1..steps).fold(setOf(start)) { acc, _ ->
            acc.flatMap { coord ->
                coord.neighbors().filter { it.x >= 0 && it.y >= 0 && it.x < input.first().length && it.y < input.size && input[it.y][it.x] != '#' }
            }.toSet()
        }

        return possibleSteps
    }

    private fun solveInfinite(steps: Int): Long {

        val time = System.currentTimeMillis()

        val (curPair, prevPair) = (1 .. steps).fold(Pair(Pair(setOf(start), 1L), Pair(setOf<Coord>(), 0L))) { (prevPair, prevPrevPair), step ->
            val (prevChanges, prevCount) = prevPair
            val (prevPrevChanges, prevPrevCount) = prevPrevPair

//            val newChanges = mutableSetOf<Coord)

            val newChanges = prevChanges.flatMap {
                it.neighborsList()
            }.filter {
                val modX = it.x.mod(input.first().length)
                val modY = it.y.mod(input.size)

                input[modY][modX] != '#' && it !in prevPrevChanges
            }.toSet()
            val newCount = prevPrevCount + newChanges.size

            if(step % 1000 == 0) {
                val time2 = System.currentTimeMillis()
                println("Step ${step} took ${time2 - time} ms in total.")
            }

            Pair(Pair(newChanges,newCount), prevPair)
        }


        return curPair.second
    }
}