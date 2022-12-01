package nl.bbenning.advent._2022

import org.apache.commons.lang3.StringUtils
import java.io.File

object Puzzle01 {
    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input01.txt").readLines()

        val caloriesOfElves = inputStrings.fold(Pair(emptyList<Int>(), 0)) { acc, s ->
            if(StringUtils.isEmpty(s)) {
                Pair(acc.first.plusElement(acc.second), 0)
            } else {
                Pair(acc.first, acc.second + s.toInt())
            }
        }.first

        println("Answer to puzzle 01a: ${caloriesOfElves.max()}")

        println("Answer to puzzle 01b: ${caloriesOfElves.sorted().takeLast(3).sum()}")
    }

}