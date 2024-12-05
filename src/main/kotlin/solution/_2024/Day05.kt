package solution._2024

import util.*

class Day05(val input: List<String>) {
    fun solve1(): Int {
        val middleNumbers = validUpdates.map { it[it.size / 2] }

        return middleNumbers.sum()
    }

    fun solve2(): Int {

        fun correctInvalidUpdates(todo: List<Int>, done: List<Int>): List<Int> {
            if(todo.isEmpty()) {
                return done
            }

            val head = todo.first()
            val doneTry = done.toMutableList()
            var idx = -1
            do {
                if(idx >= 0) {
                    doneTry.removeAt(idx)
                }
                doneTry.add(++idx, head)
            } while(!isValid(doneTry))

            return correctInvalidUpdates(todo.drop(1), doneTry)
        }

        val correctedInvalidUpdates = invalidUpdates.map{correctInvalidUpdates(it, emptyList())}
        val middleNumbers = correctedInvalidUpdates.map { it[it.size / 2] }

        return middleNumbers.sum()
    }

    private val rules: Map<Int, Set<Int>> = input.filter { it.contains("|") }.map { it.ints() }.groupBy { it[0] }.mapValues { it.value.map { it[1] }.toSet() }
    private val updatesList = input.filter {it.contains(",")}.map { it.ints() }

    private fun isValid(updates: List<Int>): Boolean {
        if(updates.isEmpty()) {
            return true
        }

        val head = updates.first()
        val tail = updates.drop(1)
        val headValid = tail.all { it in (rules[head]?: emptySet())}

        return headValid && isValid(tail)
    }

    private val validUpdates = updatesList.filter { isValid(it)}
    private val invalidUpdates = updatesList.filter { it !in validUpdates}

}