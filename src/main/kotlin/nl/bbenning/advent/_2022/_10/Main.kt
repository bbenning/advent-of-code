package nl.bbenning.advent._2022._10

import nl.bbenning.advent.utils.words
import java.io.File

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input10.txt").readLines()

        val output = inputStrings.fold(listOf(1)) { acc, s ->
            val curVal = acc.last()
            if (s == "noop") {
                acc + curVal
            } else {
                acc + curVal + (curVal + s.words()[1].toInt())
            }
        }

        val solution10a = output.mapIndexed { index, i ->
            val cycle = index + 1
            if (cycle == 20 || (cycle - 20) % 40 == 0) i * cycle else 0
        }.fold(0) { acc, i -> acc + i }

        val solution10b = output.chunked(40).map { shiftValues ->
            shiftValues.foldIndexed("") { index, acc, shiftVal ->
                acc + if (index + 1 in shiftVal .. shiftVal + 2) "█" else " "
            }
        }

        println("Answer to 10a: $solution10a")
        solution10b.forEach { println(it) }
    }
}