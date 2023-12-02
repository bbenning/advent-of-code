package solution._2016

import util.mapping.Coord
import util.mapping.Direction

class Day02(val input: List<String>) {
    fun solve1(): String {
        val keyboard = """
            123
            456
            789
        """
        return solve(fromStringBlockToKeyboard(keyboard), Coord(1,1))
    }

    fun solve2():String {
        val keyboard = """
              1
             234
            56789
             ABC
              D
        """

        return solve(fromStringBlockToKeyboard(keyboard), Coord(0,2))
    }

    private fun fromStringBlockToKeyboard(string: String): Map<Coord, Char> =
        string.trimIndent().lines().flatMapIndexed{row, str ->
            str.mapIndexedNotNull { col, c -> if(c==' ') null else Pair(Coord(col, row), c) }
        }.toMap()

    private fun solve(keyboard: Map<Coord, Char>, startCoord: Coord): String {
        return input.fold(listOf(startCoord)) { listOfCoords, str ->
            val newCoord = str.fold(listOfCoords.last()){coord, c ->
                val moveTo = when(c) {
                    'U'-> coord.move(Direction.NORTH)
                    'D'-> coord.move(Direction.SOUTH)
                    'L' -> coord.move(Direction.WEST)
                    'R' -> coord.move(Direction.EAST)
                    else -> throw IllegalArgumentException("Should never happen")
                }
                if(moveTo in keyboard) moveTo else coord
            }

            listOfCoords + newCoord
        }.drop(1).map{keyboard[it]!!}.joinToString("")
    }
}