package nl.bbenning.advent.utils

import com.sun.jdi.BooleanValue
import org.apache.commons.lang3.StringUtils


fun String.countTotalSubstrings(substrings:List<String>): Int {
    return substrings.sumOf { StringUtils.countMatches(this, it) }
}

fun String.hasNLettersInARow(n:Int): Boolean {
    return this.windowed(n).any { window -> window.all { it == window[0] } }
}

fun String.doesNotContainSubstrings(substrings: List<String>): Boolean {
    return !substrings.any { this.contains(it) }
}

fun String.decodeHex(): String {
    require(length % 2 == 0) {"Must have an even length"}
    return String(
        chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    )
}

fun String.words(): List<String> = this.split(" ")