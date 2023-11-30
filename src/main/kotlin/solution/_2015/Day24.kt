package solution._2015

import com.github.shiguruikai.combinatoricskt.powerset
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day24(val input: List<Int>) {
    fun solve1(): Long {
        return solve(3)
    }

    fun solve2():Long {
        return solve(4)
    }

    private fun solve(partitions: Int): Long {
        val goal = input.sum() / partitions

        var minGifts = Integer.MAX_VALUE
        var minQE = Long.MAX_VALUE

        fun searchBestArrangement(acc: Set<Int>, accSum: Int, numbers: List<Int>) {

            if(accSum == goal && (acc.size < minGifts || (acc.size == minGifts && acc.product() < minQE))) {
                minGifts = acc.size
                minQE = acc.product()
            } else if (accSum < goal && acc.size < minGifts) {
                numbers.forEachIndexed { index, number ->
                    searchBestArrangement(acc + number, accSum + number, numbers.drop(index + 1))
                }
            }
        }

        searchBestArrangement(emptySet(), 0, input.sortedBy { - it /* greedy */ })

        return minQE
    }
}