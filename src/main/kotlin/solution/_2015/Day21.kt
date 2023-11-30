package solution._2015

import com.github.shiguruikai.combinatoricskt.combinations
import util.*

class Day21(val hero: Character, val boss: Character) {

    val allWeapons = """
        Weapons:    Cost  Damage  Armor
        Dagger        8     4       0
        Shortsword   10     5       0
        Warhammer    25     6       0
        Longsword    40     7       0
        Greataxe     74     8       0
    """.trimIndent().lines().drop(1).map { it.ints() }

    val allArmors = """
        Armor:      Cost  Damage  Armor
        Leather      13     0       1
        Chainmail    31     0       2
        Splintmail   53     0       3
        Bandedmail   75     0       4
        Platemail   102     0       5
        Dummy         0     0       0
    """.trimIndent().lines().drop(1).map { it.ints() }

    val allRings = """
        Rings:      Cost  Damage  Armor
        Damage +1    25     1       0
        Damage +2    50     2       0
        Damage +3   100     3       0
        Defense +1   20     0       1
        Defense +2   40     0       2
        Defense +3   80     0       3
        Dummy    1    0     0       0
        Dummy    2    0     0       0
    """.trimIndent().lines().drop(1).map { it.ints().drop(1) }

    fun solve1(): Int {
        return solve(hero, boss).filter { it.second }.minOf { it.first }
    }

    fun solve2():Int {
        return solve(hero, boss).filter { !it.second }.maxOf { it.first }
    }

    data class Character(val hitPoints: Int, val damage: Int, val armor: Int)

    private fun solve(hero: Character, boss: Character): List<Pair<Int, Boolean>> {
        val ringsCombination = allRings.combinations(2).toList()

        val outfitCostsWithWinOrNot = allWeapons.flatMap { weapon ->
            allArmors.flatMap { armor ->
                ringsCombination.map { rings ->
                    val speccedHero = hero.copy(damage = hero.damage + weapon[1] + rings.sumOf { it[1] }, armor = hero.armor + armor[2] + rings.sumOf { it[2] })
                    Pair(weapon[0] + armor[0] + rings.sumOf { it[0] }, heroWins(speccedHero, boss))
                }
            }
        }

        return outfitCostsWithWinOrNot
    }

    fun heroWins(hero: Character, boss: Character): Boolean {
        fun heroWins(hero: Character, boss: Character, heroTurn: Boolean): Boolean {
            if(hero.hitPoints <= 0) return false
            if(boss.hitPoints <= 0) return true
            val hitter = if(heroTurn) hero else boss
            val casualty = if (heroTurn) boss else hero

            val damage = (hitter.damage - casualty.armor).coerceAtLeast(1)
            val newHp = casualty.hitPoints - damage

            val newHero = if(heroTurn) hero else hero.copy(hitPoints = newHp)
            val newBoss = if(!heroTurn) boss else boss.copy(hitPoints = newHp)
            return heroWins(newHero, newBoss, !heroTurn)
        }

        return heroWins(hero, boss, true)
    }
}