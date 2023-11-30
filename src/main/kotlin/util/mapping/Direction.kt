package util.mapping

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    companion object {
        /**
         *  Get direction based on given String: for instance "^>v<", 'v' gives SOUTH
         */
        fun fromChar(c: Char, str: String = "^>v<"): Direction = entries[str.indexOf(c)]
    }
}