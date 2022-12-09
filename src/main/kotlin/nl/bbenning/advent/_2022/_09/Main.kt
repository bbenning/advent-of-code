package nl.bbenning.advent._2022._09

import java.io.File
import java.lang.IllegalArgumentException
import kotlin.math.abs

object Main {

    data class Location(val x: Int, val y: Int)

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input09.txt").readLines()

        val commands = inputStrings.flatMap { s ->
            val split = s.split(" ")
            MutableList(split[1].toInt()){split[0]}
        }

        val listOfLocations = solve(commands, 2)
        println("Answer to 09a: ${listOfLocations.map { it[1] }.toSet().size}")


        val listOfLocations2 = solve(commands, 10)
        println("Answer to 09b: ${listOfLocations2.map { it[9] }.toSet().size}")
    }

    private fun solve(commands: List<String>, sizeOfSnake: Int) =
        commands.fold(listOf(MutableList(sizeOfSnake) { Location(0, 0) }.toList())) { acc, movement ->

            val headLocation = acc.last().first()

            val calculatedHead = when (movement) {
                "U" -> headLocation.copy(y = headLocation.y + 1)
                "D" -> headLocation.copy(y = headLocation.y - 1)
                "R" -> headLocation.copy(x = headLocation.x + 1)
                "L" -> headLocation.copy(x = headLocation.x - 1)
                else -> throw IllegalArgumentException("Unmapped input.")
            }

            val updatedList =
                acc.last().drop(1)// dropping head as we needn't update that one because it's already calculated
                    .fold(listOf(calculatedHead)) { accumulatedSnake, tail ->
                        val head = accumulatedSnake.last()

                        val isDiagonallyDistant = tail.x != head.x && tail.y != head.y && abs(tail.x - head.x) + abs(tail.y - head.y) > 2
                        val newX = tail.x + if(tail.x - head.x == 2) -1 else if (tail.x - head.x == -2) 1 else if (isDiagonallyDistant) head.x - tail.x else 0
                        val newY = tail.y + if(tail.y - head.y == 2) -1 else if (tail.y - head.y == -2) 1 else if (isDiagonallyDistant) head.y - tail.y else 0

                        accumulatedSnake + Location(newX, newY)
                    }

            acc.plusElement(updatedList)
        }

}