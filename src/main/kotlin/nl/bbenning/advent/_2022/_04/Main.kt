package nl.bbenning.advent._2022._04

import java.io.File

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input04.txt").readLines()

        val overlapping = inputStrings.map {
            val sections = it.split(",").flatMap { it.split("-") }.map { it.toInt() }

            ((sections[0] <= sections [2] && sections[1] >= sections [3])  ||
               (sections[2] <= sections [0] && sections[3] >= sections [1]))
        }

        println("Answer to 04a: ${overlapping.filter { it }.size}")

        val overlapping2 = inputStrings.map {
            val sections = it.split(",").flatMap { it.split("-") }.map { it.toInt() }

            ((sections[0] <= sections [2] && sections[1] >= sections [3])  ||
                    (sections[2] <= sections [0] && sections[3] >= sections [1])) ||
            ((sections[0] >= sections [2] && sections[0] <= sections [3])  ||
               (sections[1] >= sections [2] && sections[1] <= sections [3]))
        }

        println("Answer to 04b: ${overlapping2.filter { it }.size}")
    }
}