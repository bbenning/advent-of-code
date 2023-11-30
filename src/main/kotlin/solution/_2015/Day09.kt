package solution._2015

import com.github.shiguruikai.combinatoricskt.permutations
import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day09(val input: List<String>) {

    private data class Route(val from: String, val to: String, val distance: Int )

    private val routes:Map<Pair<String, String>, Int> = input.flatMap {
        val words = it.alphanumerics()
        listOf(Route(words[0], words[2], words[3].toInt()), Route(words[2], words[0], words[3].toInt()))
    }.associate { Pair(it.from, it.to) to it.distance }

    private val cities = routes.map { it.key.first }.toSet()

    private val distances = cities.permutations().map { it.windowed(2).sumOf { routes[Pair(it[0], it[1])]!! } }.sorted()

    fun solve1(): Int {
        return distances.first()
    }

    fun solve2():Int {
        return distances.last()
    }

}