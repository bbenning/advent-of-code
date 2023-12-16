package solution._2023

import util.*

class Day12(val input: List<String>) {
    fun solve1(): Long {
        return solve(input)
    }

    fun solve2(): Long {
        val newInput: List<String> = input.map {
            val (springs, groups) = it.split(" ")
            List(5){springs}.joinToString("?") + " " + List(5){groups}.joinToString(",")
        }

        return solve(newInput)
    }

    private fun solve(inputs: List<String>): Long {
        return inputs.sumOf {
            val (springs, groupsStr) = it.split(" ")
            val groups = groupsStr.ints()
            solve(springs, groups)
        }
    }

    private val solved = mutableMapOf<Pair<String, List<Int>>, Long>()

    private fun solve(mask: String, groups: List<Int>): Long {
        fun solveAcc(acc: List<Char>, maskRemaining: String, groupsRemaining: List<Int>): Long {

            val maybeSolved = solved[Pair(maskRemaining, groupsRemaining)]
            if(maybeSolved != null) {
                return maybeSolved
            }

            if(groupsRemaining.isEmpty()) {
                return if(maskRemaining.contains('#')) 0L else 1L
            }

            val remaniningSizeNecessary = (groupsRemaining.sum() + groupsRemaining.size - 1).coerceAtLeast(0)
            if(remaniningSizeNecessary > maskRemaining.length) {
                return 0
            }

            val nextChar = maskRemaining.first()
            if(nextChar == '.') {
                return solveAcc(acc + '.', maskRemaining.drop(1), groupsRemaining)
            }

            val combinationsWhenNotBroken = if(nextChar == '?') { solveAcc(acc + '.', maskRemaining.drop(1), groupsRemaining) } else 0

            val canPlaceBrokenGroup = maskRemaining.substring(0, groupsRemaining.first()).none{it == '.'} &&
                    (groupsRemaining.size == 1 || maskRemaining[groupsRemaining.first()] != '#')

            val combinationsPlacingNextGroup = if(canPlaceBrokenGroup) {
                val additionalAcc = List(groupsRemaining.first()){'#'} + if(groupsRemaining.size > 1) listOf('.') else listOf()
                solveAcc(acc + additionalAcc, maskRemaining.drop(additionalAcc.size), groupsRemaining.drop(1))
            } else {
                0
            }

            val combinations = combinationsWhenNotBroken + combinationsPlacingNextGroup
            solved[Pair(maskRemaining, groupsRemaining)] = combinations
            return combinations
        }

        return solveAcc(emptyList(), mask, groups)
    }

}