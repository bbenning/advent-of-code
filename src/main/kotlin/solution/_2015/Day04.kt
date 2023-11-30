package solution._2015

import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day04(val input: List<String>) {
    fun solve1(): Long {
        return solve(input.first(),"00000")
    }

    fun solve2():Long {
        return solve(input.first(),"000000")
    }

    private fun solve(input: String, prefix: String):Long {
        var count = 0L
        while (true) {
            val md5 = (input + count).toString().md5()
            if(md5.startsWith(prefix)) {
                return count
            }
            count++
        }
    }
}