package solution._2015

import util.*
import util.mapping.Coord
import util.mapping.Direction
import java.util.ArrayDeque

class Day07(val input: List<String>) {
    fun solve(): UShort {
        return runCircuit()["a"]!!
    }

    fun runCircuit(): Map<String, UShort> {
        val knownResults = mutableMapOf<String, UShort>()
        val unknowns = ArrayDeque(input)

        while(unknowns.isNotEmpty()) {
            val command = unknowns.removeFirst()

            if(listOf("AND", "OR", "SHIFT").any{it in command}) {
                val (in1, op, in2, out1) = "(\\w+) (\\w+) (\\w+) -> (\\w+)".toRegex().find(command)!!.destructured.toList()

                val kin1 = in1.toUShortOrNull() ?: knownResults[in1]
                val kin2 = in2.toUShortOrNull() ?: knownResults[in2]

                if(kin1 != null && kin2 != null) {
                    when (op) {
                        "AND" -> knownResults[out1] = kin1 and kin2
                        "OR" -> knownResults[out1] = kin1 or kin2
                        "LSHIFT" -> knownResults[out1] = (kin1.toInt() shl kin2.toInt()).toUShort()
                        "RSHIFT" -> knownResults[out1] = (kin1.toInt() shr kin2.toInt()).toUShort()
                    }
                    continue
                }
            } else if (command.contains("NOT")) {
                val (in1, out1) = "NOT (\\w+) -> (\\w+)".toRegex().find(command)!!.destructured.toList()

                val kin1 = in1.toUShortOrNull() ?: knownResults[in1]
                if(kin1 != null) {
                    knownResults[out1] = kin1.inv()
                    continue
                }
            } else {
                val (in1, out1) = "(\\w+) -> (\\w+)".toRegex().find(command)!!.destructured.toList()
                val kin1 = in1.toUShortOrNull() ?: knownResults[in1]

                if(kin1 != null) {
                    knownResults[out1] = kin1
                    continue
                }
            }

            unknowns.add(command)
        }

        return knownResults
    }
}