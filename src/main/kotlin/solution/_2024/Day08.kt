package solution._2024

import com.github.shiguruikai.combinatoricskt.combinations
import util.mapping.Coord

class Day08(val input: List<String>) {
    private val width = input.first().length
    private val height = input.size

    private val antennaMap: Map<Char, List<Coord>> = input.flatMapIndexed { y, s ->
        s.mapIndexedNotNull { x, c ->
            if(c == '.') null else Pair(c, Coord(x, y))
        }
    }.groupBy { it.first }.mapValues { it.value.map { it.second } }

    fun solve1(): Int {

        val antinodes: Set<Coord> = antennaMap.values.flatMap { antennaCoords ->
            val combinations = antennaCoords.combinations(2).toList()
            combinations.flatMap { (antenna1Coord, antenna2Coord) ->
                val diff = antenna1Coord - antenna2Coord
                setOf(antenna1Coord + diff, antenna2Coord - diff)
            }
        }.filter{it.x in 0 until width && it.y in 0 until height}.toSet()

        return antinodes.size
    }

    fun solve2(): Int {

        val antinodes: Set<Coord> = antennaMap.values.flatMap { antennaCoords ->
            val combinations = antennaCoords.combinations(2).toList()
            combinations.flatMap { (antenna1Coord, antenna2Coord) ->
                val diff = antenna1Coord - antenna2Coord
                val antinodes = mutableSetOf<Coord>()
                var antinode = antenna1Coord

                while(antinode.x in 0 until width && antinode.y in 0 until height) {
                    antinodes.add(antinode)
                    antinode -= diff
                }

                antinode = antenna1Coord + diff
                while(antinode.x in 0 until width && antinode.y in 0 until height) {
                    antinodes.add(antinode)
                    antinode += diff
                }
                antinodes
            }
        }.toSet()

        return antinodes.size
    }
}