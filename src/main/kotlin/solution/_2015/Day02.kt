package solution._2015

import util.*

class Day02(val input: List<String>) {
    fun solve1(): Int {
        val squareFeet = input.map{
            val (l, w, h) = it.ints()
            2*l*w + 2*w*h + 2*h*l + minOf(l * w, l * h, w * h)
        }
        return squareFeet.sum()
    }

    fun solve2():Int {
        val ribbons = input.map{
            val (l, w, h) = it.ints().sorted()
            2*l + 2*w + l*w*h
        }

        return ribbons.sum()
    }
}