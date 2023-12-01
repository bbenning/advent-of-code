package util

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.apache.commons.codec.digest.DigestUtils

/**
 * Returns a list of all ints (including minusses)
 */
fun String.ints(): List<Int> {
    val regex = Regex("-?\\d+")
    val matches = regex.findAll(this)
    return matches.map { it.value.toInt() }.toList()
}

fun String.longs(): List<Long> {
    val regex = Regex("-?\\d+")
    val matches = regex.findAll(this)
    return matches.map { it.value.toLong() }.toList()
}

fun String.numbers(): List<String> {
    return "(\\d+)".toRegex().findAll(this).map{it.value}.toList()
}

fun String.alphanumerics(): List<String> {
    return "(\\w+)".toRegex().findAll(this).map{it.value}.toList()
}

fun String.words(): List<String> {
    return "([A-Za-z]+)".toRegex().findAll(this).map{it.value}.toList()
}

fun String.md5(): String {
    return DigestUtils.md5Hex(this)
}

fun String.asJson(): JsonElement = Json.decodeFromString<JsonElement>(this)

fun String.isNumeric() = this.matches("^[-+]?\\d+$".toRegex())

fun String.indices(subString: String): List<Int> {
    val pattern = subString.toRegex()
    return pattern.findAll(this).map{it.range.first}.toList()
}