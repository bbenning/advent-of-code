package nl.bbenning.advent._2022._03

import java.io.File

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input03.txt").readLines()

        val intersectingValues = inputStrings.map { str ->
            val a = str.substring(0, str.length / 2)
            val b = str.substring(str.length / 2)
            val aIntersectB = a.toSet().intersect(b.toSet())

            aIntersectB.sumOf { letterValue(it) }
        }

        val badges = inputStrings.chunked(3).flatMap { (a, b, c) ->
            a.toSet().intersect(b.toSet()).intersect(c.toSet()).map { letterValue(it) }.toList()
        }

        println("Solution to 03a: ${intersectingValues.sum()}")
        println("Solution to 03b: ${badges.sum()}")
    }

    private fun letterValue(letter: Char) = if (letter.isLowerCase()) letter - 'a' + 1 else letter - 'A' + 27
}