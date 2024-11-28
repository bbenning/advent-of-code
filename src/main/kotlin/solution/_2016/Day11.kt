package solution._2016

import com.github.shiguruikai.combinatoricskt.permutations
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*

// Very slow and rather ugly implementation. Also needs lots of memory.
class Day11(val input: List<String>) {
    fun solve1(): Long {
        return solve()
    }

    fun solve2(): Long {
        return solve()
    }

    private data class State(val floors: List<Floor>, val elevator: Int)

    private data class Floor(val items: Set<Item>)

    private enum class ItemType {MICROCHIP, GENERATOR}
    private data class Item(val name: String, val type: ItemType)

    // The first floor contains a hydrogen-compatible microchip and a lithium-compatible microchip.
    // The second floor contains a hydrogen generator.

    private fun isValidCombination(items: Collection<Item>): Boolean {
        val generators = items.filter { it.type == ItemType.GENERATOR }
        val microchips = items.filter { it.type == ItemType.MICROCHIP }

        return microchips.isEmpty() || generators.isEmpty() || microchips.all { chip -> generators.firstOrNull { chip.name == it.name } != null }
    }

    private fun solve(): Long {
        val startFloors = input.map { s ->
            val microchipsRegex = "([a-z]*)-compatible microchip".toRegex()
            val generatorsRegex = "([a-z]*) generator".toRegex()

            val microchips = microchipsRegex.findAll(s).toList().map { Item(it.groupValues[1], ItemType.MICROCHIP) }
            val generators = generatorsRegex.findAll(s).toList().map { Item(it.groupValues[1], ItemType.GENERATOR) }
            Floor(microchips.toSet() + generators)
        }

        val allItems = startFloors.flatMap { it.items }
        val beginState = State(startFloors, 0)

        val alreadySeen = mutableSetOf<State>()
        val nextMoves = mutableListOf(Pair(beginState, 0))
        while(nextMoves.isNotEmpty()) {
            val (curState, curMoveCount) = nextMoves.removeFirst()

            if(curState.floors.last().items.size == allItems.size) {
                return curMoveCount.toLong()
            }

            val curFloor = curState.floors[curState.elevator]
            val itemPermutations: List<List<Item>> = curFloor.items.permutations(2).toList() + curFloor.items.permutations(1)

            itemPermutations.forEach { permutation ->
                if(isValidCombination(permutation)) {
                    val updatedCurFloor = curFloor.copy(items = curFloor.items - permutation.toSet())
                    if(curState.elevator > 0) {
                        val newFloors = curState.floors.mapIndexed { index, floor ->
                            when (index) {
                                curState.elevator -> updatedCurFloor
                                curState.elevator - 1 -> {
                                    val nextFloor = curState.floors[curState.elevator - 1]
                                    nextFloor.copy(items = nextFloor.items + permutation)
                                }
                                else -> floor
                            }
                        }.dropWhile { it.items.isEmpty() }
                        val nextState = State(newFloors, curState.elevator - 1 - (curState.floors.size - newFloors.size))

                        if(nextState.floors.all { isValidCombination(it.items) } && nextState !in alreadySeen) {
                            alreadySeen.add(nextState)
                            nextMoves.add(Pair(nextState, curMoveCount + 1))
                        }
                    }
                    if(curState.elevator < curState.floors.size - 1) {
                        val newFloors = curState.floors.mapIndexed { index, floor ->
                            when (index) {
                                curState.elevator -> updatedCurFloor
                                curState.elevator + 1 -> {
                                    val nextFloor = curState.floors[curState.elevator + 1]
                                    nextFloor.copy(items = nextFloor.items + permutation)
                                }
                                else -> floor
                            }
                        }.dropWhile { it.items.isEmpty() }
                        val nextState = State(newFloors, curState.elevator + 1 - (curState.floors.size - newFloors.size))
                        if(nextState.floors.all { isValidCombination(it.items) } && nextState !in alreadySeen) {
                            alreadySeen.add(nextState)
                            nextMoves.add(Pair(nextState, curMoveCount + 1))
                        }
                    }
                }
            }
        }

        return -1L
    }
}