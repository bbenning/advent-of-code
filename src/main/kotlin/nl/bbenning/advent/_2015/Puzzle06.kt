package nl.bbenning.advent._2015

import java.io.File
import java.lang.IllegalArgumentException

object Puzzle06 {
    enum class Operation {
        TOGGLE, OFF, ON
    }
    data class Instruction(val op: Operation, val point1: Pair<Int, Int>, val point2: Pair<Int, Int>)

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2015/input06.txt").readLines()
//        val inputStrings = listOf("turn on 0,0 through 999,999", "toggle 0,0 through 999,0")
        val instructions = inputStrings.map {
            val pieces = it.split(" ")
            when(pieces[0]) {
                "toggle" -> {
                    val points1 = pieces[1].split(",").map { it.toInt() }
                    val point1 = Pair(points1[0], points1[1])
                    val points2 = pieces[3].split(",").map { it.toInt() }
                    val point2 = Pair(points2[0], points2[1])
                    Instruction(Operation.TOGGLE, point1, point2)
                }
                "turn" -> {
                    val op = if(pieces[1] == "on") Operation.ON else Operation.OFF
                    val points1 = pieces[2].split(",").map { it.toInt() }
                    val point1 = Pair(points1[0], points1[1])
                    val points2 = pieces[4].split(",").map { it.toInt() }
                    val point2 = Pair(points2[0], points2[1])
                    Instruction(op, point1, point2)
                }
                else -> {
                    throw IllegalArgumentException("Cannot parse file")
                }
            }
        }

        val result = instructions.fold(Array(999){Array(999){0} }){ acc, instruction ->
            acc.mapIndexed{x, row ->
                row.mapIndexed { y, light ->
                    // Looks like there is an assumption that the point with lower points is the first one, so that makes is a bit easier
                    if(
                        instruction.point1.first <= x && instruction.point1.second <= y &&
                        instruction.point2.first >= x && instruction.point2.second >= y
                    ) {
                        when(instruction.op) {
                            Operation.TOGGLE -> {
                                if(light == 0) 1 else 0
                            }
                            Operation.OFF -> {
                                0
                            }
                            Operation.ON -> {
                                1
                            }
                        }
                    } else {
                        light
                    }
                }.toTypedArray()
            }.toTypedArray()
        }

        val lightsOn = result.map { it.sum() }.sum()
        println("Answer 6a: $lightsOn")

        val result2 = instructions.fold(Array(999){Array(999){0} }){ acc, instruction ->
            acc.mapIndexed{x, row ->
                row.mapIndexed { y, light ->
                    // Looks like there is an assumption that the point with lower points is the first one, so that makes is a bit easier
                    if(
                        instruction.point1.first <= x && instruction.point1.second <= y &&
                        instruction.point2.first >= x && instruction.point2.second >= y
                    ) {
                        when(instruction.op) {
                            Operation.TOGGLE -> {
                                light + 2
                            }
                            Operation.OFF -> {
                                if(light > 0) light - 1 else 0
                            }
                            Operation.ON -> {
                                light + 1
                            }
                        }
                    } else {
                        light
                    }
                }.toTypedArray()
            }.toTypedArray()
        }

        val lightsOn2 = result2.map { it.sum() }.sum()
        println("Answer 6b: $lightsOn2")
    }
}