package solution._2024

import util.mapping.Coord

class Day10(val input: List<String>) {
    fun solve1(): Int {
        return solve(false)
    }

    fun solve2(): Int {
        return solve(true)
    }

    private val trailheads = input.flatMapIndexed { y, s -> s.mapIndexedNotNull { x, c -> if(c == '0') Coord(x, y) else null} }
    private val width = input.first().length
    private val height = input.size

    private fun solve(uniquePaths: Boolean): Int {
        val paths = mutableListOf<Pair<Coord, Coord>>()
        val uniqueEnds = mutableSetOf<Pair<Coord, Coord>>()
        val uniquePathsToEnd = mutableListOf<Pair<Coord, Coord>>()

        paths.addAll(trailheads.map { Pair(it, it) })
        while(paths.isNotEmpty()) {
            val curPath = paths.removeFirst()
            val curHeight = input[curPath.second.y][curPath.second.x].digitToInt()
            if(curHeight == 9) {
                uniqueEnds.add(curPath)
                uniquePathsToEnd.add(curPath)
                continue
            }

            val neighbors = curPath.second.neighbors().filter {
                it.x in 0 until width && it.y in 0 until height && input[it.y][it.x].digitToInt() == curHeight + 1
            }

            paths.addAll(neighbors.map { Pair(curPath.first, it) })
        }
        return if(uniquePaths) uniquePathsToEnd.size else uniqueEnds.size
    }
}