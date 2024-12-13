package util.mapping

import kotlin.math.abs

data class Coord(val x: Int, val y: Int) {
    companion object {
        fun origin() = Coord(0, 0)
        fun directionsDiagonally(): Set<Coord> = setOf(Coord(1,0), Coord(-1, 0), Coord(0, 1), Coord(0, -1), Coord(1, 1), Coord( 1, -1), Coord(-1, 1), Coord(-1, -1))
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

    infix operator fun plus(coord: Coord): Coord = Coord(this.x + coord.x, this.y + coord.y)
    infix operator fun minus(coord: Coord): Coord = Coord(this.x - coord.x, this.y - coord.y)
    infix operator fun times(times: Int): Coord = Coord(this.x * times, this.y * times)

    fun manhattenDistance(other: Coord = Coord(0, 0)): Int = abs(this.y - other.y) + abs(this.x - other.x)

    fun neighbors(): Set<Coord> {
        return setOf(Coord(x - 1, y), Coord(x + 1, y), Coord(x, y - 1), Coord(x, y + 1))
    }

    fun neighborsList(): List<Coord> {
        return listOf(Coord(x - 1, y), Coord(x + 1, y), Coord(x, y - 1), Coord(x, y + 1))
    }

    fun neighborsDiagonally(): Set<Coord> {
        return setOf(
            Coord(x - 1, y - 1), Coord(x - 1, y), Coord(x - 1, y + 1),
            Coord(x, y -1),  Coord(x, y + 1),
            Coord(x + 1, y - 1), Coord(x + 1, y), Coord(x + 1, y +1)
        )
    }
}

