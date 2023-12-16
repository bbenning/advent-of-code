package solution._2023

class Day13(val input: List<String>) {
    fun solve1(): Int {

        val foundHorizontalMirrors = horizontalSimilarityCounts.map{ map -> map.mapIndexed { index, i -> Pair(index + 1, i) }.filter { it.second == 0 } }
        val foundVerticalMirrors = verticalSimilarityCounts.map{map -> map.mapIndexed { index, i -> Pair(index + 1, i) }.filter { it.second == 0 } }

        val horizontalSum = foundHorizontalMirrors.sumOf { it.sumOf { it.first } } * 100
        val verticalSum = foundVerticalMirrors.sumOf { it.sumOf { it.first } }

        return verticalSum + horizontalSum
    }

    fun solve2(): Int {
        val foundHorizontalMirrors = horizontalSimilarityCounts.map{ map -> map.mapIndexed { index, i -> Pair(index + 1, i) }.filter { it.second == 1 } }
        val foundVerticalMirrors = verticalSimilarityCounts.map{map -> map.mapIndexed { index, i -> Pair(index + 1, i) }.filter { it.second == 1 } }

        val horizontalSum = foundHorizontalMirrors.sumOf { it.sumOf { it.first } } * 100
        val verticalSum = foundVerticalMirrors.sumOf { it.sumOf { it.first } }

        return verticalSum + horizontalSum
    }

    private val maps: List<List<String>> = input.fold(listOf(emptyList())){ acc, s ->
        if(s.isEmpty()) {
            acc.plusElement(emptyList())
        } else {
            val lastLast = acc.last().plusElement(s)
            acc.dropLast(1).plusElement(lastLast)
        }
    }

    private val transposedMaps: List<List<String>> = maps.map { map ->
        val width = map.first().length
        val height = map.size

        (0 until width).map { w ->
            (0 until height).map { h ->
                map[h][w]
            }.joinToString("")
        }
    }

    private val horizontalSimilarityCounts = maps.map{map -> findHorizontalReflection(map)}
    private val verticalSimilarityCounts = transposedMaps.map {map -> findHorizontalReflection(map)}

    private fun findHorizontalReflection(map: List<String>): List<Int> {
        return (1 until map.size).mapNotNull { reflectAfterRow ->
            val linesBefore = map.take(reflectAfterRow)
            val linesAfter = map.drop(reflectAfterRow)

            val minSize = minOf(linesBefore.size, linesAfter.size)

            val linesBeforeCut = linesBefore.takeLast(minSize)
            val linesAfterCut = linesAfter.take(minSize)

            linesBeforeCut.zip(linesAfterCut.reversed()).sumOf {
                // find the number of differences
                it.first.zip(it.second).count { (c1, c2) -> c1 != c2 }
            }

        }
    }
}