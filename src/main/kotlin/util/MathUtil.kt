package util

import kotlin.math.abs
import kotlin.math.sqrt

fun gcd(a: Int, b: Int, vararg numbers: Int): Int = when {
    numbers.isEmpty() -> if (b == 0) a else gcd(b, a % b)
    else -> gcd(gcd(a, b), numbers.first(), *numbers.drop(1).toIntArray())
}

fun lcm(a: Int, b: Int, vararg numbers: Int): Int = when {
    numbers.isEmpty() -> abs(a * b) / gcd(a, b)
    else -> lcm(lcm(a, b), numbers.first(), *numbers.drop(1).toIntArray())
}

fun gcd(a: Long, b: Long, vararg numbers: Long): Long = when {
    numbers.isEmpty() -> if (b == 0L) a else gcd(b, a % b)
    else -> gcd(gcd(a, b), numbers.first(), *numbers.drop(1).toLongArray())
}

fun lcm(a: Long, b: Long, vararg numbers: Long): Long = when {
    numbers.isEmpty() -> abs(a * b) / gcd(a, b)
    else -> lcm(lcm(a, b), numbers.first(), *numbers.drop(1).toLongArray())
}

fun Int.divisors(): Set<Int> {
    return (1 .. sqrt(this.toDouble()).toInt()).filter { i ->
        this % i == 0
    }.flatMap {
        listOf(it, this/it)
    }.toSet()
}