package solution._2024

import util.*
import util.mapping.Coord

class Day13(val input: List<String>) {
    private data class Machine(val buttonA: Coord, val buttonB: Coord, val prize: Coord)

    private val machines = input.filter { it.isNotEmpty() }.chunked(3) {
        val nums = it.map { line ->
            val nums = line.ints()
            Coord(nums[0], nums[1])
        }
        Machine(nums[0], nums[1], nums[2])
    }

    fun solve1(): Long {
        return machines.sumOf { solveMachine(it, 0L) }
    }

    fun solve2(): Long {
        return machines.sumOf { solveMachine(it, 10000000000000L) }
    }

    private fun solveMachine(machine: Machine, addToPrizeCoordinates: Long): Long {
//        cx = m * ax + n * bx
//        cy = m * ay + n * by
//
//        m = (cx - n * bx) / ax
//        m = (cy - n * by) / ay
//
//        (cx - n * bx) / ax = (cy - n * by) / ay
//
//        ay * (cx - n * bx) = ax * (cy - n * by)
//
//        ay * cx - n * bx * ay = ax * cy - n * by * ax
//
//        n * by * ax - n * bx * ay = ax * cy - ay * cx
//
//        n * (by * ax - bx * ay) = ax * cy - ay * cx
//
//        n = (ax * cy - ay * cx) / (by * ax - bx * ay)
        val ax = machine.buttonA.x.toLong()
        val ay = machine.buttonA.y.toLong()
        val bx = machine.buttonB.x.toLong()
        val by = machine.buttonB.y.toLong()
        val cx = machine.prize.x + addToPrizeCoordinates
        val cy = machine.prize.y + addToPrizeCoordinates

        if(by * ax - bx * ay == 0L || ax == 0L) {
            return 0
        }

        val buttonPressesB = (ax * cy - ay * cx) / (by * ax - bx * ay)
        val buttonPressesA = (cx - bx * buttonPressesB) / ax

        return if(ax * buttonPressesA + bx * buttonPressesB == cx && ay * buttonPressesA + by * buttonPressesB == cy) {
            buttonPressesA * 3 + buttonPressesB
        } else {
            0
        }
    }
}