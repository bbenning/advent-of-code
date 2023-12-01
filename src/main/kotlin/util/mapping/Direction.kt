package util.mapping

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    companion object {
        /**
         *  Get direction based on given String: for instance "^>v<", 'v' gives SOUTH
         */
        fun fromChar(c: Char, str: String = "^>v<"): Direction = entries[str.indexOf(c)]
    }

    /**
     * Get direction after turning from c.
     * L = LEFT, R = right, T = turn back.
     */
    fun turn(c: Char, str: String = "LRT"): Direction =
        when (c) {
            str[0] -> turnLeft()
            str[1] -> turnRight()
            str[2] -> turnBack()
            else -> throw IllegalArgumentException("Impossible turn.")
        }

    fun turnLeft(): Direction = Direction.values()[(this.ordinal - 1).mod(4)]
    fun turnRight(): Direction = Direction.values()[(this.ordinal + 1).mod(4)]
    fun turnBack(): Direction = Direction.values()[(this.ordinal + 2).mod(4)]

}