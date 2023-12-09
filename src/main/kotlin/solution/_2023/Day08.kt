package solution._2023

import util.*

class Day08(val input: List<String>) {

    val route = input.first()

    val intersections = input.drop(2).associate {
        val (source, left, right) = it.words()
        source to Pair(left, right)
    }

    fun solve1(): Int {
        val steps = mutableListOf("AAA")

        while(steps.last() != "ZZZ") {
            val choice = route[(steps.size - 1) % route.length] // need to discount AAA
            val step = intersections[steps.last()]!!.let { if(choice == 'L') it.first else it.second }
            steps.add(step)
        }

        return steps.size - 1
    }


    fun solve2():Long {

        // need to figure out for each ghost its path until it loops ... more importantly, the only important information
        // is the number of steps until each found endpoint and an offset
        val ghostStarts = intersections.filter { it.key.endsWith("A") }.keys.toList()

        // for each ghost calculate its path until it loops + its stepcount when it loops
        val pathsWithStepCounts = ghostStarts.map{ ghostStart ->

            val steps = mutableListOf(Triple(ghostStart, 0, 0))
            val duplicateCheckSet = mutableSetOf(Pair(ghostStart, 0))

            while(true) {
                val choice = route[(steps.size - 1) % route.length] // need to discount AAA
                val step = intersections[steps.last().first]!!.let { if(choice == 'L') it.first else it.second }
                val newStep = Triple(step, (steps.last().second + 1) % route.length, steps.last().third + 1)
                val newState = Pair(step, (steps.last().second + 1) % route.length)

                steps.add(newStep)
                if(newState in duplicateCheckSet){
                    break
                } else{
                    duplicateCheckSet.add(newState)
                }
            }

            steps.toList()
        }

        // I guess i want it to be like List<Pair<Stepcount, CycleSize>>, so I could iterate over it easily,
        // or use it for calculation.
        val stepsAndCyclesToEndpointsPerGhost = pathsWithStepCounts.map {
            val loopsBackOnto = it.find { cycleStep -> cycleStep.first == it.last().first && cycleStep.second == it.last().second }!!
            val loopDistance = it.last().third - loopsBackOnto.third

            // only 1 endpoint in the cycle of each ghost, which makes life quite a bit easier.
            it.filter { it.first.endsWith("Z") }.map { Pair(it.third.toLong(), loopDistance) }.first()
        }

        // apparently takes about an hour on my machine.
        //            .toMutableList()
        //        while(stepsAndCyclesToEndpointsPerGhost.any{it.first != stepsAndCyclesToEndpointsPerGhost[0].first}) {
        //            if(stepsAndCyclesToEndpointsPerGhost[0].first >= 14_299_763_833_181L) {
        //                println()
        //            }
        //            val lowestStepCountAndCycle = stepsAndCyclesToEndpointsPerGhost.minBy { it.first }
        //            val lowestStepCountAndCycleIdx = stepsAndCyclesToEndpointsPerGhost.indexOf(lowestStepCountAndCycle)
        //
        //            val newStepcount = lowestStepCountAndCycle.first + lowestStepCountAndCycle.second
        //            stepsAndCyclesToEndpointsPerGhost[lowestStepCountAndCycleIdx] = Pair(newStepcount, lowestStepCountAndCycle.second)
        //        }

        // Was first thinking about lcm until it became clear that it's not true for the general case because it doesn't loop back to the start.
        // It will work in this case though since all endpoints precisely align on the first step of the route and this is also divisible
        // by that distance.
        //        val endpoints:List<Long> = stepsAndCyclesToEndpointsPerGhost.map { it.first }
        //        val lcm = lcm(endpoints[0], endpoints[1], *endpoints.drop(2).toLongArray())

        // This gives the general solution:
        val result = alignPeriodsAndPhases(stepsAndCyclesToEndpointsPerGhost)

        return result.first + result.second
    }

}