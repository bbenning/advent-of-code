package nl.bbenning.advent._2015

import nl.bbenning.advent.utils.PathWalker
import java.io.File

object Puzzle01 {
    @JvmStatic
    fun main(args: Array<String>) {

        val input = File("./src/main/resources/inputs/2015/input01.txt").readLines()[0]
        val pathExplorer = PathWalker("()")

        println("Answer for A: ${pathExplorer.getResultAfterWalking(input)}")
        println("Answer for B: ${pathExplorer.getStepsUntilPointDiscovered(input, listOf(-1))}")
    }
}