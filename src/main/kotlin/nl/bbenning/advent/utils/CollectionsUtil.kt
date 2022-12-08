package nl.bbenning.advent.utils

object CollectionsUtil {
    // if unequal in length, will ignore empty spaces and move them over, like so:
    // input:
    //[1, 2, 3]
    //[4, 5]
    //[6, 7, 8]
    //
    //result:
    //[1, 4, 6]
    //[2, 5, 7]
    //[3, 8]
    //
    // taken from https://gist.github.com/clementgarbay/49288c006252955c2a3c6139a61ca92a
    fun <E> transpose(xs: List<List<E>>): List<List<E>> {
        // Helpers
        fun <E> List<E>.head(): E = this.first()
        fun <E> List<E>.tail(): List<E> = this.takeLast(this.size - 1)
        fun <E> E.append(xs: List<E>): List<E> = listOf(this).plus(xs)

        xs.filter { it.isNotEmpty() }.let { ys ->
            return when (ys.isNotEmpty()) {
                true -> ys.map { it.head() }.append(transpose(ys.map { it.tail() }))
                else -> emptyList()
            }
        }
    }

    fun <E> sequenceTranspose(input: List<List<E>>, missingElementDefault: E? = null): Sequence<Sequence<E?>> =
        sequence {

            val columnIndices = input.indices

            val maxRowSize = input.maxOf { it.size }
            val rowIndices = 0 until maxRowSize

            columnIndices.forEach { columnIndex ->
                val transposedRow = sequence {
                    rowIndices.forEach { rowIndex ->
                        // instead of getting input[column][row], get input[row][column]
                        val element = input.getOrNull(rowIndex)?.getOrNull(columnIndex)
                        yield(element ?: missingElementDefault)
                    }
                }

                yield(transposedRow)
            }
        }
}