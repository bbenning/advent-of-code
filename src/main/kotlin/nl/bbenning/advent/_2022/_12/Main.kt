package nl.bbenning.advent._2022._12

import java.io.File
import kotlin.collections.ArrayDeque

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input12.txt").readLines()

        val myLocation = inputStrings.mapIndexed { index, s -> Pair(s.indexOf('S'), index) }.first { it.first >= 0 }
        val endLocation = inputStrings.mapIndexed { index, s -> Pair(s.indexOf('E'), index) }.first { it.first >= 0 }

        val map = inputStrings.map { it.map { l -> if(l == 'E') 'z' else if (l == 'S') 'a' else l }}

        fun findSolution(start: Pair<Int, Int>): List<Pair<Int, Int>>? {
            val mapOfPaths = mutableMapOf(Pair(start, listOf<Pair<Int, Int>>()))
            val underConsideration = ArrayDeque<Pair<Int, Int>>()
            underConsideration.add(start)

            while(!mapOfPaths.contains(endLocation) && underConsideration.isNotEmpty()) {
                val curPoint = underConsideration.removeFirst()
                val nextSteps = listOf(Pair(curPoint.first - 1, curPoint.second), Pair(curPoint.first + 1, curPoint.second), Pair(curPoint.first, curPoint.second - 1), Pair(curPoint.first, curPoint.second + 1))
                    .filter { it.first >= 0 && it.second >= 0 && it.second < map.size && it.first < map[0].size && // check bounds
                            map[it.second][it.first].code - map[curPoint.second][curPoint.first].code <= 1 && // check not climbing too much
                            !mapOfPaths.contains(it) && !underConsideration.contains(it) // check not already earlier went there
                    }

                underConsideration.addAll(nextSteps)
                nextSteps.forEach { nextStep ->
                    mapOfPaths[nextStep] = mapOfPaths[curPoint]!! + nextStep
                }
            }

            return mapOfPaths[endLocation]
        }

        println("Answer to 12a: ${findSolution(myLocation)!!.size}")

        val results = map.flatMapIndexed { y, list ->
            list.mapIndexed{x, c ->
                if(c == 'a') { findSolution(Pair(x, y)) } else { null }
            }.filterNotNull()
        }

        println("Answer to 12b: ${results.map{it.size}.sorted().take(1)}")
    }
}