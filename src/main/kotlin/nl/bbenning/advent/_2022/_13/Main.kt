package nl.bbenning.advent._2022._13

import java.io.File
import com.fasterxml.jackson.module.kotlin.*

object Main {

    fun compare(left: Any, right: Any): Int {
        return when {
            left is Int && right is Int         -> left - right
            left is Int                         -> compare(listOf(left), right)
            right is Int                        -> compare(left, listOf(right))
            left is List<*> && right is List<*> -> {
                when {
                    left.isEmpty() && right.isEmpty() -> 0
                    left.isEmpty()                    -> -1
                    right.isEmpty()                   -> 1
                    else                              -> {
                        val result = compare(left[0]!!, right[0]!!)
                        if (result == 0) {
                            compare(left.drop(1), right.drop(1))
                        } else {
                            result
                        }
                    }
                }
            }
            else -> throw IllegalStateException("Can't happen")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input13.txt").readLines()

        val mapper = jacksonObjectMapper()
        val structuredInputs = inputStrings.windowed(2, 3).map { listOf(mapper.readValue(it[0]), mapper.readValue<List<Any>>(it[1])) }

        val resultA = structuredInputs.mapIndexed{ idx, i ->
            if(compare(i[0], i[1]) < 0) idx + 1 else 0
        }

        val two = mapper.readValue<List<Any>>("[[2]]")
        val six = mapper.readValue<List<Any>>("[[6]]")
        val sortedInputs = structuredInputs.flatten().plusElement(two).plusElement(six).sortedWith{a, b -> compare(a, b)}

        println("Answer 13a: ${resultA.sum()}")

        println("Answer 13b: ${(sortedInputs.indexOf(two) + 1) * (sortedInputs.indexOf(six) + 1)}")
    }
}