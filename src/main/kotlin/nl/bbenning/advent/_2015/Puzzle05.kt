package nl.bbenning.advent._2015

import nl.bbenning.advent.utils.countTotalSubstrings
import nl.bbenning.advent.utils.doesNotContainSubstrings
import nl.bbenning.advent.utils.hasNLettersInARow
import java.io.File

object Puzzle05 {
    @JvmStatic
    fun main(args: Array<String>) {
        val stringList = File("./src/main/resources/inputs/2015/input05.txt").readLines()

        val niceStringCount = stringList.filter {
            it.countTotalSubstrings(listOf("a", "e", "i", "o", "u")) >= 3 &&
                    it.hasNLettersInARow(2) &&
                    it.doesNotContainSubstrings(listOf("ab", "cd", "pq", "xy"))
        }

        println("Answer to 5a: ${niceStringCount.size}")

        val pattern1 = ".*(..).*\\1.*".toPattern().toRegex()
        val pattern2 = ".*(.).\\1.*".toPattern().toRegex()
        val niceStringCount2 = stringList.filter {
            it.matches(pattern1) && it.matches(pattern2)
        }

        println("Answer to 5b: ${niceStringCount2.size}")

    }
}