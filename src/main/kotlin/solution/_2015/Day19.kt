package solution._2015

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day19(val input: List<String>) {
    /**
     * Got this myself by hand at some point, and I don't like this solution for part 2, since it's not really
     * guaranteed I think to get to the solution. But I suppose it works for most inputs.
     *
     * This has been copied from https://github.com/0legg/adventofcode/blob/master/year2015/src/main/kotlin/net/olegg/aoc/year2015/day19/Day19.kt
     * So all credits belong to him.
     */

    private val transitions = input
        .dropLast(2)
        .map { it.split(" => ") }
        .map { it.first() to it.last() }
    private val molecule = input.last()

    private fun applyTransitions(molecule: String, transition: Pair<Regex, String>): Set<String> {
        val (regex, replacement) = transition
        return regex.findAll(molecule)
            .map { molecule.replaceRange(it.range, replacement) }
            .toSet()
    }

    fun solve1(): Int {
        return transitions
            .map { it.first.toRegex() to it.second }
            .flatMap { applyTransitions(molecule, it) }
            .toSet()
            .size
    }

    fun solve2(): Int {
        val reverse = transitions.map { it.second.toRegex() to it.first }
        val molecules = mutableMapOf(molecule to 0)
        val queue = ArrayDeque(listOf(molecule))
        while ("e" !in molecules && queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            val size = molecules.getOrDefault(curr, Int.MAX_VALUE)
            val next = reverse.flatMap { applyTransitions(curr, it) }.sortedBy { it.length }.take(2) // greedy approach
            queue += next.filterNot { it in molecules }
            molecules += next.associateWith { (molecules.getOrDefault(it, Int.MAX_VALUE)).coerceAtMost(size + 1) }
        }
        return molecules.getOrDefault("e", Int.MAX_VALUE)
    }
}