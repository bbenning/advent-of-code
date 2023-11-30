package solution._2015

import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day11(val input: String) {
    fun solve1(): String {
        return solve(input)
    }

    fun solve2():String {
        return solve(input)
    }

    private tailrec fun solve(pass: String):String {
        val nextPass = add1ToPass(pass)
        return if(isValidPassword(nextPass)) {
            nextPass
        } else {
            solve(nextPass)
        }
    }

    private fun add1ToPass(pass: String): String {
        return pass.foldRight(Pair(1, StringBuffer(""))){ c, (carry, acc) ->
            if(c + carry > 'z') {
                Pair(1, acc.append('a'))
            } else {
                Pair(0, acc.insert(0,c + carry))
            }
        }.second.toString()
    }

    private val threeInARow = "abcdefghijklmnopqrstuvwxyz".windowed(3, 1)
    fun isValidPassword(input: String): Boolean {
        val inARow = threeInARow.any{it in input}
        val notConfusing = listOf('i','o','l').none { it in input }
        val twoDifferentNonOverlappingPairs = ("(\\w)\\1".toRegex().findAll(input).map { it.value.first() }.toSet().size > 1)
        return inARow && notConfusing && twoDifferentNonOverlappingPairs
    }

}