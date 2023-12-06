package solution._2023

import util.*
import util.ints.product

private typealias Color = String
private typealias Amount = Int

class Day02(val input: List<String>) {

    fun solve1(): Int {
        val myCubes = mapOf("red" to 12, "green" to 13, "blue" to 14)

        return input.filter {
            val cubes: List<Pair<Color, Amount>> = it.words().drop(1).zip(it.ints().drop(1))
            val isPossible = cubes.all { myCubes.getOrDefault(it.first, 0) >= it.second }

            isPossible
        }.sumOf { it.ints().first() }
    }

    fun solve2():Long {
        return input.sumOf {
            val cubes: List<Pair<Color, Amount>> = it.words().drop(1).zip(it.ints().drop(1))
            val groupedByColor = cubes.groupBy { it.first }
            val maxPerColor = groupedByColor.mapValues { it.value.maxOf { it.second } }

            maxPerColor.values.product()
        }
    }
}


