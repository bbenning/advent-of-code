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
class Day10(val input: List<String>) {
    val startY = input.indexOfFirst { it.contains("S") }
    val startX = input[startY].indexOf("S")

    private val start = Coord(startX, startY)

    fun canMoveFromTo(start: Coord, to: Coord): Boolean {
        if(to.x < 0 || to.y < 0 || to.x >= input[0].length || to.y >= input.size) return false

        val startChar = input[start.y][start.x]
        val toChar = input[to.y][to.x]
        return if(start.move(Direction.NORTH) == to) {
            startChar in setOf('|', 'L', 'J', 'S') && toChar in setOf('|', '7', 'F', 'S')
        } else if (start.move(Direction.EAST) == to) {
            startChar in setOf('-', 'L', 'F', 'S') && toChar in setOf('-', 'J', '7', 'S')
        } else if (start.move(Direction.SOUTH) == to) {
            startChar in setOf('|', '7', 'F', 'S') && toChar in setOf('|', 'L', 'J', 'S')
        } else if (start.move(Direction.WEST) == to) {
            startChar in setOf('-', 'J', '7', 'S') && toChar in setOf('-', 'L', 'F', 'S')
        } else {
            false
        }
    }

    fun solve1(): Long {
        return solve().size / 2L
    }


    fun solve2():Int {
        // I think I need to solve this by using the direction of the path... So you always know left side of the path
        // is out and right-side in or vice versa.

        val path = solve()

        // I guess let's start with a canvas and color it in...
        val canvas = input.mapIndexed { y, row-> row.mapIndexed { x, _ -> if(Coord(x, y) in path) 'x' else '.' }.toMutableList() }

        path.windowed(2).forEach{window ->
            val (first, second) = window
            // Determine direction, so we know left and right
            val directionHandedness = when (second) {
                first.move(Direction.EAST) -> {
                    Pair(true, "LR") // in north-south direction, north = left
                }
                first.move(Direction.WEST) -> {
                    Pair(true, "RL") // in north-south direction, north = right
                }
                first.move(Direction.NORTH) -> {
                    Pair(false, "LR") // in west-east direction, west = left
                }
                first.move(Direction.SOUTH) -> {
                    Pair(false, "RL") // in west-east direction, west = right
                }
                else -> throw IllegalStateException("Could not happen")
            }

            listOf(first, second).forEach { 
                val sidesHandedness = if(directionHandedness.first) {
                    Pair(it.move(Direction.NORTH), it.move(Direction.SOUTH))
                } else {
                    Pair(it.move(Direction.WEST), it.move(Direction.EAST))
                }.toList().zip(directionHandedness.second.toList()).filter{it.first.x >= 0 && it.first.x < input[0].length && it.first.y >= 0 && it.first.y < input.size}

                sidesHandedness.forEach {
                    if(it.first in path) {
                        canvas[it.first.y][it.first.x] = 'x'
                    } else {
                        canvas[it.first.y][it.first.x] = it.second
                    }
                }
            }
        }

        while(canvas.any{it.contains('.')}) {
            canvas.forEachIndexed{y, row ->
                // each line can now be drawn in ...
                row.forEachIndexed {x, c ->
                    if(c == '.') {
                        val neighbors = Coord(x,y).neighbors().filter{it.x >= 0 && it.x < input[0].length && it.y >= 0 && it.y < input.size}.map { canvas[it.y][it.x]}.filter { it in setOf('R', 'L') }.toSet()
                        if(neighbors.size > 1) {
                            throw IllegalStateException("Should not be possible.")
                        } else if (neighbors.size == 1 ){
                            canvas[y][x] = neighbors.first()
                        }
                    }
                }
            }
        }

        val outside = canvas[0][0]
        val inside = if(outside == 'R') 'L' else 'R'

        val result = canvas.map { it.count{it == inside} }.sum()

        return result
    }

    private fun solve(): List<Coord> {
        val toProcess: ArrayDeque<List<Coord>> = ArrayDeque()
        toProcess.add(listOf(start))

        val paths = mutableListOf<List<Coord>>()

        while(toProcess.isNotEmpty()) {
            val processing = toProcess.removeFirst()
            val curCoord = processing.last()
            val neighbors = curCoord.neighbors().filter { canMoveFromTo(curCoord, it) }

            neighbors.forEach { neighbor ->
                if(neighbor == start && processing.size > 2) {
                    paths.add(processing + neighbor)
                } else if(!processing.contains(neighbor)){
                    toProcess.add(processing + neighbor)
                }
            }
        }

        return paths.first()
    }
}