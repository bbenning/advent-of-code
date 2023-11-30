package solution._2015

import util.*

class Day16(val input: List<String>) {

    private val compounds = """children: 3 cats: 7 samoyeds: 2 pomeranians: 3 akitas: 0 vizslas: 0 goldfish: 5 trees: 3 cars: 2 perfumes: 1"""
        .alphanumerics().chunked(2).associate { Pair(it.first(), it.last().toInt()) }

    fun solve1(): Int {
        val validSues = sues.filter { (_, things) ->
            things.all { compounds[it.key] == it.value }
        }
        return validSues.first().first
    }

    fun solve2():Int {
        val validSues = sues.filter { (_, things) ->
            things.all {
                val count = compounds[it.key]!!
                when (it.key) {
                    in listOf("cats", "trees") -> count < it.value
                    in listOf("pomerians", "goldfish") -> count > it.value
                    else -> count == it.value
                }
            }
        }
        return validSues.first().first
    }

    private val sues = input.map {
        val ints = it.ints()
        val words = it.words()

        val sueNumber = ints.first()
        Pair(
            sueNumber,
            mapOf(
                words[1] to ints[1],
                words[2] to ints[2],
                words[3] to ints[3]
            )
        )
    }
}