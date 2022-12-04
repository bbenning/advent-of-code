package nl.bbenning.advent._2022._02

import java.io.File

object Main {
    fun calcScore(p1: Char, p2: Char): Int {
        val winScore = if(p1 == p2 - 23) {
            3
        } else if (p1 == 'A' && p2 == 'Y' || p1 == 'B' && p2 == 'Z' || p1 == 'C' && p2 == 'X') {
            6
        } else {
            0
        }

        val playScore = if(p2 == 'X') 1 else if (p2 == 'Y') 2 else 3

        return winScore + playScore
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input02.txt").readLines()

        val scores = inputStrings.map { calcScore(it[0], it[2]) }

        println("Answer for 02a: ${scores.sum()}")

        val scores2 = inputStrings.map {
            val elfPlays = it[0]
            val needToWin = it[2]
            val iPlay = if(needToWin == 'X') {
                if(elfPlays == 'A') 'Z' else if (elfPlays == 'B') 'X' else 'Y'
            } else if (needToWin == 'Y') {
                elfPlays + 23
            } else {
                if(elfPlays == 'A') 'Y' else if (elfPlays == 'B') 'Z' else 'X'
            }
            calcScore(elfPlays, iPlay)
        }

        println("Answer for 02b: ${scores2.sum()}")

    }
}