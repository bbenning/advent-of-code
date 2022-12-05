package nl.bbenning.advent._2022._05

import nl.bbenning.advent.utils.updated
import java.io.File

object Main {
    data class Movement(val from: Int, val to: Int, val amount: Int)

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input05.txt").readLines()

        val numberOfCrates =
            inputStrings.filter { !it.contains("[") && it.isNotEmpty() && !it.contains("move") }[0].split(
                " "
            ).last { it.isNotEmpty() }.toInt()

        val crateInputs = inputStrings.filter { it.contains("[") }

        val crateStacks = (0 until numberOfCrates).map { crateNumber ->
            crateInputs.map { s ->
                val crateStrPos = 4*crateNumber + 1
                if(s.length > crateStrPos && s[crateStrPos] != ' ') {
                    "" + s[crateStrPos]
                } else {
                    ""
                }
            }.joinToString("")
        }

        val movementPattern = "move (\\d+) from (\\d+) to (\\d+)".toPattern()
        val movements = inputStrings.filter { it.contains("move")}.map {
            val matcher = movementPattern.matcher(it)
            matcher.find()
            val amount = matcher.group(1).toInt()
            val from = matcher.group(2).toInt() - 1 // offset by 1 because i want it to be 0 based.
            val to = matcher.group(3).toInt() - 1

            Movement(from, to, amount)
        }

        val result1 = movements.fold(crateStacks) { acc, movement ->
            val updatedNewStack = acc[movement.from].substring(0, movement.amount).reversed() + acc[movement.to]
            val updatedOldStack = acc[movement.from].substring(movement.amount)
            acc.updated(movement.to, updatedNewStack).updated(movement.from, updatedOldStack)
        }

        val result2 = movements.fold(crateStacks) { acc, movement ->
            val updatedNewStack = acc[movement.from].substring(0, movement.amount) + acc[movement.to]
            val updatedOldStack = acc[movement.from].substring(movement.amount)
            acc.updated(movement.to, updatedNewStack).updated(movement.from, updatedOldStack)
        }

        println("Answer for question 05a: ${result1.map { it.firstOrNull() }.filter { it != null }.joinToString("")}")
        println("Answer for question 05b: ${result2.map { it.firstOrNull() }.filter { it != null }.joinToString("")}")
    }
}