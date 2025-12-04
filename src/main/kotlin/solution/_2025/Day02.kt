package solution._2025

class Day02(val input: String) {
    // can simply use regex and iterate over all numbers, given the inputs. This of course
    // would never work for more challenging inputs.

    fun solve1(): Long {
        val validRegex = "^(\\d+)\\1$".toRegex()

        return solve(validRegex)
    }

    fun solve2(): Long {
        val validRegex = "^(\\d+)\\1+$".toRegex()
        return solve(validRegex)
    }

    fun solve(validRegex: Regex): Long {
        val ranges: List<LongRange> = input.split(",").map {
            val (a, b) = it.split("-").map{ it.toLong() }
            LongRange(a, b)
        }

        var sum = 0L
        ranges.forEach { range ->
            range.forEach { number ->
                if(number.toString().matches(validRegex)) {
                    sum += number
                }
            }
        }

        return sum
    }
}