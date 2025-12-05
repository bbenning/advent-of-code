package solution._2025

import util.*

class Day05(val input: List<String>) {
    fun solve1(): Long {
        val ingredients = input.takeLastWhile { it.isNotEmpty() }.map { it.toLong() }

        val freshIngredients = ingredients.filter { ingredient -> freshRanges.any { range -> range.contains(ingredient) }}

        return freshIngredients.size.toLong()
    }

    fun solve2(): Long {
        val numberOfFreshIngredients = freshRanges.map { it.last - it.first + 1 }

        return numberOfFreshIngredients.sum()
    }

    private val freshRanges: List<LongRange> = RangeUtil.unionAllLongRanges(input.takeWhile { it.isNotEmpty() }.map {
        val (a, b) = it.split("-").map { it.toLong() }
        LongRange(a, b)
    })
}