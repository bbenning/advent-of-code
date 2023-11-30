package solution._2015

import com.github.shiguruikai.combinatoricskt.derangements
import util.*

class Day13(val input: List<String>) {

    fun solve1(): Int {
        return solve(input)
    }

    fun solve2():Int {
        val names = input.map{it.words().first()}.toSet()
        val additional = names.map { "Bas would gain 0 happiness units by sitting next to $it" }

        return solve(input + additional)
    }

    private fun solve(lines: List<String>):Int {
        val names = lines.map{it.words().first()}.toSet()

        val happiness = lines.associate {
            val name1 = it.words().first()
            val name2 = it.words().last()
            val pairingHappiness = it.ints().first() * if (it.contains("lose")) -1 else 1
            Pair(Pair(name1, name2), pairingHappiness)
        }.withDefault { 0 }

        return names.derangements().map {
            it.windowed(2).sumOf { pairing ->
                happiness.getValue(pairing.toPair()) +
                happiness.getValue(pairing.reversed().toPair())
            } + happiness.getValue(Pair(it.first(), it.last())) + happiness.getValue(Pair(it.last(), it.first()))
        }.max()
    }

}