package solution._2024

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.ints.product
import util.longs.product
import util.mapping.Coord
import util.mapping.Direction

class Day03(val input: List<String>) {
    fun solve1(): Long {
        val mulRegex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
        val muls = input.flatMap { line -> mulRegex.findAll(line).map{ v -> v.value}.toList() }
        val sumOfProduct = muls.sumOf { it.longs().product() }

        return sumOfProduct
    }

    fun solve2(): Long {
        val instructionRegex = "mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)".toRegex()
        val instructions = input.flatMap { line -> instructionRegex.findAll(line).map{ v -> v.value}.toList() }

        val result = instructions.fold(Pair(true, 0L)){ acc, s ->
            when(s) {
                "do()" -> Pair(true, acc.second)
                "don't()" -> Pair(false, acc.second)
                else -> if(acc.first) Pair(true, acc.second + s.longs().product()) else acc
            }
        }

        return result.second
    }
}