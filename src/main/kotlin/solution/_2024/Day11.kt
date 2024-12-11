package solution._2024

import util.*

class Day11(val input: String) {

    fun solve(blinksTodo: Int): Long {
        val stones = input.longs()

        // from pair of stone + blinksTodo to resulting number of stones
        val processedStones = mutableMapOf<Pair<Long, Int>, Long>()

        fun blinking(stone: Long, blinksTodo: Int): Long {
            if(blinksTodo == 0) {
                return 1
            }

            val cached = processedStones[Pair(stone, blinksTodo)]
            if(cached != null) {
                return cached
            }

            val result = if(stone == 0L) {
                blinking(1, blinksTodo - 1)
            } else if (stone.toString().length % 2 == 0) {
                val str = stone.toString()
                blinking(str.substring(0, str.length / 2).toLong(), blinksTodo - 1) +
                blinking(str.substring(str.length / 2).toLong(), blinksTodo - 1)
            } else {
                blinking(stone * 2024, blinksTodo - 1)
            }

            processedStones[Pair(stone, blinksTodo)] = result

            return result
        }

        val result = stones.sumOf { stone ->
            blinking(stone, blinksTodo)
        }

        return result
    }
}