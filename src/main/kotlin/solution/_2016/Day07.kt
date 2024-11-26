package solution._2016

class Day07(val input: List<String>) {
    fun solve1(): Long {
        return solveABBA()
    }

    fun solve2(): Long {
        return solveSSL()
    }

    private fun solveABBA(): Long {
        val abbaRegex = ".*([a-z])(?!\\1)([a-z])\\2\\1.*".toRegex()
        val abbaInBracketRegex = ".*\\[[a-z]*([a-z])(?!\\1)([a-z])\\2\\1[a-z]*].*".toRegex()

        val supportsABBA = input.filter { str ->
            val abbas = abbaRegex.matches(str)
            val abbasInBrackets = abbaInBracketRegex.matches(str)
            abbas && !abbasInBrackets
        }

        return supportsABBA.size.toLong()
    }

    private fun solveSSL(): Long {
        val validSSL = input.filter { str ->
            val strs = str.split("[]\\[]".toRegex())
            val supernetABAs = mutableSetOf<String>()
            val hypernetBABs = mutableSetOf<String>()

            strs.forEachIndexed { index, s ->
                val abas = s.windowed(3).filter {
                    it[0] != it[1] && it[0] == it[2]
                }

                if(index % 2 == 0) {
                    supernetABAs.addAll(abas)
                } else {
                    hypernetBABs.addAll(abas)
                }
            }

            supernetABAs.any {
                val reversed = "${it[1]}${it[0]}${it[1]}"
                hypernetBABs.contains(reversed)
            }
        }


        return validSSL.size.toLong()
    }
}