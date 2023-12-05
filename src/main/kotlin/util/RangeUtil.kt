package util

import java.lang.IllegalStateException
import kotlin.math.max
import kotlin.math.min

object RangeUtil {

    /**
     * Returns a fully unioned list of UIntRanges that is sorted.
     */
    fun unionAllUIntRanges(ranges: Iterable<UIntRange>): List<UIntRange> {
        val sortedRanges = ranges.sortedBy { it.first }
        return sortedRanges.drop(1).fold(listOf(sortedRanges.first())) { acc, range ->
            acc.dropLast(1) + union(acc.last(), range)
        }
    }

    /**`
     * Returns a fully unioned list of UIntRanges that is sorted.
     */
    fun unionAllLongRanges(ranges: Iterable<LongRange>): List<LongRange> {
        val sortedRanges = ranges.filter{it != LongRange.EMPTY}.sortedBy { it.first }
        return sortedRanges.drop(1).fold(listOf(sortedRanges.first())) { acc, range ->
            acc.dropLast(1) + union(acc.last(), range)
        }
    }

    /**
     * Returns a fully unioned list of IntRanges that is sorted.
     */
    fun unionAllIntRanges(ranges: Iterable<IntRange>): List<IntRange> {
        val sortedRanges = ranges.sortedBy { it.first }
        return sortedRanges.drop(1).fold(listOf(sortedRanges.first())) { acc, range ->
            acc.dropLast(1) + union(acc.last(), range)
        }
    }

    fun minusAllLongRanges(range: LongRange, otherRanges: Iterable<LongRange>): List<LongRange> {
        return otherRanges.fold(listOf(range)) {acc:List<LongRange>, otherRange:LongRange ->
            acc.flatMap { RangeUtil.minus(it, otherRange) }
        }
    }


    /**
     * Returns an unioned list of UIntRanges. For instance:
     * union((1U..2U), (3U..4U)) -> List((1U..4U))
     * union((1U..2U), (4U..5U)) -> List((1U..2U), (4U..5U))
     */
    fun union(range1:UIntRange, range2: UIntRange): List<UIntRange> {
        val r1 = if(range1.first < range2.first) range1 else range2
        val r2 = if(range1.first < range2.first) range2 else range1

        return if(r2.first - 1U > r1.last) {
            listOf(r1, r2)
        } else if(r2.last > r1.last) {
            listOf(UIntRange(r1.first, r2.last))
        } else {
            listOf(UIntRange(r1.first, r1.last))
        }
    }
    /**
     * Returns an unioned list of LongRanges. For instance:
     * union((1U..2U), (3U..4U)) -> List((1U..4U))
     * union((1U..2U), (4U..5U)) -> List((1U..2U), (4U..5U))
     */
    fun union(range1:LongRange, range2: LongRange): List<LongRange> {
        val r1 = if(range1.first < range2.first) range1 else range2
        val r2 = if(range1.first < range2.first) range2 else range1

        return if(r2.first - 1L > r1.last) {
            listOf(r1, r2)
        } else if(r2.last > r1.last) {
            listOf(LongRange(r1.first, r2.last))
        } else {
            listOf(LongRange(r1.first, r1.last))
        }
    }

    /**
     * Returns an unioned list of IntRanges. For instance:
     * union((1..2), (3..4)) -> List((1..4))
     * union((1..2), (4..5)) -> List((1..2), (4..5))
     */
    fun union(range1:IntRange, range2: IntRange): List<IntRange> {
        val r1 = if(range1.first < range2.first) range1 else range2
        val r2 = if(range1.first < range2.first) range2 else range1

        return if(r2.first + 1 > r1.last) {
            listOf(r1, r2)
        } else if(r2.last > r1.last) {
            listOf(IntRange(r1.first, r2.last))
        } else {
            listOf(IntRange(r1.first, r1.last))
        }
    }

    fun minus(range1: UIntRange, range2: UIntRange): List<UIntRange> {
        return if(range2.last < range1.first) {
            listOf(range1)
        } else if(range2.last < range1.last && range2.first <= range1.first) {
            listOf((range2.last + 1U .. range1.last))
        } else if(range2.last < range1.last && range2.first > range1.first) {
            listOf((range1.first .. range2.first - 1U), (range2.last + 1U .. range1.last))
        } else if (range2.first <= range1.first && range2.last >= range1.last) {
            listOf()
        } else if (range2.first < range1.last && range2.last >= range1.last) {
            listOf((range1.first..range2.first - 1U))
        }
        else if (range2.first > range1.last) {
            listOf(range1)
        } else {
            throw IllegalStateException("Programmer error ... missing condition for $range1 minus $range2")
        }
    }
    fun minus(range1: LongRange, range2: LongRange): List<LongRange> {
        return if(range2.last < range1.first) {
            listOf(range1)
        } else if(range2.last < range1.last && range2.first <= range1.first) {
            listOf((range2.last + 1L .. range1.last))
        } else if(range2.last < range1.last && range2.first > range1.first) {
            listOf((range1.first .. range2.first - 1L), (range2.last + 1L .. range1.last))
        } else if (range2.first <= range1.first && range2.last >= range1.last) {
            listOf()
        } else if (range2.first < range1.last && range2.last >= range1.last) {
            listOf((range1.first..range2.first - 1L))
        }
        else if (range2.first > range1.last) {
            listOf(range1)
        } else {
            throw IllegalStateException("Programmer error ... missing condition for $range1 minus $range2")
        }
    }

    fun intersection(range1: LongRange, range2: LongRange): LongRange {
        return if(range1.first > range2.last || range1.last < range2.first) {
            LongRange.EMPTY
        } else {
            LongRange(max(range1.first, range2.first), min(range1.last, range2.last))
        }
    }
}