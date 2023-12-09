package solution._2023

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.longs.product
import util.mapping.Coord
import util.mapping.Direction

class Day07(val input: List<String>) {
    enum class Rank{
                   HIGH_CARD,ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND,FULL_HOUSE,FOUR_OF_A_KIND,FIVE_OF_A_KIND
    }

    fun solve1(): Long {
        return solve(false)
    }

    fun solve2():Long {
        return solve(true)
    }

    private fun solve(withJokers: Boolean):Long {
        val cardsWithBets = input.map{
            val (cards, betStr) = it.split(" ")
            Pair(cards, betStr.toInt())
        }

        val cardsRanked = cardsWithBets.sortedWith(Comparator { cards1, cards2 ->
            compare(cards1.first, cards2.first, withJokers)
        })

        return cardsRanked.mapIndexed { index, pair ->
            pair.second.toLong() * ( index + 1)
        }.sum()
    }

    private fun getRank(cards: String, withJokers: Boolean = false): Rank {

        val cardCounts = cards.groupBy { it }.mapValues { it.value.size }

        val jokerCount = if(withJokers) cardCounts['J']?:0 else 0
        val jokerNormalizedCardCounts = cardCounts.filter { !withJokers || it.key != 'J' }

        val highestCount = (jokerNormalizedCardCounts.values.maxOrNull() ?: 0) + jokerCount
        val secondHighestCount = jokerNormalizedCardCounts.map { it.value }.sorted().reversed().getOrElse(1){0}

        val rank: Rank = if(highestCount == 5) {
            Rank.FIVE_OF_A_KIND
        } else if (highestCount == 4) {
            Rank.FOUR_OF_A_KIND
        } else if (highestCount == 3 && secondHighestCount == 2) {
            Rank.FULL_HOUSE
        } else if (highestCount == 3) {
            Rank.THREE_OF_A_KIND
        } else if (highestCount == 2 && secondHighestCount == 2) {
            Rank.TWO_PAIR
        } else if (highestCount == 2) {
            Rank.ONE_PAIR
        } else {
            Rank.HIGH_CARD
        }

        return rank
    }

    private fun compare(cards1: String, cards2: String, withJokers: Boolean):Int {
        val rank1 = getRank(cards1, withJokers)
        val rank2 = getRank(cards2, withJokers)

        val comparison = rank1.compareTo(rank2)
        if(comparison!= 0) {
            return comparison
        }

        return cards1.zip(cards2).fold(0) {acc, (c1, c2) ->
            if(acc != 0) {
                acc
            } else {
                val idx1 = "23456789TJQKA".indexOf(c1).let{if(withJokers && c1 == 'J') -1 else it}
                val idx2 = "23456789TJQKA".indexOf(c2).let{if(withJokers && c2 == 'J') -1 else it}

                idx1 - idx2
            }
        }
    }
}