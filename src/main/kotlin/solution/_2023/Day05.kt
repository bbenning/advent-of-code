package solution._2023

import util.*

class Day05(val input: List<String>) {
    fun solve1(): Long {
        val seedRanges = input.first().longs().map { LongRange(it, it) }
        return solve(seedRanges)
    }


    fun solve2():Long {
        val seedRanges = input.first().longs().chunked(2)
            .map { it.first() until it.first() + it[1] }.let { RangeUtil.unionAllLongRanges(it) }

        return solve(seedRanges)
    }

    private data class RangeMap(val sourceRange: LongRange, val movement: Long)

    private fun solve(seedRanges: List<LongRange>): Long {
        val listOfMaps: List<List<RangeMap>> = input
            .filter { it.isNotEmpty() }.drop(1)
            .fold(emptyList()) { acc, s ->
                if("map" in s) {
                    acc.plusElement(emptyList())
                } else {
                    val (dest, source, move) = s.longs()
                    val newRangeMap = RangeMap(source until source + move, dest - source)
                    val lastWithNewRangeMap = acc.last().plus(newRangeMap)
                    acc.updatedLast(lastWithNewRangeMap)
                }
            }

        val listOfMapRangesWithoutMovement: List<List<RangeMap>> = listOfMaps.map{ listOfMap ->
            val default = 0L .. Long.MAX_VALUE
            RangeUtil.minusAllLongRanges(default, listOfMap.map { it.sourceRange })
                .map{RangeMap(it, 0)}
        }

        // A map here consists of multiple lines that are each ranges with movements. This has it for the whole space of longs.
        val allMapsCombined = listOfMaps.zip(listOfMapRangesWithoutMovement).map { it.first + it.second }

        val result = allMapsCombined.fold(seedRanges){ acc: List<LongRange>, rangeMaps: List<RangeMap> ->
            val passThroughMap = acc.flatMap { seedRange: LongRange ->
                val resultingSeedRanges: List<LongRange> = rangeMaps.map { rangeMap ->
                    val intersection = RangeUtil.intersection(rangeMap.sourceRange, seedRange)
                    intersection.first + rangeMap.movement .. intersection.last + rangeMap.movement
                }

                resultingSeedRanges
            }

            // remove empty ranges and merge possible ranges to make everything smaller
            RangeUtil.unionAllLongRanges(passThroughMap)
        }

        return result.minOf{it.first}

    }
}