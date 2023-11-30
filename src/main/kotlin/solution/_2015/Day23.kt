package solution._2015

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.emulator.Emulator
import util.mapping.Coord
import util.mapping.Direction

class Day23(val input: List<String>) {

    private val instructions = input.map { Emulator.Instruction.strToInstruction(it) }

    fun solve1(): Long {
        return Emulator.run(instructions).registers["b"]!!
    }

    fun solve2():Long {
        return Emulator.run(instructions, registers = mapOf(Pair("a", 1L))).registers["b"]!!
    }

    private fun solve():Int {
        return 0
    }
}