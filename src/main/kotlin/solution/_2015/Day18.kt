package solution._2015

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day18(val input: List<String>) {
    fun solve1(steps: Int): Int {
        val rounds = solve(input.map { it.map { it == '#' } }, steps, false)
        return rounds.sumOf { it.count { it } }
    }

    fun solve2(steps: Int):Int {
        return solve(input.map { it.map { it == '#' } }, steps, true).sumOf { it.count { it } }
    }

    private tailrec fun solve(grid: List<List<Boolean>>, steps: Int, cornersOn: Boolean):List<List<Boolean>> {

        if(steps == 0) {
            return grid
        }

        val newGrid = grid.mapIndexed{y, row ->
            row.mapIndexed{x, light ->
                val neighborsOn = grid.countNeighbors(Coord(x, y)){it}

                (grid[y][x] && neighborsOn in listOf(2,3) ) || (!grid[y][x] && neighborsOn == 3)
                        || (cornersOn && grid.isCorner(Coord(x,y)))
            }
        }

        return solve(newGrid, steps - 1, cornersOn)
    }

}