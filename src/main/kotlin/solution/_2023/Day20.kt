package solution._2023

import util.*

class Day20(val input: List<String>) {
    fun solve1(): Long {

        val startPulse = listOf(Pulse("button", "broadcaster", PulseStrength.LOW))
        val startState = State(flipflops, conjunctions, startPulse, 0L, 0L, 0)

        val endState = (1L..1000).fold(startState) {acc, buttonPressCount ->

            // press button
            var state = acc.copy(pulsesToProcess = startPulse, buttonPresses = buttonPressCount)
            while(state.pulsesToProcess.isNotEmpty()) {
                state = runCircuit(state)
            }

            state
        }

        return endState.lowPulseCount * endState.highPulseCount
    }

    fun solve2(): Long {
        val startPulse = listOf(Pulse("button", "broadcaster", PulseStrength.LOW))
        val startState = State(flipflops, conjunctions, emptyList(), 0L, 0L, 0)

        // analysis shows that qz, cq, jx and tt need to receive a low pulse

        var qzPeriodicity:Long? = null
        var cqPeriodicity:Long? = null
        var jxPeriodicity:Long? = null
        var ttPeriodicity:Long? = null


        var state = startState
        while(qzPeriodicity == null || cqPeriodicity == null || jxPeriodicity == null || ttPeriodicity == null) {

            if(state.pulsesToProcess.isEmpty()) {
                state = state.copy(pulsesToProcess = startPulse, buttonPresses = state.buttonPresses + 1)
            }

            while(state.pulsesToProcess.isNotEmpty()){
                val nextPulse = state.pulsesToProcess.first()

                if(nextPulse.destination == "qz" && nextPulse.strength == PulseStrength.LOW && qzPeriodicity == null) {
                    qzPeriodicity = state.buttonPresses
                }
                if(nextPulse.destination == "cq" && nextPulse.strength == PulseStrength.LOW && cqPeriodicity == null) {
                    cqPeriodicity = state.buttonPresses
                }
                if(nextPulse.destination == "jx" && nextPulse.strength == PulseStrength.LOW && jxPeriodicity == null) {
                    jxPeriodicity = state.buttonPresses
                }
                if(nextPulse.destination == "tt" && nextPulse.strength == PulseStrength.LOW && ttPeriodicity == null) {
                    ttPeriodicity = state.buttonPresses
                }

                state = runCircuit(state)
            }
        }

        return lcm(qzPeriodicity, cqPeriodicity, jxPeriodicity, ttPeriodicity)
    }

    data class BroadCaster(val destinations: List<String>)
    data class FlipFlop(val name: String, val destinations: List<String>, val on: Boolean = false)
    data class Conjunction(val name: String, val destinations: List<String>, val rememberedInputStates: Map<String, PulseStrength>)

    private val broadcaster = input.filter { it.startsWith("broadcaster") }.map { BroadCaster(it.words().drop(1)) }.first()

    private val flipflops = input.filter {it.startsWith("%")}.map {
        it.words().let { Pair(it[0], FlipFlop(it[0], it.drop(1))) }
    }.toMap()

    private val conjunctions  = input.filter {it.startsWith("&")}.map {
        it.words().let {
            val (name, destination) = it
            val inputs = input.filter { it.endsWith(name) || (it.startsWith("broadcaster") && it.contains(name)) }.map { it.words().first() }
            Pair(it[0], Conjunction(it[0], it.drop(1), inputs.map { Pair(it, PulseStrength.LOW) }.toMap()))
        }
    }.toMap()

    enum class PulseStrength { HIGH, LOW }
    data class Pulse(val source: String, val destination: String, val strength: PulseStrength)

    data class State(val flipflops: Map<String, FlipFlop>, val conjunctions: Map<String, Conjunction>, val pulsesToProcess: List<Pulse>, val lowPulseCount: Long, val highPulseCount: Long, val buttonPresses: Long)

    private fun runCircuit(state: State): State {

        val stateFlipFlops = state.flipflops.toMutableMap()
        val stateConjunctions = state.conjunctions.toMutableMap()

        if(state.pulsesToProcess.isNotEmpty()) {
            val pulse = state.pulsesToProcess.first()

            val newPulses = if(pulse.destination == "broadcaster") {
                broadcaster.destinations.map { destination -> Pulse(pulse.destination, destination, pulse.strength) }
            } else if(stateFlipFlops.contains(pulse.destination)) {
                if(pulse.strength == PulseStrength.HIGH) {
                    emptyList()
                } else {
                    val flipFlop = stateFlipFlops[pulse.destination]!!
                    val newOn = !flipFlop.on
                    val strengthOut = if(newOn) PulseStrength.HIGH else PulseStrength.LOW

                    val updatedFlipFlop = flipFlop.copy(on = newOn)
                    stateFlipFlops[flipFlop.name] = updatedFlipFlop

                    updatedFlipFlop.destinations.map { destination ->
                        Pulse(updatedFlipFlop.name, destination, strengthOut)
                    }
                }
            } else if(stateConjunctions.contains(pulse.destination)) {
                val conjunction = stateConjunctions[pulse.destination]!!

                val newRememberedInputs = conjunction.rememberedInputStates + Pair(pulse.source, pulse.strength)
                val updatedConjunction = conjunction.copy(rememberedInputStates = newRememberedInputs)

                stateConjunctions[pulse.destination] = updatedConjunction

                val strengthOut = if(newRememberedInputs.values.all{it == PulseStrength.HIGH}) PulseStrength.LOW else PulseStrength.HIGH

                updatedConjunction.destinations.map { destination ->
                    Pulse(updatedConjunction.name, destination, strengthOut)
                }
            } else {
                emptyList()
            }

            val lowPulseCount = state.lowPulseCount + if(pulse.strength == PulseStrength.LOW) 1 else 0
            val highPulseCount = state.highPulseCount + if(pulse.strength == PulseStrength.HIGH) 1 else 0

            return State(stateFlipFlops, stateConjunctions, state.pulsesToProcess.drop(1) + newPulses, lowPulseCount, highPulseCount, state.buttonPresses)
        } else {
            return state
        }
    }

}