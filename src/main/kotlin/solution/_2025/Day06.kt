package solution._2025

import util.*

class Day06(val input: List<String>) {
    fun solve1(): Long {
        val nums = input.dropLast(1).map { it.longs() }
        val ops = input.last().split(" +".toRegex())

        val results:List<Long> = (ops.indices).map { idx ->
            val op = ops[idx]

            when (op) {
                "*" -> nums.fold(1L) { acc, longs -> acc * longs[idx] }
                "+" -> nums.fold(0L) { acc, longs -> acc + longs[idx] }
                else -> 0L
            }
        }

        return results.sum()
    }

    fun solve2(): Long {
        val preparedInput = input.map { "$it " }

        var operation:Char? = null
        var interimResult = 0L
        val results = mutableListOf<Long>()
        (preparedInput.first().indices).forEach { x ->
            if(operation == null) {
                operation = preparedInput.last()[x]
                interimResult = if(operation == '*') {
                    1L
                } else {
                    0L
                }
            }

            val numStr = preparedInput.dropLast(1).fold("") { acc, nums ->
                val c = nums[x]
                if(c == ' ') {
                    acc
                } else {
                    acc + c
                }
            }

            if(numStr.isNotEmpty()) {
                if(operation == '*') {
                    interimResult *= numStr.toLong()
                } else {
                    interimResult += numStr.toLong()
                }
            } else {
                results.add(interimResult)
                operation = null
            }
        }

        return results.sum()
    }
}