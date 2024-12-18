package solution._2024

import util.*

class Day17(val input: List<String>) {
    fun solve1(): String {
        val a = input.first { it.contains("Register A") }.longs().first()
        return solve(a)
    }

    fun solve2(): Long {
        // one "instruction" is gained in this program for every multiple of 8 instructions
        // given the length of the program, needed to switch to longs.
        // further, it seems to be stable for the first n found numbers
        fun search(a: Long, depth: Int): Long {
            if(depth == 0) {
                return a
            }

            for (i in 0 .. 7) {
                val result = solve(a * 8 + i)
                val resultProgram = result.ints()
                if(resultProgram == instructions.takeLast(resultProgram.size)) {
                    val solution = search(a * 8 + i, depth - 1)
                    if(solution >= 0) return solution
                }
            }

            return -1
        }

        return search(0L, instructions.size)
    }

    val initialB = input.first { it.contains("Register B") }.longs().first()
    val initialC = input.first { it.contains("Register C") }.longs().first()
    val instructions = input.first{ it.contains("Program") }.ints()
    val instructionsString = instructions.joinToString(",")

    private fun solve(_a: Long): String {
        var a = _a
        var b = initialB
        var c = initialC

        var instrPtr = 0

        val out = mutableListOf<Int>()

        fun getComboOperand(operand: Int): Long {
            return when(operand) {
                0, 1, 2, 3 -> operand.toLong()
                4 -> a
                5 -> b
                6 -> c
                else -> throw IllegalArgumentException("Should never happen")
            }
        }

        while(instrPtr in 0 until instructions.size) {
            val opcode = instructions[instrPtr++]
            val operand = instructions[instrPtr++]
            when(opcode) {
                0 -> { // adv
                    val numerator = a
                    val comboOperand = getComboOperand(operand)
                    val divisor = 1L.shl(comboOperand.toInt())
                    a = numerator / divisor
                }
                1 -> { // bxl
                    b = b.xor(operand.toLong())
                }
                2 -> { // bst
                    b = getComboOperand(operand).mod(8L)
                }
                3 -> { // jnz
                    if(a != 0L) {
                        instrPtr = operand.toInt()
                    }
                }
                4 -> { // bxc
                    b = b.xor(c)
                }
                5 -> { // out
                    out.add(getComboOperand(operand).mod(8))
                }
                6 -> { // bdv
                    val numerator = a
                    val comboOperand = getComboOperand(operand)
                    val divisor = 1L.shl(comboOperand.toInt())
                    b = numerator / divisor
                }
                7 -> { // cdv
                    val numerator = a
                    val comboOperand = getComboOperand(operand)
                    val divisor = 1L.shl(comboOperand.toInt())
                    c = numerator / divisor
                }
            }
        }

        return out.joinToString(",")
    }
}