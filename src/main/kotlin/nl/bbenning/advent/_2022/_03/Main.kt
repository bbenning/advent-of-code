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

            aIntersectB.sumOf { letter ->
                letterValue(letter)
            }
        }

        println("Solution to 03a: ${intersectingValues.sum()}")

        val badges = inputStrings.chunked(3).flatMap { groupOf3 ->
            groupOf3[0].toSet().intersect(groupOf3[1].toSet()).intersect(groupOf3[2].toSet()).map { letterValue(it) }
        }

        println("Solution to 03b: ${badges.sum()}")
    }

    private fun letterValue(letter: Char) = (if (letter.isLowerCase()) {
        letter - 96
    } else {
        letter - 38
    }).code
}