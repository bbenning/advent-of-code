package solution._2015

import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day10(val input: String) {
    private val regex = "(\\d)\\1*".toRegex()

    fun solve1(): Int {
        return (1..40).fold(input){acc, _ -> iterate (acc)}.length
    }

    fun solve2():Int {
        return (1..50).fold(input){acc, _ -> iterate (acc)}.length
    }

    fun iterate(str: String):String {
        val res = regex.findAll(str).map { matchResult ->
            "${matchResult.value.length}${matchResult.value.first()}"
        }.joinToString("")

        return res
    }
}