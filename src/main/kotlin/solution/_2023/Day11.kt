package solution._2023

import com.github.shiguruikai.combinatoricskt.combinations
import com.github.shiguruikai.combinatoricskt.permutations
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

class Day11(val input: List<String>) {
    fun solve1(): Long {
        return solve(2L)
    }

    fun solve2(expansionFactor: Long = 1000000L):Long {
        return solve(expansionFactor)
    }

    private fun solve(expansionFactor: Long): Long {
        val verticalEmptyRowNumbers = input.first().mapIndexedNotNull { index, _ ->
            if(input.all{it[index] == '.'}) {
                index
            } else {
                null
            }
        }

        val horizontalEmptyRowNumbers = input.mapIndexedNotNull { index, s -> if(s.all { it == '.' }) index else null }

        val galaxyCoords = input.flatMapIndexed { y, row ->
            row.mapIndexedNotNull{x, c ->
                if(c == '#') {
                    Pair(
                        x + verticalEmptyRowNumbers.count{it < x} * (expansionFactor - 1),
                        y + horizontalEmptyRowNumbers.count{it < y} * (expansionFactor - 1)
                    )
                }
                else null
            }
        }

        val distances = galaxyCoords.combinations(2).map { (galaxy1, galaxy2) ->
            abs(galaxy1.first - galaxy2.first) + abs(galaxy1.second - galaxy2.second)
        }

        return distances.sum()
    }
}