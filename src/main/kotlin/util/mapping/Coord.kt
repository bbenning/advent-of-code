package util.mapping

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
}