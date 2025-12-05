package solution._2025

import util.mapping.Coord

class Day04(val input: List<String>) {
    fun solve1(): Int {
        val map:List<List<Int>> = input.map { it.map{ if(it  == '@') 1 else 0 }}
        val coordinates = solve(map)
        return coordinates.size
    }


    fun solve2(): Int {
        val map:List<MutableList<Int>> = input.map { it.map{ if(it  == '@') 1 else 0 }.toMutableList()}

        var count = 0
        do {
            val coords = solve(map)
            coords.forEach { coord ->
                map[coord.y][coord.x] = 0
            }
            count += coords.size
        } while(coords.isNotEmpty())

        return count
    }

    private fun solve(maps: List<List<Int>>): List<Coord> {
        val directions = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1, 0 to 1,
            1 to -1, 1 to 0, 1 to 1
        )

        val rows = maps.size
        val cols = maps.first().size

        val coords = mutableListOf<Coord>()

        for (y in 0 until rows) {
            for (x in 0 until cols) {
                var neighbors = 0
                if (maps[y][x] == 1) {
                    for ((dY, dX) in directions) {
                        val newY = y + dY
                        val newX = x + dX
                        if (newY in 0 until rows && newX in 0 until cols) {
                            neighbors += maps[newY][newX]
                        }
                    }

                    if (neighbors < 4) {
                        coords.add(Coord(x, y))
                    }
                }
            }
        }
        return coords
    }

}