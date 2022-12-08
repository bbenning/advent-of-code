package nl.bbenning.advent._2022._08

import nl.bbenning.advent.utils.CollectionsUtil
import java.io.File

object Main {
    private fun countVisibleFromPoint(l: List<Int>, myHeight: Int) = l.fold(Triple(0, myHeight, false)){ (count, maxHeight, wasBlocked), height  ->
        if(wasBlocked) {
            Triple(count, maxHeight, wasBlocked)
        } else {
            if(height < myHeight) {
                Triple(count + 1, maxHeight, false)
            } else {
                Triple(count +  1, height, true)
            }
        }
    }.first

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input08.txt").readLines()

        val map = inputStrings.map { strList -> strList.map { Integer.valueOf("" + it) } }
        val mapReversed = map.map {it.reversed()}
        val mapTransposed = CollectionsUtil.transpose(map)
        val mapTransposedAndReversed = CollectionsUtil.transpose(map).map { it.reversed() }

        val visibility = map.mapIndexed { y, line ->
            line.mapIndexed { x, treeHeight ->
                val isVisibleFromWest = line.filterIndexed { idx, _ -> idx < x }.all { h -> h < treeHeight }
                val isVisibleFromEast = line.filterIndexed { idx, _ -> idx > x }.all { h -> h < treeHeight }
                val isVisibleFromNorth = mapTransposed[x].filterIndexed { idx, _ -> idx < y }.all { h -> h < treeHeight }
                val isVisibleFromSouth = mapTransposed[x].filterIndexed { idx, _ -> idx > y }.all { h -> h < treeHeight }

                if (isVisibleFromWest || isVisibleFromEast || isVisibleFromNorth || isVisibleFromSouth) 1 else 0
            }
        }

        println("Answer to 08a: ${visibility.sumOf { it.sum() }}")

        val horizontalSize = map[0].size
        val verticalSize = map.size
        val visibleFromPoint = map.mapIndexed { y, line ->
            line.mapIndexed { x, treeHeight ->
                val isVisibleToWest = countVisibleFromPoint(mapReversed[y].drop(horizontalSize - x), treeHeight)
                val isVisibleToEast = countVisibleFromPoint(map[y].drop(x+1), treeHeight)
                val isVisibleToNorth = countVisibleFromPoint(mapTransposedAndReversed[x].drop(verticalSize - y), treeHeight)
                val isVisibleToSouth = countVisibleFromPoint(mapTransposed[x].drop(y+1), treeHeight)

                isVisibleToEast * isVisibleToWest * isVisibleToNorth * isVisibleToSouth
            }
        }

        println("Answer to 08b: ${visibleFromPoint.maxOf { it.max() }}")
    }
}