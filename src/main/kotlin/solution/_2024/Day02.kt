package solution._2024

import util.*

class Day02(val input: List<String>) {
    private val reports = input.map { it.ints() }

    private fun isValid(list: List<Int>): Boolean =
        list.zipWithNext { a, b -> a - b }
            .let {
                it.all { d -> d in 1..3 } || it.all { d -> d in -3 .. -1 }
            }

    fun solve1(): Long {
        return reports.count{ isValid(it) }.toLong()
    }

    fun solve2(): Long {
        val result = reports.count { levels ->
            levels.indices.any { filterLevelIdx -> isValid(levels.filterIndexed { levelIdx, _ -> levelIdx != filterLevelIdx}) }
        }
        return result.toLong()
    }
}