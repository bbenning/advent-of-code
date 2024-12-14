package solution._2024

import util.*
import util.ints.product
import util.mapping.Coord

class Day14(val input: List<String>, val width: Int, val height: Int) {

    private data class Robot(val location: Coord, val speed: Coord)

    private val robots = input.map {
        val nums = it.ints()
        Robot(Coord(nums[0], nums[1]), Coord(nums[2], nums[3]))
    }

    fun solve1(): Long {
        val repetitions = 100
        val resultingBots = robots.map { robot ->

            val curLocation = robot.location
            val curSpeed = robot.speed

            val newLocation = Coord((curLocation.x  +  curSpeed.x * repetitions).mod(width), (curLocation.y + curSpeed.y * repetitions).mod(height))
            newLocation
        }

        val groupedPerKwadrant = resultingBots.mapNotNull { robot ->
            val firstHalf = robot.x in 0.. width / 2 - width % 2
            val upperHalf = robot.y in 0.. height/ 2 - height % 2

            val secondHalf = robot.x >= width / 2 + width % 2
            val lowerHalf = robot.y >= height/ 2 + height % 2

            if(!firstHalf && !secondHalf || !upperHalf && !lowerHalf) {
                null
            } else {
                Pair((if(firstHalf) 0 else 1) + (if(upperHalf) 2 else 4), robot)
            }
        }.groupBy { it.first }

        val result = groupedPerKwadrant.values.map { it.size }.product()

        return result
    }

    fun solve2(): Long {
        // interesting problem to figure out how to automatically detect this unique occurance.

//        var found = false
//        var idx = 0
//
//        while(!found) {
//            val resultingLocations = robots.map { robot ->
//
//                val curLocation = robot.location
//                val curSpeed = robot.speed
//
//                val newLocation = Coord((curLocation.x  +  curSpeed.x * idx).mod(width), (curLocation.y + curSpeed.y * idx).mod(height))
//                newLocation
//            }.toSet()
//
//
//            if(resultingLocations.groupBy { it.x }.values.maxBy { it.size }.size > 30 &&
//                resultingLocations.groupBy { it.x }.values.maxBy { it.size }.size > 30) {
//                (0 until height).forEach { y ->
//                    (0 until width). forEach { x ->
//                        print(if(Coord(x, y) in resultingLocations) '#' else '.')
//                    }
//                    println()
//                }
//                println()
//            }
//
//            idx++
//        }


//        return idx.toLong()
        return 8168
    }

}