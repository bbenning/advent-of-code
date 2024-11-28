package solution._2016

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.longs.product
import util.mapping.Coord
import util.mapping.Direction

class Day10(val input: List<String>) {
    fun solve1(): Long {
        return solve()
    }

    fun solve2(): Long {
        return solve()
    }


    data class Bot(var low: Int?, var high: Int?, val lowToBot: Int?, val highToBot: Int?, val lowToOutput: Int?, val highToOutput: Int?, val number: Int) {
        fun takeChip(value: Int) {
            if(low == null) {
                low = value
            } else if (value < low!!) {
                high = low
                low = value
            } else {
                high = value
            }
        }
    }

    private fun solve(): Long {

        val bots = mutableMapOf<Int, Bot>()
        val outputs = mutableMapOf<Int, Int>()

        // can first inialize bots with instructions
        input.filter { it.startsWith("bot") }.forEach {
            val (botNumber, lowTo, highTo) = it.ints()
            val lowIsOutput = it.contains("low to output")
            val highIsOutput = it.contains("high to output")
            bots[botNumber] = Bot(null, null,
                if(!lowIsOutput) lowTo else null,
                if(!highIsOutput) highTo else null,
                if(lowIsOutput) lowTo else null,
                if(highIsOutput) highTo else null,
                botNumber)
        }

        fun moveFromBot(bot: Bot) {
            if(bot.low != null && bot.high != null) {
                println(bot)
                if(bot.lowToOutput != null) {
                    outputs[bot.lowToOutput] = bot.low!!
                } else {
                    val toLowBot = bots[bot.lowToBot]!!
                    toLowBot.takeChip(bot.low!!)
                }
                if(bot.highToOutput != null) {
                    outputs[bot.highToOutput] = bot.high!!
                } else {
                    val toHighBot = bots[bot.highToBot]!!
                    toHighBot.takeChip(bot.high!!)
                }

                bot.low =null
                bot.high = null
                if(bot.lowToBot != null)  {
                    val toLowBot = bots[bot.lowToBot]!!
                    moveFromBot(toLowBot)
                }
                if(bot.highToBot != null)  {
                    val toHighBot = bots[bot.highToBot]!!
                    moveFromBot(toHighBot)
                }
            }
        }

        input.filter { it.startsWith("value") }.forEach {
            val (value, botNumber) = it.ints()

            val bot = bots[botNumber]!!
            bot.takeChip(value)
            moveFromBot(bot)
        }

        val product = outputs[0]!!.toLong() * outputs[1]!! * outputs[2]!!
        return product
    }
}