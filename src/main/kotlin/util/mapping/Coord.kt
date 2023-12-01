package util.mapping

import kotlin.math.abs

data class Coord(val x: Int, val y: Int) {
    companion object {
        fun origin() = Coord(0, 0)
    }

    fun move(direction: Direction): Coord {
        return when(direction) {
            Direction.NORTH -> Coord(x, y - 1)
            Direction.EAST -> Coord(x + 1, y)
            Direction.SOUTH -> Coord(x , y + 1)
            Direction.WEST -> Coord(x - 1, y)
        }
    }
    fun move(direction: Direction, steps: Int): List<Coord> =
        (1 .. steps).fold(listOf(this)){ acc, _ ->
            acc + acc.last().move(direction)
        }.drop(1) // dropping 1 because otherwise it wouldn't strictly be the result of a movement.

    fun manhattenDistance(other: Coord = Coord(0, 0)): Int = abs(this.y - other.y) + abs(this.x - other.x)
}

