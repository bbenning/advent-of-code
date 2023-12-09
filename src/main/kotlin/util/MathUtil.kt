package util

import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.sqrt

fun gcd(a: Int, b: Int, vararg numbers: Int): Int = when {
    numbers.isEmpty() -> if (b == 0) a else gcd(b, a % b)
    else -> gcd(gcd(a, b), numbers.first(), *numbers.drop(1).toIntArray())
}

fun gcd(a: Long, b: Long, vararg numbers: Long): Long = when {
    numbers.isEmpty() -> if (b == 0L) a else gcd(b, a % b)
    else -> gcd(gcd(a, b), numbers.first(), *numbers.drop(1).toLongArray())
}

/**
 * returns a Triple (gcd, m, n), such that: gcd(a, b) = a * m + b * n
 */
fun gcdExtended(a: Long, b: Long): Triple<Long, Long, Long> {
    return if (b == 0L) {
        Triple(a, 1L, 0L)
    } else {
        val (gcd, m, n) = gcdExtended(b, a % b)
        val nNew = m - (a / b) * n
        Triple(gcd, n, nNew)
    }
}

fun Int.divisors(): Set<Int> {
    return (1 .. sqrt(this.toDouble()).toInt()).filter { i ->
        this % i == 0
    }.flatMap {
        listOf(it, this/it)
    }.toSet()
}

fun lcm(a: Int, b: Int, vararg numbers: Int): Int = when {
    numbers.isEmpty() -> abs(a * b) / gcd(a, b)
    else -> lcm(lcm(a, b), numbers.first(), *numbers.drop(1).toIntArray())
}
fun lcm(a: Long, b: Long, vararg numbers: Long): Long = when {
    numbers.isEmpty() -> abs(a * b) / gcd(a, b)
    else -> lcm(lcm(a, b), numbers.first(), *numbers.drop(1).toLongArray())
}

/**
 * Used for 2023.8.2 to calculate the general case of repeating loops with an offset.
 * So suppose there are 2 people running:
 * - person 1: starts at 2 meters, with speed 3
 * - person 2: starts at 3 meters, with speed 5
 * - person 3: starts at 5 meters, with speed 7
 * when will they all align first?
 * This return the combined period and offset, so the first point at which they align
 * is the sum of returned Pair.
 */
fun alignPeriodsAndPhases(periodLengthsWithPhases: List<Pair<Long, Int>>): Pair<Long, Int> {
    return periodLengthsWithPhases.reduce{ acc, next ->
        val (gcd, m, _) = gcdExtended(acc.first, next.first)
        val phaseShiftDividedByGcd = (acc.second - next.second) / gcd.toDouble()
        if(phaseShiftDividedByGcd != floor(phaseShiftDividedByGcd)) {
            // it not equally divisible, can't ever align.
            throw IllegalStateException("Won't ever align.")
        }

        val combinedPeriod = acc.first / gcd * next.first
        val combinedPhase = ((acc.second - m * phaseShiftDividedByGcd * acc.first) % combinedPeriod).toInt()
        Pair(combinedPeriod, combinedPhase)
    }
}