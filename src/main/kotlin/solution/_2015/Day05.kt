package solution._2015

import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day05(val input: List<String>) {
    fun solve1(): Int {
        return input.count { isNicePart1(it) }
    }

    fun solve2():Int {
        return input.count { isNicePart2(it) }
    }

    private fun isNicePart1(str: String): Boolean {
        val atLeast3Vowels = str.matches(".*[aeiou].*[aeiou].*[aeiou].*".toRegex())
        val atLeastALetterTwiceInARow = str.matches(".*([a-z])\\1.*".toRegex())
        val doesNotContainCertainStrings = !listOf("ab", "cd", "pq", "xy").any { it in str }

        return atLeast3Vowels && atLeastALetterTwiceInARow && doesNotContainCertainStrings
    }

    private fun isNicePart2(str: String): Boolean {
        val atLeastPairOfTwoLettersNonOverlapping = str.matches(".*([a-z][a-z]).*\\1.*".toRegex())
        val oneLetterRepeatedWithLetterInBetween = str.matches(".*([a-z])[a-z]\\1.*".toRegex())

        return atLeastPairOfTwoLettersNonOverlapping && oneLetterRepeatedWithLetterInBetween
    }
}