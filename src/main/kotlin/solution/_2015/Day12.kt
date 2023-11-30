package solution._2015

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.mapping.Coord
import util.mapping.Direction

class Day12(val input: String) {

    fun solve1(): Int {
        return input.ints().sum()
    }

    fun solve2():Int {
        val decodedJson = input.asJson()
        fun determineValue(jsonElement: JsonElement): Int {
            return when(jsonElement) {
                is JsonArray -> jsonElement.sumOf{determineValue(it)}
                is JsonObject -> if(jsonElement.containsValue(JsonPrimitive("red"))) 0 else jsonElement.values.sumOf{determineValue(it)}
                is JsonPrimitive -> if(jsonElement.isString) 0 else jsonElement.content.toInt()
            }
        }

        return determineValue(decodedJson)
    }
}