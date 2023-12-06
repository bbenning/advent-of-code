package solution._2023

import util.ints.product
import util.mapping.Coord

class Day03(val input: List<String>) {
    fun solve1(): Int {
        return getSymbolsWithPartnumbers().sumOf { it.second.sum() }
    }

    fun solve2(): Long {
        return getSymbolsWithPartnumbers()
            .filter { it.first == '*' && it.second.size == 2 }
            .sumOf { it.second.product() }
    }

    private fun getSymbolsWithPartnumbers(): List<Pair<Char, List<Int>>> {

        val partNumberCoords:Map<Coord, Pair<Coord, Int>> = input.flatMapIndexed { y, str ->
            "\\d+".toRegex().findAll(str).flatMap { matchedNumber ->
                matchedNumber.range.map { x ->
                    Pair(
                        Coord(x, y),
                        /** Take starting coordinate so it's unique and duplicates can be removed later. */
                        Pair(Coord(matchedNumber.range.first, y), matchedNumber.value.toInt())
                    )
                }
            }
        }.toMap()

        val symbolPartnumbers: List<Pair<Char, List<Int>>> = input.flatMapIndexed { y, str ->
            str.mapIndexedNotNull{ x, c ->
                if(c.isDigit() || c == '.') {
                    null
                } else {
                    val neighboringCoordinates = Coord(x,y).neighborsDiagonally()
                    val partNumbers: List<Int> = neighboringCoordinates.mapNotNull { coordinate -> partNumberCoords[coordinate] }
                        .toSet().map { it.second }
                    Pair(c, partNumbers)
                }
            }
        }

        return symbolPartnumbers
    }

}