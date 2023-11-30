package solution._2015

class Day01(val input: List<String>) {
    fun solve1(): Int {
        return solve().last()
    }

    fun solve2():Int {
        return solve().indexOf(-1)
    }

    private fun solve(): List<Int> {
        return input.first().fold(listOf(0)){acc, c -> if(c == '(') acc + (acc.last() + 1) else acc + (acc.last() - 1) }
    }
}