package solution._2024

import util.mapping.Coord
import util.mapping.Direction

class Day16(val input: List<String>) {
    fun solve1(): Int {
        return solve(false)
    }

    fun solve2(): Int {
        return solve(true)
    }

    private fun solve(solve2: Boolean):Int {
        val endY = input.indexOfFirst { it.contains('E') }
        val endX = input[endY].indexOf('E')
        val startY = input.indexOfFirst { it.contains('S') }
        val startX = input[startY].indexOf('S')
        val width = input.first().length
        val height = input.size

        val end = Coord(endX, endY)
        val start = Coord(startX, startY)

        val toExplore = mutableListOf(Triple(listOf(start), Direction.EAST, 0))
        var bestScore = Integer.MAX_VALUE
        val bestScorePerCoord = mutableMapOf<Coord, Int>()
        val coordsOnBestPath = mutableSetOf<Coord>()

        while(toExplore.isNotEmpty()) {
            val (curPath, curDirection, score) = toExplore.removeFirst()
            val curCoord = curPath.last()

            if(curCoord == end) {
                if(score < bestScore) {
                    bestScore = score
                    coordsOnBestPath.clear()
                }

                if (score == bestScore) {
                    coordsOnBestPath.addAll(curPath)
                }

                continue
            }


            fun validCoord(coord: Coord, score: Int): Boolean =
                coord.x in 0 until width &&
                        coord.y in 0 until height &&
                        input[coord.y][coord.x] != '#' &&
                        coord !in curPath &&
                        (bestScorePerCoord[coord]?:Integer.MAX_VALUE) >= (score - 1001)

            val straightAheadCoord = curCoord.move(curDirection)
            if(validCoord(straightAheadCoord, score + 1)) {
                toExplore.add(Triple(curPath + straightAheadCoord, curDirection, score + 1))
                bestScorePerCoord[straightAheadCoord] = score + 1
            }

            val directionClockwise = curDirection.turnRight()
            val clockwiseCoord = curCoord.move(directionClockwise)
            if(validCoord(clockwiseCoord, score + 1001)) {
                toExplore.add(Triple(curPath + clockwiseCoord, directionClockwise, score + 1001))
                bestScorePerCoord[clockwiseCoord] = score + 1001
            }

            val directionCountclockwise = curDirection.turnLeft()
            val counterclockwiseCoord = curCoord.move(directionCountclockwise)
            if(validCoord(counterclockwiseCoord, score + 1001)) {
                toExplore.add(Triple(curPath + counterclockwiseCoord, directionCountclockwise, score + 1001))
                bestScorePerCoord[counterclockwiseCoord] = score + 1001
            }
        }

        return if(solve2) {
            coordsOnBestPath.size
        } else {
            bestScore
        }
    }

}