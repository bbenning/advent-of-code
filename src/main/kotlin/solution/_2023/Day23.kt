package solution._2023

import util.mapping.Coord
import util.mapping.Direction

class Day23(val input: List<String>) {
    fun solve1(): Int {
        return solve(true).size - 1
    }

    fun solve2(): Int {
        return solveB() - 1
    }

    private val startCoord = Coord(input.first().indexOfFirst { it == '.' }, 0)
    private val endCoord = Coord(input.last().indexOfFirst { it == '.' }, input.size - 1)

    private fun solve(slippery: Boolean): List<Coord> {
        var longestPath = emptyList<Coord>()

        val exploring = mutableListOf(listOf(startCoord))
        while(exploring.isNotEmpty()) {
            val curPath = exploring.removeFirst()
            if(curPath.last() == endCoord){
                if(curPath.size > longestPath.size) {
                    longestPath = curPath
//                    println("Found new longest path at length: ${longestPath.size}")
                }

//                input.forEachIndexed { y, row ->
//                    row.forEachIndexed { x, c ->
//                        if(Coord(x,y) in curPath) {
//                            print('O')
//                        } else {
//                            print(c)
//                        }
//                    }
//                    println()
//                }
//
//                println()

                continue
            }

            val curSlide = input[curPath.last().y][curPath.last().x]

            val neighbors = (if (slippery) {(when (curSlide) {
                '.' -> curPath.last().neighbors()
                in "^><v" -> listOf(curPath.last().move(Direction.fromChar(curSlide)))
                else -> throw IllegalStateException("Should be impossible.")
            })} else {curPath.last().neighbors()}).filter { it.x >= 0 && it.x < input.first().length && it.y >= 0 && it.y < input.size && input[it.y][it.x] != '#' && it !in curPath }

            neighbors.forEach { exploring.add(curPath + it) }
        }

        return longestPath
    }

    private fun solveB(): Int {

        // first gather linepieces
        val linePiecesList = mutableListOf<Triple<Coord, Coord, Int>>()

        val exploringLinePieces = mutableListOf(listOf(startCoord))

        while(exploringLinePieces.isNotEmpty()) {
            val curLinePiece = exploringLinePieces.removeFirst()

            val validNeighbors = curLinePiece.last().neighbors()
                .filter { it !in curLinePiece && it.x >= 0 && it.x < input.first().length && it.y >= 0 && it.y < input.size && input[it.y][it.x] != '#'}

            if(validNeighbors.size == 1) {
                exploringLinePieces.add(curLinePiece + validNeighbors.first())
            } else if(validNeighbors.size > 1 || curLinePiece.last() == endCoord) {
                val linePiece = Triple(curLinePiece.first(), curLinePiece.last(), curLinePiece.size)
                if(linePiece !in linePiecesList){
                    linePiecesList.add(linePiece)
                    validNeighbors.forEach { exploringLinePieces.add(listOf(curLinePiece.last(), it))}
                }
            }
        }

        val linePiecesByStartCoord = linePiecesList.groupBy { it.first }

        val exploring = sortedSetOf(Comparator { t1, t2 ->
            val s1 = t1.sumOf { it.third } - t1.size + 1
            val s2 = t2.sumOf { it.third } - t2.size + 1
            val diff = s2 - s1
            if(diff != 0) {
                diff
            } else {
                t1.hashCode().compareTo(t2.hashCode())
            }
        }, listOf(linePiecesByStartCoord.getValue(startCoord).first()))

        var longestLength = 0
        while(exploring.isNotEmpty()) {
            val curPath = exploring.first()
            exploring.remove(curPath)
            val curNode = curPath.last().second
            if(curNode == endCoord) {
                val length = curPath.sumOf { it.third } - curPath.size + 1
                if(length > longestLength) {
                    println("New Longest Length: ${length}")
                    longestLength = length
                }
                continue
            }

            val curVisited = curPath.flatMap { listOf(it.first, it.second) }.toSet()
            val possibleNextSegments = linePiecesByStartCoord.getOrDefault(curNode, emptyList()).filter { it.second !in curVisited }

            possibleNextSegments.forEach { exploring.add(curPath + it) }
        }

        return longestLength
    }
}