package nl.bbenning.advent._2022._10

import java.io.File
import java.math.BigInteger

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input10.txt").readLines()


        val output = inputStrings.fold(listOf(1)) { acc, s ->
            val curVal = acc.last()
            if (s == "noop") {
                acc + curVal
            } else {
                acc + curVal + (curVal + s.split(" ")[1].toInt())
            }
        }

        val solution10a = output.mapIndexed { index, i ->
            val cycle = index + 1
            if (cycle == 20 || (cycle - 20) % 40 == 0) i * cycle else 0
        }.fold(0) { acc, i -> acc + i }

        println("Answer to 10a: $solution10a")

        val printedLines = output.chunked(40).map { shiftValues ->
            shiftValues.foldIndexed("") { index, acc, i ->
                val takePixelAt = index - i + 1
                acc + if (takePixelAt in 0 .. 2) "#" else "."
            }
        }

        printedLines.forEach { println(it) }

        // 13480
        // EGJBGCFK
    }
}