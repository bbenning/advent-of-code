package solution._2025

class Day03(val input: List<String>) {
    fun solve1(): Long {
        val joltagePerBank = input.map { bank ->
            solve(bank, 2).toLong()
        }

        return joltagePerBank.sum()
    }

    fun solve2(): Long {
        val joltagePerBank = input.map { bank ->
            solve(bank, 12).toLong()
        }

        return joltagePerBank.sum()    }

    private fun solve(str: String, numsRemaining: Int): String {
        if(numsRemaining == 1) {
            return str.max().toString()
        }

        // given that the left number always trumps the right number, you want the highest possible joltage
        // from the left except for the last n batteries.

        var idxLeft = 0
        var highestLeft = '0'

        for(i in 0..< str.length - numsRemaining + 1) {
            val joltage = str[i]
            if(joltage > highestLeft) {
                idxLeft = i
                highestLeft = joltage
            }

            if(highestLeft == '9') {
                break
            }
        }

        return "" + highestLeft + solve(str.substring(idxLeft + 1), numsRemaining - 1)
    }

    private fun solve(): Long {
        return 0
    }
}