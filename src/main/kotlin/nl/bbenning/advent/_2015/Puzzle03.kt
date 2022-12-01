package nl.bbenning.advent._2015

import nl.bbenning.advent.utils.PathWalker
import java.io.File

object Puzzle03 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("./src/main/resources/inputs/2015/input03.txt").readLines()[0]
        val pathWalker = PathWalker("><^v")

        val pointsOnPath = pathWalker.getPointsOnPath(input)

        println("Result of 3A: ${pointsOnPath.toSet().size}")

        val santaPath = input.filterIndexed{index, c -> index % 2 == 0 }
        val roboSantaPath = input.filterIndexed{index, c -> index % 2 == 1 }

        val santaPoints = pathWalker.getPointsOnPath(santaPath)
        val roboSantaPoints = pathWalker.getPointsOnPath(roboSantaPath)

        println("Result of 3B: ${(santaPoints + roboSantaPoints).toSet().size}")
    }
}
