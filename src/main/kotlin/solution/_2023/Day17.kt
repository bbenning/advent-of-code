package solution._2023

import util.mapping.Coord
import util.mapping.Direction

class Day17(val input: List<String>) {
    fun solve1(): Int {
        data class State(val coord: Coord, val path: Set<Coord>, val directions: List<Direction>, val heatLoss: Int)

        val destination = Coord(input.size - 1, input.first().length - 1)
        val visited = mutableMapOf<Pair<Coord,Direction>, Set<Pair<Int, Int>>>()
        val exploring = mutableListOf<State>()
        exploring.add(State(Coord.origin(), emptySet(), emptyList(), 0))
        var minimalHeatLoss = Int.MAX_VALUE

        while(exploring.isNotEmpty()) {
            val curState = exploring.removeFirst()

            if(curState.heatLoss >= minimalHeatLoss) {
                continue
            }

            if(curState.coord == destination) {
                minimalHeatLoss = curState.heatLoss
                continue
            }

            if(curState.directions.isNotEmpty()) {
                val checkDominatedVisited = visited[Pair(curState.coord, curState.directions.last())]!!

                if(!checkDominatedVisited.all {
                        val lastSameSize = curState.directions.takeLastWhile { it == curState.directions.last()}.size
                        lastSameSize < it.first || curState.heatLoss < it.second || (lastSameSize == it.first && curState.heatLoss == it.second)
                    }) {
                    continue
                }
            }

            val movements = if(curState.directions.isEmpty()) {
                listOf(Direction.EAST, Direction.SOUTH)
            } else {
                val curDirection = curState.directions.last()
                listOf(curDirection.turnLeft(), curDirection, curDirection.turnRight())
            }.map { Pair(curState.coord.move(it), it) }

            val movementsInBounds = movements.filter { (coord, _) -> coord.x >= 0 && coord.y >= 0 && coord.x < input.first().length && coord.y < input.size }
            val movementsNotOnPreviousPath = movementsInBounds.filter { (coord, _) -> coord !in curState.path }

            val potentialNewStates = movementsNotOnPreviousPath.map { (coord, direction) ->
                State(coord, curState.path + coord, curState.directions + direction, curState.heatLoss + input[coord.y][coord.x].digitToInt())
            }

            potentialNewStates.forEach { state ->
                val lastSameDirections = state.directions.takeLastWhile { it == state.directions.last() }

                if(lastSameDirections.size < 4) {
                    val maybeVisited = visited[Pair(state.coord, state.directions.last())]
                    if(maybeVisited == null) {
                        visited[Pair(state.coord, state.directions.last())] = setOf(Pair(lastSameDirections.size, state.heatLoss))
                        exploring.add(state)
                    } else if(maybeVisited.all{it.first > lastSameDirections.size || it.second > state.heatLoss}) {
                        visited[Pair(state.coord, state.directions.last())] =
                            (maybeVisited.filter { it.first < lastSameDirections.size || it.second < state.heatLoss } + Pair(lastSameDirections.size, state.heatLoss)).toSet()
                        exploring.add(state)
                    }
                }
            }
        }

        return minimalHeatLoss
    }

    fun solve2(): Int {
        data class State(val coord: Coord, val directions: List<Direction>, val heatLoss: Int)

        val destination = Coord(input.size - 1, input.first().length - 1)
        val visited = mutableMapOf<Pair<Coord,Direction>, Set<Pair<Int, Int>>>()
        val exploring = mutableListOf<State>()
        exploring.add(State(Coord.origin(), emptyList(), 0))
        var minimalHeatLoss = Int.MAX_VALUE

        while(exploring.isNotEmpty()) {
            val curState = exploring.removeFirst()


//            if (curState.directions == listOf(Direction.EAST,Direction.EAST,Direction.EAST,Direction.EAST,Direction.EAST,Direction.EAST,Direction.EAST,Direction.EAST,
//                Direction.SOUTH,Direction.SOUTH,Direction.SOUTH,Direction.SOUTH,
//                Direction.EAST,Direction.EAST,Direction.EAST,Direction.EAST,
//                Direction.SOUTH,Direction.SOUTH,Direction.SOUTH,Direction.SOUTH,Direction.SOUTH,Direction.SOUTH,Direction.SOUTH,Direction.SOUTH).take(curState.directions.size))
//                {
//                    println(curState)
//                }
            if(curState.heatLoss >= minimalHeatLoss) {
                continue
            }

            if(curState.coord == destination) {
                minimalHeatLoss = curState.heatLoss
                continue
            }

            if(curState.directions.isNotEmpty()) {
                val checkDominatedVisited = visited[Pair(curState.coord, curState.directions.last())]

                if(checkDominatedVisited != null && !checkDominatedVisited.all {
                        val lastSameSize = curState.directions.takeLastWhile { it == curState.directions.last()}.size
                        lastSameSize < it.first || curState.heatLoss < it.second || (lastSameSize == it.first && curState.heatLoss == it.second)
                    }) {
                    continue
                }
            }

            val movements = if(curState.directions.isEmpty()) {
                listOf(Direction.EAST, Direction.SOUTH)
            } else {
                val curDirection = curState.directions.last()
                val curDirectionLength = curState.directions.takeLastWhile { it == curDirection }.size
                if(curDirectionLength < 4) {
                    listOf(curDirection)
                } else if (curDirectionLength < 9){
                    listOf(curDirection.turnLeft(), curDirection, curDirection.turnRight())
                } else {
                    listOf(curDirection.turnLeft(), curDirection.turnRight())
                }
            }.map { Pair(curState.coord.move(it), it) }

            val movementsInBounds = movements.filter { (coord, _) -> coord.x >= 0 && coord.y >= 0 && coord.x < input.first().length && coord.y < input.size }
//            val movementsNotOnPreviousPath = movementsInBounds.filter { (coord, _) -> coord !in curState.path }
//            val potentialNewStates = movementsNotOnPreviousPath.map { (coord, direction) ->
//                State(coord, curState.path + coord, curState.directions + direction, curState.heatLoss + input[coord.y][coord.x].digitToInt())
//            }
            val potentialNewStates = movementsInBounds.map { (coord, direction) ->
                State(coord, curState.directions + direction, curState.heatLoss + input[coord.y][coord.x].digitToInt())
            }

            potentialNewStates.forEach { state ->
                val lastSameDirectionsSize = state.directions.takeLastWhile { it == state.directions.last() }.size

                val maybeVisited = visited[Pair(state.coord, state.directions.last())]
                if(maybeVisited == null) {
//                    visited[Pair(state.coord, state.directions.last())] = setOf(Pair(lastSameDirectionsSize, state.heatLoss))
                    exploring.add(state)
                } else if(maybeVisited.all{it.first > lastSameDirectionsSize || it.second > state.heatLoss}) {
                    if(lastSameDirectionsSize >= 4) {
                        visited[Pair(state.coord, state.directions.last())] =
                            (maybeVisited.filter { it.first < lastSameDirectionsSize || it.second < state.heatLoss } + Pair(lastSameDirectionsSize, state.heatLoss)).toSet()
                    }
                    exploring.add(state)
                }
            }
        }

        return minimalHeatLoss
    }
}