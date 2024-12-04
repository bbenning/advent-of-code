package solution._2024

import util.mapping.Coord

class Day04(val input: List<String>) {
    fun solve1(): Int {
        fun find(xmas: List<Char>, coord: Coord, directionalCoord: Coord): Boolean {
            if(xmas.isEmpty()) {
                return true
            }

            if(coord.x !in input.first().indices || coord.y !in input.indices) {
                return false
            }

            return input[coord.y][coord.x] == xmas.first() && find(xmas.drop(1), coord + directionalCoord, directionalCoord)
        }

        val xmasCount = input.indices.sumOf { y ->
            input.first().indices.sumOf { x ->
                Coord.directionsDiagonally().count { directionalCoord ->
                    find("XMAS".toList(), Coord(x, y), directionalCoord)
                }
            }
        }

        return xmasCount
    }

    fun solve2(): Int {
        var count = 0
        for(y in 0 until input.size - 2) {
            for(x in 0 until input.first().length - 2) {

                val diag1 = "${input[y][x]}${input[y+1][x+1]}${input[y+2][x+2]}"
                val diag2 = "${input[y][x+2]}${input[y+1][x+1]}${input[y+2][x]}"

                if((diag1 == "MAS" || diag1 == "SAM") && (diag2 == "MAS" || diag2 == "SAM")) {
                    count++
                }
            }
        }

        return count
    }
}