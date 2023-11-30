package solution._2015

import util.*

class Day14(val input: List<String>) {
    fun solve1(seconds: Int): Int {
        return solve(seconds).maxOf{ it.distance }
    }

    fun solve2(seconds: Int):Int {
        return solve(seconds).maxOf {it.points }
    }

    private data class Reindeer(val speed: Int, val longevity: Int, val rest: Int)
    private data class State (val reindeer: Reindeer, val seconds: Int, val distance: Int, val points: Int)

    private fun solve(seconds: Int): List<State> {
        val reindeers = input.map {
            val (speed, longevity, rest) = it.ints()
            Reindeer(speed, longevity, rest)
        }

        fun calculateNewPosition(state: State): State {
            val newDistance = state.distance + if(state.seconds < state.reindeer.longevity) state.reindeer.speed else 0
            val newSeconds = if(state.seconds + 1 == state.reindeer.longevity + state.reindeer.rest) 0 else state.seconds + 1

            return state.copy(distance = newDistance, seconds = newSeconds)
        }

        val initialStates = reindeers.map { State(it, 0, 0, 0) }

        val allTransitions = (0 until seconds).fold(initialStates){ acc, _ ->
            val newStatePositions = acc.map { calculateNewPosition(it) }
            val farthest = newStatePositions.maxOf { it.distance }

            val newStates = newStatePositions.map {
                if(it.distance == farthest) {
                    it.copy(points = it.points + 1)
                } else {
                    it
                }
            }

            newStates
        }

        return allTransitions
    }
}