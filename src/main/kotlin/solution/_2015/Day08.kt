package solution._2015

import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day08(val input: List<String>) {
    fun solve1(): Int {
        return inCode().sum() - inMemory().sum()
    }

    fun solve2():Int {
        return encodedLengths().sum() - inCode().sum()
    }

    private fun inCode():List<Int> {
        return input.map {
            it.length
        }
    }

    private fun inMemory():List<Int> {
        return input.map {
            it.replace("\\\\", "q").replace("\\\"","q").replace("\\\\x[0-9a-f]{2}".toRegex(),"Q").length - 2
        }
    }

    private fun encodedLengths(): List<Int> {
        return input.map {
            it.replace("\\", "\\\\").replace("\"", "\\\"").length + 2
        }
    }
}