package nl.bbenning.advent._2022._11

import nl.bbenning.advent.utils.words
import java.io.File
import java.lang.IllegalArgumentException

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        solve(20, 3U)
        solve(10000, 1U)

        // 57348
        // 14106266886
    }

    data class Monkey(val items: MutableList<ULong>, val operation: (ULong) -> ULong, val testValue: ULong, val throwItem: (Boolean) -> Int, var numberOfInspections: Int = 0)

    fun solve(rounds: Int, divisor: UInt) {
        val inputStrings = File("./src/main/resources/inputs/2022/input11.txt").readLines()

        val monkeys = inputStrings.windowed(6, step = 7).map { lines ->
            val items = lines[1].words().drop(4).map{it.replace(",", "")}.map{it.toULong()}.toMutableList()
            val operationStr = lines[2].substring("  Operation: new = old ".length)
            val testValue = lines[3].substring("  Test: divisible by ".length).toULong()
            val ifTrue = lines[4].substring("    If true: throw to monkey ".length).toInt()
            val ifFalse = lines[5].substring("    If false: throw to monkey ".length).toInt()

            val operator = operationStr[0]
            val arg2 = operationStr.substring(2)

            val operation: (ULong) -> ULong = when(operator) {
                '*' -> if (arg2 == "old") { a -> a * a } else { a -> a * arg2.toULong() }
                '+'-> if(arg2 == "old") { a -> a + a } else { a -> a + arg2.toULong() }
                else -> throw IllegalArgumentException("Unmapped operator.")
            }
            Monkey(items, operation, testValue, {test: Boolean -> if(test) ifTrue else ifFalse})
        }

        val commonDivisor = monkeys.map { it.testValue }.reduce{ acc, bigInteger -> acc * bigInteger }

        (0 until rounds).forEach { _ ->
            monkeys.forEach { monkey ->
                monkey.items.forEach{ itemWorryLevel ->
                    val newLevel = (monkey.operation(itemWorryLevel) / divisor) % commonDivisor
                    monkey.numberOfInspections++
                    val throwItemTo = monkey.throwItem(newLevel % monkey.testValue == 0UL)
                    monkeys[throwItemTo].items.add(newLevel)
                }
                monkey.items.clear()
            }
        }

        val top = monkeys.map { it.numberOfInspections }.sorted().takeLast(2)
        println("Answer: ${top[0].toULong() * top[1].toULong()}")
    }
}