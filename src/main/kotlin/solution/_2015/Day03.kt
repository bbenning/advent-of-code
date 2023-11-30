package solution._2015

import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day03(val input: List<String>) {
    fun solve1(): Int {
        return solve(input.first()).toSet().size
    }

    fun solve2():Int {
        val evenChars: String = input.first().filterIndexed { index, _ -> index % 2 == 0 }
        val oddChars = input.first().filterIndexed { index, _ -> index % 2 != 0 }
        return (solve(evenChars) + solve(oddChars)).toSet().size
    }

    private fun solve(input: String):List<Coord> {
        return input.fold(listOf(Coord.origin())){acc, c ->
            acc + acc.last().move(Direction.fromChar(c))
        }
    }
}