package solution._2023

import util.*

class Day15(val input: String) {
    fun solve1(): Int {
        return input.split(",").sumOf { hash(it) }
    }

    fun solve2():Int {
        val instructions = input.split(",")
        val boxes = List<MutableList<Pair<String, Int>>>(256){ mutableListOf() }

        for(instr in instructions) {
            val focalLength = instr.words().first()
            val hash = hash(focalLength)
            val idx = boxes[hash].indexOfFirst { it.first == focalLength }

            if("=" in instr) {
                val setting = instr.ints().first()

                if(idx < 0) {
                    boxes[hash].add(Pair(focalLength, setting))
                } else {
                    boxes[hash][idx] = Pair(focalLength, setting)
                }
            } else if(idx >= 0) {
                boxes[hash].removeAt(idx)
            }
        }
        return boxes.mapIndexed { boxNr, lenses ->
            lenses.mapIndexed { lensNr, (_, focalLength) ->
                (1 + boxNr) * (1 + lensNr) * focalLength
            }.sum()
        }.sum()
    }

    private fun hash(str: String): Int {
        return str.fold(0) { acc, c ->
            ((acc + c.code) * 17) % 256
        }
    }
}