package nl.bbenning.advent.utils

/**
 * Walks n-dimensional paths one dimension at a time.
 * So, give it a list of symbols as follows:
 * - the symbols must be even, since for every 'left' there has to be a 'right' possible
 * - first symbol counts as incrementing the first point in space where it ends up,
 * - second symbol decrementing the first point in space where it ends up,
 * - third symbol incrementing the second point in space where it ends up,
 * - etc
 */
class PathWalker(val symbols: String) {

    private val dimensions = symbols.length / 2

    fun getResultAfterWalking(path: String): List<Int> {
        return path.fold(List(dimensions){0}){ acc, c ->
            val idxInstruction = symbols.indexOf(c)
            val increment = if(idxInstruction % 2 == 0) 1 else -1
            val dimension = idxInstruction / 2

            acc.updated(dimension, acc[dimension] + increment)
        }
    }

    /**
     * Returns the number of steps taken until point is found or -1 if it's never found.
     */
    fun getStepsUntilPointDiscovered(path: String, point: List<Int>): Int {
        val curPoint = MutableList(dimensions) {0}
        var step = 0

        do {
            if(curPoint == point) {
                return step
            }
            val c = path[step]
            val idxInstruction = symbols.indexOf(c)
            val increment = if(idxInstruction % 2 == 0) 1 else -1
            val dimension = idxInstruction / 2

            curPoint[dimension] = curPoint[dimension] + increment
            step++
        } while(step < path.length)

        return -1
    }

    fun getPointsOnPath(path:String): List<List<Int>> {
        val firstPoint = List(dimensions){0}
        return path.fold(Pair(listOf(firstPoint), firstPoint)){ acc, c ->
            val idxInstruction = symbols.indexOf(c)
            val increment = if(idxInstruction % 2 == 0) 1 else -1
            val dimension = idxInstruction / 2

            val newPoint : List<Int> = acc.second.updated(dimension, acc.second[dimension] + increment)
            Pair(acc.first.plusElement(newPoint), newPoint)
        }.first
    }

}