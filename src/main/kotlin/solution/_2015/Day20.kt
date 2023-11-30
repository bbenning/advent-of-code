package solution._2015

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day20(val goal: Int) {
    fun solve1(): Int {
        return generateSequence(1){it + 1}.first{ house ->
            house.divisors().sum() * 10 >= goal
        }    }

    fun solve2():Int {
        return generateSequence(1){it + 1}.first{ house ->
            house.divisors().filter{house <= it * 50 }.sum() * 11 >= goal
        }
    }
}