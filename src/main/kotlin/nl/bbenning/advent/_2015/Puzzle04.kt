package nl.bbenning.advent._2015

import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

object Puzzle04 {

    fun md5(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))
    fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = "ckczppom"

        var count = 0
        while (true) {
            val md5 = md5(input + count)
            if(md5.toHex().startsWith("00000")) {
                break
            } else {
                count++
            }
        }
        println("For A:")
        println(count)
        println(md5(input + count).toHex())

        count = 0
        while (true) {
            val md5 = md5(input + count)
            if(md5.toHex().startsWith("000000")) {
                break
            } else {
                count++
            }
        }

        println("For B:")
        println(count)
        println(md5(input + count).toHex())
    }
}