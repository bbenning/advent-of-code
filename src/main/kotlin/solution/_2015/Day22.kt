package solution._2015

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*

class Day22(val hero: Hero, val boss: Boss) {
    data class Hero(val hp: Int, val mana: Int, val armor: Int = 0)
    data class Boss(val hp: Int, val damage: Int)

    enum class Spell { MISSILE, DRAIN, SHIELD, POISON, RECHARGE }
    data class Effect(val type: Spell, val turns: Int)

    fun solve1(): Int {
        return solve(false)
    }

    fun solve2():Int {
        return solve(true)
    }

    private data class State(val hero: Hero, val boss: Boss, val effects: List<Effect>, val manaSpent: Int, val heroTurn: Boolean)
    
    private fun solve(hardMode: Boolean):Int {

        val initialState = State(hero, boss, emptyList(), 0, true)

        val listOfStates = ArrayDeque(listOf(initialState))
        var lowestWinMana = Integer.MAX_VALUE

        while (listOfStates.isNotEmpty()) {
            val curState = listOfStates.removeFirst()

            if(curState.hero.hp <= 0 || curState.manaSpent >= lowestWinMana) {
                continue
            } else if (curState.boss.hp <= 0) {
                lowestWinMana = curState.manaSpent
                continue
            }

            if(curState.heroTurn) {
                heroTurn(curState, hardMode)
            } else {
                listOf(bossTurn(curState))
            }.forEach {
                listOfStates.add(it)
            }
        }

        return lowestWinMana
    }
    
    private fun heroTurn(state: State, hardMode: Boolean): List<State> {
        val bossAfterPoison = if(state.effects.any{it.type == Spell.POISON}) {
            state.boss.copy(hp = state.boss.hp - 3)
        } else {
            state.boss
        }

        val newHp = state.hero.hp - if(hardMode) 1 else 0
        if(newHp <= 0) {
            return emptyList()
        }
        val heroAfterRecharge = if(state.effects.any{it.type == Spell.RECHARGE}) {
            state.hero.copy(mana = state.hero.mana + 101, hp = newHp)
        } else {
            state.hero.copy(hp = newHp)
        }

        val newEffects = state.effects.map { effect -> effect.copy(turns = effect.turns - 1) }.filter { it.turns > 0 }

        return Spell.entries.filter { it !in newEffects.map { it.type } }.map { spell ->
            when(spell) {
                Spell.MISSILE -> State(heroAfterRecharge.copy(mana = heroAfterRecharge.mana - 53), bossAfterPoison.copy(hp = bossAfterPoison.hp - 4), newEffects, state.manaSpent + 53, false)
                Spell.DRAIN -> State(heroAfterRecharge.copy(hp = heroAfterRecharge.hp + 2, mana = heroAfterRecharge.mana - 73), bossAfterPoison.copy(hp = bossAfterPoison.hp - 2), newEffects, state.manaSpent + 73, false)
                Spell.SHIELD -> State(heroAfterRecharge.copy(mana = heroAfterRecharge.mana - 113), bossAfterPoison, newEffects.plus(Effect(Spell.SHIELD, 6)), state.manaSpent + 113, false)
                Spell.POISON -> State(heroAfterRecharge.copy(mana = heroAfterRecharge.mana - 173), bossAfterPoison, newEffects.plus(Effect(Spell.POISON, 6)), state.manaSpent + 173, false)
                Spell.RECHARGE -> State(heroAfterRecharge.copy(mana = heroAfterRecharge.mana - 229), bossAfterPoison, newEffects.plus(Effect(Spell.RECHARGE, 5)), state.manaSpent + 229, false)
            }
        }.filter { it.hero.mana >= 0 }
    }

    private fun bossTurn(state: State): State {
        val bossAfterPoison = if(state.effects.any{it.type == Spell.POISON}) {
            state.boss.copy(hp = state.boss.hp - 3)
        } else {
            state.boss
        }
        val heroAfterRecharge = if(state.effects.any{it.type == Spell.RECHARGE}) {
            state.hero.copy(mana = state.hero.mana + 101)
        } else {
            state.hero
        }

        val heroArmor = if(state.effects.any {it.type == Spell.SHIELD}) 7 else 0

        val newEffects = state.effects.map { effect -> effect.copy(turns = effect.turns - 1) }.filter { it.turns > 0 }
        val damage = if(bossAfterPoison.hp <= 0) 0 else (state.boss.damage - heroArmor).coerceAtLeast(1)
        val heroNewHp = state.hero.hp - damage

        val newHero = heroAfterRecharge.copy(hp = heroNewHp)

        return State(newHero, bossAfterPoison, newEffects, state.manaSpent, true)
    }
}