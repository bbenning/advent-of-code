package nl.bbenning.advent._2015

import nl.bbenning.advent.utils.countTotalSubstrings
import nl.bbenning.advent.utils.decodeHex
import org.apache.commons.lang3.StringUtils
import java.io.File
import java.util.regex.Pattern

object Puzzle08 {
    data class ParsingObject(val str: String, val prevSlash: Boolean, val hex: Boolean)

    private fun isHexChar(c: Char): Boolean {
        return (c in '0'..'9') || (c in 'a'..'f') || (c in 'A'..'F')
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2015/input08.txt").readLines()

        val counts = inputStrings.map { encodedStr ->

            val decodedStr = encodedStr.foldIndexed(Pair("", "")) {idx, (curStr, cache), c ->
                if(cache + c == "\\\\") {
                    Pair(curStr + "\\", "")
                } else if (cache + c == "\\\"") {
                    Pair(curStr + "\"", "")
                } else if (cache.length == 3) {
                    if(isHexChar(c)) {
                        val newChar = ("" + cache[2] + c).decodeHex()
                        Pair(curStr + newChar, "")
                    } else {
                        Pair(curStr + cache + c, "")
                    }
                }
// case is unnecessary because last char is always "
//                else if (idx == encodedStr.length - 1) {
//                    Pair(curStr + cache + c, "")
//                }
                else if (cache == "\\x" && isHexChar(c)) {
                    Pair(curStr, cache + c)
                } else if (cache == "\\" && (c == 'x' || c == '"'|| c == '\\')) {
                    Pair(curStr, cache + c)
                } else if (cache == "" && c == '\\') {
                    Pair(curStr, cache + c)
                } else if ((idx == 0 || idx == encodedStr.length - 1) && c == '"' ) {
                    Pair(curStr, cache)
                } else {
                    Pair(curStr + cache + c, "")
                }
            }.first

            val reEncodedStr = "\"" + encodedStr.replace("\\", "\\\\").replace("\"", "\\\"") + "\""

            Triple(encodedStr.length, decodedStr.length, reEncodedStr.length)
        }



        val result = counts.map { it.first - it.second }.sum()
        println("Answer for 8a: $result")

        val result2 = counts.map { it.third - it.first }.sum()
        println("Answer for 8b: $result2")
    }
}