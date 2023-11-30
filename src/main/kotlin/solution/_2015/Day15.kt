package solution._2015


import util.*

class Day15(val input: List<String>) {
    fun solve1(): Long {
        return solve().maxOf { it.first }
    }

    fun solve2():Long {
        return solve().filter { it.second == 500L }.maxOf{it.first}
    }

    private data class Ingredient(val name: String, val capacity: Long, val durability: Long, val flavor: Long, val texture: Long, val calories: Long)

    fun solve(): List<Pair<Long, Long>> {
        val ingredients = input.map {
            val name = it.words().first()
            val (capacity, durability, flavor, texture, calories) = it.longs()
            Ingredient(name, capacity, durability, flavor, texture, calories)
        }

        val allResults = CollectionUtil.generatePartitions(100L, ingredients.size.toLong()).map {
            val capacity = it.multiplyLongs(ingredients.map { it.capacity }).sum().coerceAtLeast(0)
            val durability = it.multiplyLongs(ingredients.map { it.durability }).sum().coerceAtLeast(0)
            val flavor = it.multiplyLongs(ingredients.map { it.flavor }).sum().coerceAtLeast(0)
            val texture = it.multiplyLongs(ingredients.map { it.texture }).sum().coerceAtLeast(0)

            val calories = it.multiplyLongs(ingredients.map { it.calories }).sum().coerceAtLeast(0)

            val result = capacity * durability * flavor * texture
            Pair(result, calories)
        }

        return allResults
    }

}