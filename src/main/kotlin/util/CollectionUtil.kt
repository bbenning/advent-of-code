package util

import util.mapping.Coord

fun <T : List<List<U>>, U> T.applyToRectangle(fromCoord: Coord, toCoord: Coord, apply: (U) -> U): List<List<U>> {
    val mutable = this.map { it.toMutableList() }.toMutableList()

    for (y in fromCoord.y..toCoord.y) {
        for (x in fromCoord.x..toCoord.x) {
            mutable[y][x] = apply(mutable[y][x])
        }
    }

    return mutable
}

fun <T: List<U>, U> T.toPair():Pair<U, U> {
    if(this.size != 2) {
        throw IllegalArgumentException("Cannot simply make a pair, size is ${this.size}")
    }

    return Pair(this.first(), this.last())
}

fun <T: List<Int>> T.multiplyInts(other: List<Int>): List<Int> {
    if(this.size != other.size) {
        throw IllegalArgumentException("Sizes are different, so don't know how to multiply.")
    }
    
    return this.zip(other).fold(emptyList()){acc, pair -> acc + pair.first * pair.second }
}

fun <T: List<Long>> T.multiplyLongs(other: List<Long>): List<Long> {
    if(this.size != other.size) {
        throw IllegalArgumentException("Sizes are different, so don't know how to multiply.")
    }

    return this.zip(other).fold(emptyList()){acc, pair -> acc + pair.first * pair.second }
}

fun <U> List<List<U>>.countNeighbors(coord: Coord, predicate: (U) -> Boolean): Int {
    val (x, y) = coord
    return (if (y > 0 && x > 0 && predicate(this[y - 1][x - 1])) 1 else 0) +
            (if (y > 0 && predicate(this[y - 1][x])) 1 else 0) +
            (if (y > 0 && x < (this[0].size - 1) && predicate(this[y - 1][x + 1])) 1 else 0) +
            (if (x < (this[0].size - 1) && predicate(this[y][x + 1])) 1 else 0) +
            (if (y < (this.size - 1) && x < (this[0].size - 1) && predicate(this[y + 1][x + 1])) 1 else 0) +
            (if (y < (this.size - 1) && predicate(this[y + 1][x])) 1 else 0) +
            (if (y < (this.size - 1) && x > 0 && predicate(this[y + 1][x - 1])) 1 else 0) +
            (if (x > 0 && predicate(this[y][x - 1])) 1 else 0)
}

fun <U> List<List<U>>.isCorner(coord: Coord): Boolean {
    return (coord.x == 0 || coord.x == this[0].size - 1) && (coord.y == 0 || coord.y == this.size - 1)
}

fun <T> List<T>.findFirstDuplicate(): T? {
    val seenElements = mutableSetOf<T>()

    for (element in this) {
        if (seenElements.contains(element)) {
            return element
        } else {
            seenElements.add(element)
        }
    }

    return null
}

fun <T> List<List<T>>.transpose(): List<List<T>> {
    return if (this.isEmpty()) {
        emptyList()
    } else {
        this[0].indices.map { colIndex ->
            this.map { row -> row[colIndex] }
        }
    }
}

object CollectionUtil {
    /**
     * Returns a list of a list of all the possible partitions of n into i parts.
     *
     * Returning longs, because of the domainspace where that's potentially useful.
     * Do not call with values where it being long actually matters, because there's not enough
     * memory or time in the universe.
     */
    fun generatePartitions(n: Long, i: Long): List<List<Long>> {
        fun generatePartitionsHelper(
            n: Long,
            i: Long,
            currentPartition: MutableList<Long>,
            allPartitions: MutableList<List<Long>>
        ) {
            if (n == 0L && i == 0L) {
                allPartitions.add(currentPartition.toList())
                return
            }

            for (x in 0..n) {
                if (i > 0L) {
                    currentPartition.add(x)
                    generatePartitionsHelper(n - x, i - 1, currentPartition, allPartitions)
                    currentPartition.removeAt(currentPartition.size - 1)
                }
            }
        }

        val allPartitions = mutableListOf<List<Long>>()
        val currentPartition = mutableListOf<Long>()
        generatePartitionsHelper(n, i, currentPartition, allPartitions)
        return allPartitions
    }
}
