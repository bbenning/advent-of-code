package nl.bbenning.advent._2015

import java.io.File

object Puzzle07 {

    enum class Operation {
        NOP, AND, OR, NOT, LSHIFT, RSHIFT
    }

    data class BuildingBlock(val op: Operation, val in1: String?, val in2: String?, val outName: String)

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2015/input07.txt").readLines()

        val buildingBlocks: Map<String, BuildingBlock> = inputStrings.map { inputStr ->
            val strSplit = inputStr.split("->").map { it.trim() }
            val outName = strSplit[1]

            if(strSplit[0].contains("AND")) {
                val split = strSplit[0].split(" AND ")
                BuildingBlock(Operation.AND, split[0], split[1], outName)
            } else if (strSplit[0].contains("OR")) {
                val split = strSplit[0].split(" OR ")
                BuildingBlock(Operation.OR, split[0], split[1], outName)
            } else if (strSplit[0].contains("NOT")) {
                val split = strSplit[0].split("NOT ")
                BuildingBlock(Operation.NOT, split[1],null, outName)
            } else if (strSplit[0].contains("LSHIFT")) {
                val split = strSplit[0].split(" LSHIFT ")
                BuildingBlock(Operation.LSHIFT, split[0], split[1], outName)
            } else if (strSplit[0].contains("RSHIFT")) {
                val split = strSplit[0].split(" RSHIFT ")
                BuildingBlock(Operation.RSHIFT, split[0], split[1], outName)
            } else {
                BuildingBlock(Operation.NOP, strSplit[0], null, outName)
            }
        }.groupBy { it.outName }.mapValues { it.value[0] }

        val cachedValues = mutableMapOf<String, UShort>()

        fun findResultFor(nodeName: String): UShort {
            if(cachedValues.contains(nodeName)) {
                return cachedValues[nodeName]!!
            }

            if(nodeName.toUShortOrNull() != null) {
                return nodeName.toUShort()
            }

            val buildingBlock = buildingBlocks[nodeName]!!

            val value = when(buildingBlock.op) {
                Operation.NOP -> findResultFor(buildingBlock.in1!!)
                Operation.AND -> findResultFor(buildingBlock.in1!!) and findResultFor(buildingBlock.in2!!)
                Operation.OR -> findResultFor(buildingBlock.in1!!) or findResultFor(buildingBlock.in2!!)
                Operation.NOT -> findResultFor(buildingBlock.in1!!).inv()
                Operation.LSHIFT -> (findResultFor(buildingBlock.in1!!).toUInt() shl findResultFor(buildingBlock.in2!!).toInt()).toUShort()
                Operation.RSHIFT -> (findResultFor(buildingBlock.in1!!).toUInt() shr findResultFor(buildingBlock.in2!!).toInt()).toUShort()
            }

            cachedValues[nodeName] = value

            return value
        }

        println("Answer for 7a: ${findResultFor("a")}")

        // did 7b manually, by changing the input from:
        // 1674 -> b
        // to:
        // 46065 -> b
    }
}