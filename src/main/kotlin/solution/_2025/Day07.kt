package solution._2025

class Day07(val input: List<String>) {

    fun solve1(): Long {
        val startIndex = input.first().indexOf('S')

        val (_, splitCount) = input.drop(1).fold(Pair(setOf(startIndex), 0)){ (beamLocations, splitCount), line ->

            val (newBeamLocations, newCount) = beamLocations.fold(Pair(setOf<Int>(), splitCount)) { (newBeamLocations, newSplitCount), previousBeamLocation ->
                if(line[previousBeamLocation] == '^') {
                    val nextPositions = listOf(previousBeamLocation - 1, previousBeamLocation + 1)
                        .filter { it in line.indices }

                    Pair(newBeamLocations + nextPositions, newSplitCount + 1)
                } else {
                    Pair(newBeamLocations + previousBeamLocation, newSplitCount)
                }
            }

            Pair(newBeamLocations, newCount)
        }

        return splitCount.toLong()
    }

    fun solve2(): Long {
        val startIndex = input.first().indexOf('S')
        val width = input.first().length
        val grid = input.drop(1)

        val startState = LongArray(width)
        startState[startIndex] = 1L

        val finalState = grid.fold(startState) { currentCounts, rowString ->
            val nextCounts = LongArray(width)

            for (i in 0 until width) {
                val paths = currentCounts[i]
                if (paths == 0L) continue

                if (rowString[i] == '^') {
                    if (i - 1 >= 0) nextCounts[i - 1] += paths
                    if (i + 1 < width) nextCounts[i + 1] += paths
                } else {
                    nextCounts[i] += paths
                }
            }
            nextCounts
        }

        return finalState.sum()
    }
}