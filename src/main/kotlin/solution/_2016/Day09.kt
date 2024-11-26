package solution._2016

import util.*

class Day09(val input: String) {
    fun solve1(): Long {
        return solve(input, false)
    }

    fun solve2(): Long {
        return solve(input, true)
    }

    private val regex = "(\\(\\d+x\\d+\\))".toRegex()

    private fun solve(str: String, solveInPlace: Boolean): Long {
        var idx = 0
        var length = 0L

        while(idx < str.length) {
            val matchResult = regex.find(str, idx)

            if(matchResult == null){
                val diff = str.length - idx
                idx += diff
                length += diff
            } else {
                val resultRange = matchResult.range
                val repeater = matchResult.value
                val (nextChars, repeatCount) = repeater.ints()

                length += (resultRange.first - idx)
                if(solveInPlace) {
                    length += repeatCount * solve(str.substring(resultRange.last + 1, resultRange.last + 1 + nextChars), true)
                } else {
                    length += repeatCount * nextChars
                }
                idx = resultRange.last + 1 + nextChars
            }
        }

        return length
    }
}