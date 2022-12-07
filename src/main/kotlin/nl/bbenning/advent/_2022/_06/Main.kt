package nl.bbenning.advent._2022._06

import java.io.File

object Main {
    private fun uniqueSequenceEndPos(str: String, length: Int): Int {
        tailrec fun uniqueSequenceEndPos(acc: List<Char>, startPos: Int): Int {
            if(acc.size == length) {
                return startPos + length
            }

            val curLetter = str[startPos + acc.size]
            val curLetterPos = acc.indexOf(curLetter)
            return if(curLetterPos >= 0) {
                uniqueSequenceEndPos(emptyList(), startPos + curLetterPos + 1)
            } else {
                uniqueSequenceEndPos(acc.plus(curLetter), startPos)
            }
        }

        return uniqueSequenceEndPos(emptyList(), 0)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input06.txt").readLines()

        inputStrings.forEach {
            println("Answer to puzzle 06a: ${uniqueSequenceEndPos(it, 4)}")
            println("Answer to puzzle 06b: ${uniqueSequenceEndPos(it, 14)}")
        }
    }
}