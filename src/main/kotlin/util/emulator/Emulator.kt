package util.emulator

import util.isNumeric

data class Emulator(val instructions: List<Instruction>, val registers: Map<String, Long> = emptyMap(), val instr: Int = 0) {

    private fun getNumberOrRegister(str: String): Long? {
        return if(str.isNumeric()) {
            str.toLong()
        } else {
            registers[str]
        }
    }

    private fun getNumberOrRegister(str: String, default: Long): Long {
        return if(str.isNumeric()) {
            str.toLong()
        } else {
            registers[str] ?: default
        }
    }

    companion object {
        fun run(instructions: List<Instruction>, registers: Map<String, Long> = emptyMap(), instr: Int = 0): Emulator {
            var emulator = Emulator(instructions, registers, instr)
            while(emulator.instr < emulator.instructions.size) {
                emulator = emulator.instructions[emulator.instr].apply(emulator)
            }

            return emulator
        }
    }

    sealed interface Instruction {
        companion object {
            fun strToInstruction(str: String) : Instruction {
                // matches <instr> <op> [op]
                val (instr, op1, op2) = "^(\\w+) ([a-z]+|[-+]?[0-9]+)(?:(?:, )([a-z]+|[-+]?[0-9]+))?$".toRegex().findAll(str).flatMap { it.groupValues }.toList().drop(1)

                return when(instr) {
                    "hlf" -> Hlf(op1)
                    "tpl" -> Tpl(op1)
                    "inc" -> Inc(op1)
                    "jmp" -> Jmp(op1)
                    "jie" -> Jie(op1, op2)
                    "jio" -> Jio(op1, op2)
                    else -> throw IllegalArgumentException("Should never happen: $instr")
                }
            }
        }

        fun apply(e: Emulator): Emulator

    }

    data class Hlf(val reg: String) : Instruction {
        override fun apply(e: Emulator): Emulator {
            val instr = e.instr + 1
            val newValue = e.getNumberOrRegister(reg, 0) / 2
            val newRegisters = e.registers.plus(reg to newValue)
            return e.copy(registers = newRegisters, instr = instr)
        }
    }

    data class Tpl(val reg: String) : Instruction {
        override fun apply(e: Emulator): Emulator {
            val instr = e.instr + 1
            val newValue = e.getNumberOrRegister(reg, 0) * 3
            val newRegisters = e.registers.plus(reg to newValue)
            return e.copy(registers = newRegisters, instr = instr)
        }
    }

    data class Inc(val reg: String) : Instruction {
        override fun apply(e: Emulator): Emulator {
            val instr = e.instr + 1
            val newValue = e.getNumberOrRegister(reg, 0) + 1
            val newRegisters = e.registers.plus(reg to newValue)
            return e.copy(registers = newRegisters, instr = instr)
        }
    }

    data class Jmp(val offset: String) : Instruction {
        override fun apply(e: Emulator): Emulator {
            val instr = e.instr + e.getNumberOrRegister(offset, 0).toInt()
            return e.copy(instr = instr)
        }
    }

    data class Jie(val reg: String, val offset: String) : Instruction {
        override fun apply(e: Emulator): Emulator {
            val instr = if (e.getNumberOrRegister(reg, 1) % 2 == 0L)  {
                e.instr + e.getNumberOrRegister(offset, 0).toInt()
            } else {
                e.instr + 1
            }
            return e.copy(instr = instr)
        }
    }

    data class Jio(val reg: String, val offset: String) : Instruction {
        override fun apply(e: Emulator): Emulator {
            val instr = if (e.getNumberOrRegister(reg, 0) == 1L)  {
                e.instr + e.getNumberOrRegister(offset, 0).toInt()
            } else {
                e.instr + 1
            }
            return e.copy(instr = instr)
        }
    }
}



