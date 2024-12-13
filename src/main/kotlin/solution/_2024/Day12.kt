package solution._2024

import util.mapping.Coord
import util.mapping.Direction

class Day12(val input: List<String>) {
    fun solve1(): Long {
        return solve(false)
    }

    fun solve2(): Long {
        return solve(true)
    }

    private val cropLocations: Map<Char, Set<Coord>> = input.flatMapIndexed { y, s -> s.mapIndexed { x, c -> Pair(c, Coord(x, y)) } }.groupBy { it.first }.mapValues { it.value.map { it.second }.toSet() }

    private fun solve(discount: Boolean): Long {
        data class Region(val crop: Char, val cropLocations: Set<Coord>)

        val width = input.first().length
        val height = input.size
        val regions = mutableSetOf<Region>()

        tailrec fun findRegion(crop: Char, todo: List<Coord>, acc: Set<Coord>): Set<Coord> {
            if(todo.isEmpty()) {
                return acc
            }

            val coord = todo.first()

            val validCropNeighbors = coord.neighbors().filter {
                it.x in 0 until width && it.y in 0 until height && it !in acc && it !in todo && it in cropLocations[crop]!!
            }.toSet()

            return findRegion(crop, todo.drop(1) + validCropNeighbors, acc + coord)
        }

        val allCropLocationsLeft = cropLocations.flatMap { (_, value) -> value }.toMutableList()
        while(allCropLocationsLeft.isNotEmpty()) {
            val curCropLocation = allCropLocationsLeft.removeFirst()
            val curCrop = input[curCropLocation.y][curCropLocation.x]
            val cropLocations = findRegion(curCrop, listOf(curCropLocation), emptySet())

            allCropLocationsLeft.removeAll(cropLocations)
            regions.add(Region(curCrop, cropLocations))
        }

        fun area(region: Region):Int = region.cropLocations.size
        fun perimeter(region: Region): Int {
            return region.cropLocations.fold(0){ acc, l ->
                val neighbors = l.neighbors().filter { it in region.cropLocations }.size
                acc + 4 - neighbors
            }
        }

        fun perimeterDiscount(region: Region): Int {
            // probably not the nicest way, but a way of doing this is:
            // you could take 4 sides, determine there are no neighbors on top/bottom/right/left
            // then traverse from the start

            return Direction.entries.toTypedArray().sumOf { direction ->
                val borders = region.cropLocations.filter { location ->
                    val maybeBorder = location.move(direction)
                    maybeBorder !in region.cropLocations
                }

                fun calculateBorders(groups: Collection<List<Coord>>, sortKey: (Coord) -> Int): Int {
                    return groups.sumOf { group ->
                        val sorted = group.sortedBy(sortKey)
                        sorted.fold(Pair(0, Coord(-10, -10))) { acc, curCoord ->
                            if (sortKey(curCoord) > sortKey(acc.second) + 1) {
                                Pair(acc.first + 1, curCoord)
                            } else {
                                Pair(acc.first, curCoord)
                            }
                        }.first
                    }
                }

                val border = if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                    calculateBorders(borders.groupBy { it.y }.values) { it.x }
                } else {
                    calculateBorders(borders.groupBy { it.x }.values) { it.y }
                }

                border
            }
        }

        val result = regions.sumOf { area(it).toLong() * if(discount) perimeterDiscount(it) else perimeter(it)}

        return result
    }
}