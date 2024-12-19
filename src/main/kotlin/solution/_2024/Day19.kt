package solution._2024

import util.*

class Day19(val input: List<String>) {
    private val availableTowels = input.first().words()
    private val wantedPatterns = input.drop(2)

    fun solve1(): Int {
        return solve().filter { it > 0 }.size
    }

    fun solve2(): Long {
        return solve().sum()
    }

    private fun solve(): List<Long> {
        val foundPatterns = mutableMapOf<String, Long>()

        fun matchPattern(pattern: String): Long {
            if(pattern.isEmpty()) {
                return 1
            }

            val foundPatternCount = foundPatterns[pattern]
            if(foundPatternCount != null) {
                return foundPatternCount
            }

            val count = availableTowels.filter { pattern.startsWith(it) }.sumOf { towel ->
                matchPattern(pattern.drop(towel.length))
            }
            foundPatterns[pattern] = count

            return count
        }

        return wantedPatterns.map { matchPattern(it) }
    }
}