package solution._2016

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.longs.product
import util.mapping.Coord
import util.mapping.Direction

class Day05(val input: String) {
    fun solve1(): String {
        val nextChars = mutableListOf<Char>()

        var n = 0
        while(nextChars.size < 8) {
            val str = input + n++
            val hash = str.md5()
            if(hash.startsWith("00000")) {
                nextChars.add(hash[5])
            }
        }

        return nextChars.joinToString("")
    }

    fun solve2(): String {
        val nextChars = mutableMapOf<Int, Char>()

        var n = 0
        while(nextChars.size < 8) {
            val str = input + n++
            val hash = str.md5()
            if(hash.startsWith("00000") && hash[5] >= '0' && hash[5] <= '7') {
                if(nextChars[hash[5].digitToInt()] == null) {
                    nextChars[hash[5].digitToInt()] = hash[6]
                }
            }
        }

        return nextChars.toSortedMap().values.joinToString("")
    }
}