package solution._2024

import util.mapping.Coord
import util.mapping.Direction

class Day15(val input: List<String>, doubleField: Boolean = false) {

     private val field: List<List<Char>>
     private val width: Int
     private val height: Int

     private val program: List<Direction>

     private val robotLocation: Coord

     init {
         val emptyLine = input.indexOfFirst { it.isEmpty() }

         if(!doubleField){
             field = input.take(emptyLine).map { it.toList() }
         } else {
             field = input.take(emptyLine).map { str ->
                str.fold(""){ acc, c ->
                    when (c) {
                        '#' -> "$acc##"
                        'O' -> "$acc[]"
                        '.' -> "$acc.."
                        else -> "$acc@."
                    }
                }.toList()
             }
         }

         height = field.size
         width = field.first().size

         program = input.drop(emptyLine + 1).joinToString("").map { Direction.fromChar(it) }

         val robotY = field.indexOfFirst { it.contains('@') }
         val robotX = field[robotY].indexOf('@')

         robotLocation = Coord(robotX, robotY)
     }

    fun solve1(): Long {
        return solve()
    }

    fun solve2(): Long {
        return solve()
    }

    private fun solve(): Long {

        val resultField = program.fold(Pair(field, robotLocation)) { acc, direction ->
            moveRobot(acc.first, acc.second, direction)
        }.first

        val score = resultField.mapIndexed { y, chars -> chars.mapIndexed { x, c -> if(c in "O[") 100 * y + x else 0 } }

        return score.sumOf { it.sum() }.toLong()
    }

    private fun moveRobot(field: List<List<Char>>, robotLocation: Coord, direction: Direction): Pair<List<List<Char>>, Coord> {

        val stuffToMove = mutableListOf<Coord>()
        val pushFurther = mutableListOf(robotLocation.move(direction))
        var isStopped = false
        var endIsFound = false

        while(pushFurther.isNotEmpty() && !endIsFound) {
            val pushBlock = pushFurther.removeFirst()

            when(field[pushBlock.y][pushBlock.x]) {
                '#' -> {
                    isStopped = true
                    endIsFound = true
                }
                'O' -> {
                    stuffToMove.add(pushBlock)
                    pushFurther.add(pushBlock.move(direction))
                }
                '[' -> {
                    if(pushBlock !in stuffToMove) {
                        stuffToMove.add(pushBlock)
                        pushFurther.add(pushBlock.move(direction))
                    }
                    val eastNextCoord = pushBlock.move(Direction.EAST)
                    if(eastNextCoord !in stuffToMove) {
                        stuffToMove.add(eastNextCoord)
                        pushFurther.add(eastNextCoord.move(direction))
                    }
                }
                ']' -> {
                    if(pushBlock !in stuffToMove) {
                        stuffToMove.add(pushBlock)
                        pushFurther.add(pushBlock.move(direction))
                    }
                    val westNextCoord = pushBlock.move(Direction.WEST)
                    if(westNextCoord !in stuffToMove) {
                        stuffToMove.add(westNextCoord)
                        pushFurther.add(westNextCoord.move(direction))
                    }
                }
            }
        }

        val nextField = if(isStopped) {
            Pair(field, robotLocation)
        } else {
            val movedBlocks = stuffToMove.map { Triple(it.move(direction), field[it.y][it.x], it) }
            val mutableField = field.map { it.toMutableList() }
            movedBlocks.reversed().forEach { movedBlock ->
                mutableField[movedBlock.first.y][movedBlock.first.x] = movedBlock.second
                mutableField[movedBlock.third.y][movedBlock.third.x] = '.'
            }
            val newRobotPosition = robotLocation.move(direction)
            mutableField[newRobotPosition.y][newRobotPosition.x] = '@'
            mutableField[robotLocation.y][robotLocation.x] = '.'

            Pair(mutableField, newRobotPosition)
        }

        return nextField
    }
}