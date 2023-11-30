package solution._2015

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day17(val input: List<String>) {
    fun solve1(liters: Int): Int {
        return solve(liters).size

    }

    fun solve2(liters: Int):Int {
        val totalSolutions = solve(liters)
        val minimumStackSize = totalSolutions.minOf { it.size }
        return totalSolutions.filter { it.size == minimumStackSize }.size
    }

    private fun solve(liters: Int):List<List<Int>> {
        val containers = input.map { it.toInt() }

        val stores = mutableListOf<List<Int>>()

        fun store(stack: List<Int>, containers: List<Int>, liters: Int) {
            if(liters == 0) {
                stores.add(stack)
            } else if (liters > 0) {
                containers.forEachIndexed { index, container ->
                    store(stack.plusElement(container), containers.drop(index + 1), liters - container)
                }
            }
        }

        store(emptyList(), containers, liters)

        return stores
    }
}