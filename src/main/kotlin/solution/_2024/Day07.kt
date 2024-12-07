package solution._2024

import util.*
import java.math.BigInteger

class Day07(val input: List<String>) {

    private val equations = input.map { it.bigInts() }

    fun solve1(): String {
        return solve(listOf({ a, b -> a * b }, { a, b -> a + b }))
    }

    fun solve2(): String {
        return solve(listOf({ a, b -> BigInteger(a.toString() + b.toString()) }, { a, b -> a * b }, { a, b -> a + b }))
    }

    private fun equationIsSolvable(outcome: BigInteger, operations: List<(BigInteger, BigInteger) -> BigInteger>, inputs: List<BigInteger>): Boolean {
        if (inputs.size == 1) {
            return inputs[0] == outcome
        }

        if (inputs[0] > outcome) {
            return false
        }

        return operations.any { operation ->
            equationIsSolvable(
                outcome, operations, listOf(operation(inputs[0], inputs[1])) + inputs.drop(2)
            )
        }
    }

    private fun solve(operations: List<(BigInteger, BigInteger) -> BigInteger>): String {
        val calibratedResult = equations.filter { equation ->
            equationIsSolvable(equation[0], operations, equation.drop(1))
        }

        return calibratedResult.sumOf { it[0] }.toString()
    }
}