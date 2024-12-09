package solution._2024

class Day09(val input: String) {
    private data class Block(val id: Long, val used: Int, val free: Int) {
        fun checkSum(startIdx: Int): Long = (startIdx until startIdx + used).sumOf { it * id }

    }

    private val initialDiskMap = input.map { it.digitToInt() }.let{if(it.size % 2 == 0) it else it + 0}.chunked(2)
        .mapIndexed{ idx, (a, b) -> Block(idx.toLong(), a, b)}

    fun solve1(): Long {
        val disk = initialDiskMap.toMutableList()

        var idx = 0
        while(idx < disk.size - 1) {
            val block = disk[idx]
            if(block.free == 0) {
                idx++
            } else {
                val lastBlock = disk.removeLast()
                val moveSize = minOf(lastBlock.used, block.free)
                disk.add(idx + 1, Block(lastBlock.id, moveSize, block.free - moveSize))
                disk[idx] = block.copy(free = 0)
                if(lastBlock.used > moveSize) {
                    disk.add(lastBlock.copy(used = lastBlock.used - moveSize))
                }
            }
        }

        val checksum = disk.fold(Pair(0L, 0)) { acc, block ->
            Pair(acc.first + block.checkSum(acc.second), acc.second + block.used + block.free)
        }.first
        return checksum
    }

    fun solve2(): Long {
        val disk = initialDiskMap.toMutableList()

        var idx = disk.size - 1
        var filesRemaining = disk.size
        while(idx > 0) {

            val curBlock = disk[idx]
            if(curBlock.id > filesRemaining) {
                // already moved, so let's move on
                idx--
                continue
            }

            val firstAvailableBlockIdx = disk.indexOfFirst { it.free >= curBlock.used }
            if(firstAvailableBlockIdx < 0 || firstAvailableBlockIdx >= idx) {
                // no suitable free space found, or further away, so let's move on
                idx--
                filesRemaining--
                continue
            }

            val firstAvailableBlock = disk[firstAvailableBlockIdx]
            disk[firstAvailableBlockIdx] = firstAvailableBlock.copy(free = 0)
            disk[idx] = Block(-1, 0, curBlock.used + curBlock.free)
            disk.add(firstAvailableBlockIdx + 1, curBlock.copy(free = firstAvailableBlock.free - curBlock.used))

        }

        val checksum = disk.fold(Pair(0L, 0)) { acc, block ->
            Pair(acc.first + block.checkSum(acc.second), acc.second + block.used + block.free)
        }.first
        return checksum
    }
}


