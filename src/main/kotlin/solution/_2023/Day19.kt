package solution._2023

import util.RangeUtil
import util.ints
import util.longs.product
import util.words

typealias Part = Map<String, LongRange>

class Day19(val input: List<String>) {

    fun solve1(): Long {
        val partRegex = "^\\{x=(\\d+),m=(\\d+),a=(\\d+),s=(\\d+)}$".toRegex()
        val partStrings = input.drop(instructionStrings.size + 1)
        val parts: List<Part> = partStrings.map { partString ->
            val (x, m, a, s) = partRegex.findAll(partString).flatMap { it.groupValues.drop(1).map { it.toLong() } }.toList()

            mapOf("x" to x..x, "m" to m..m, "a" to a..a, "s" to s..s)
        }
        val solutions = parts.flatMap { solve(it).first }.filter { it.all { !it.value.isEmpty() } }
        return solutions.sumOf { it.values.sumOf { it.first } }
    }

    fun solve2(): Long {
        val part = mapOf("x" to 1L..4000L, "m" to 1L..4000L, "a" to 1L..4000L, "s" to 1L..4000L)
        val solutions = solve(part).first.filter { it.all { !it.value.isEmpty() } }

        return solutions.sumOf { it.values.map { it.last - it.first + 1}.product() }
    }

    private val instructionStrings = input.takeWhile { it.isNotEmpty() }

    private val instructions: Map<String, (Part) -> List<Pair<Part, String>>> = instructionStrings.map { instrStr ->
        val (name, functionStr) = instrStr.dropLast(1).split("{")

        val func: (Part) -> List<Pair<Part, String>> = functionStr.let {
            val split = it.split(",")

            val conditionalStrings = split.takeWhile { it.contains(":") }
            val endpoint: String = split.drop(conditionalStrings.size).first()

            val conditions: List<Pair<(Part) -> Pair<Part, Part>, String>> = conditionalStrings.map {
                val (conditionStr, resultStr) = it.split(":")
                val smallerThan = conditionStr.contains("<")
                val arg1 = conditionStr.words().first()
                val arg2 = conditionStr.ints().first()

                Pair({part: Part ->
                    val range = part[arg1]!!

                    if(smallerThan) {
                        val lower = RangeUtil.intersection(0L until arg2, range)
                        val upper = RangeUtil.intersection(arg2  .. 4000L, range)
                        val lowerPart: Part = part.plus(Pair(arg1, lower))
                        val upperPart: Part = part.plus(Pair(arg1, upper))
                        Pair(lowerPart, upperPart)
                    } else {
                        val lower = RangeUtil.intersection(0L .. arg2, range)
                        val upper = RangeUtil.intersection(arg2 + 1  .. 4000L, range)
                        val lowerPart: Part = part.plus(Pair(arg1, lower))
                        val upperPart: Part = part.plus(Pair(arg1, upper))
                        Pair(upperPart, lowerPart)
                    }}, resultStr)
            }

            ({ part: Part ->

                // this part needs to be pulled through the list of conditions and then continuing the right side of the pair,
                // where the left side splits off.
                val resolvedParts = mutableListOf<Pair<Part, String>>()
                var remainingPart = part
                conditions.forEach { condition ->
                    val (resolvedPart, remainder) = condition.first(remainingPart)
                    remainingPart = remainder // don't have the mental energy to write this properly now
                    resolvedParts.add(Pair(resolvedPart, condition.second))
                }

                resolvedParts.add(Pair(remainingPart, endpoint))

                resolvedParts
            })
        }

        Pair(name, func)
    }.toMap()

    private fun solve(part: Part): Pair<List<Part>, List<Part>> {

        val partsAtWorkFlows = mutableMapOf<String, List<Part>>()
        partsAtWorkFlows["in"] = listOf(part)

        while(partsAtWorkFlows.any { it.key !in listOf("R", "A") && it.value.isNotEmpty()}) {
            val todo = partsAtWorkFlows.filter { it.key !in listOf("R", "A") && it.value.isNotEmpty()}.toList()

            todo.forEach { (workFlow, partsList) ->
                partsList.forEach { part ->
                    val listOfNewPartsWithWorkflows = instructions[workFlow]!!(part)
                    partsAtWorkFlows[workFlow] = partsAtWorkFlows[workFlow]!!.filter { it != part}
                    listOfNewPartsWithWorkflows.forEach { (newPart, newWorkFlow) ->
                        val knownPartsForWorkflow = partsAtWorkFlows[newWorkFlow]
                        if(knownPartsForWorkflow == null) {
                            partsAtWorkFlows[newWorkFlow] = listOf(newPart)
                        } else {
                            partsAtWorkFlows[newWorkFlow] = knownPartsForWorkflow + newPart
                        }
                    }
                }
            }
        }

        return Pair(partsAtWorkFlows["A"]!!, partsAtWorkFlows["R"]!!)
    }
}