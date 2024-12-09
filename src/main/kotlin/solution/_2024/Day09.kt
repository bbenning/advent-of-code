package solution._2024

class Day09(val input: String) {
    fun solve1(): Long {
        return solve()
    }

    fun solve2(): Long {
        abstract class DiskSpace
        data class File(val id: Int, val size: Int) : DiskSpace()
        data class FreeSpace(val size: Int): DiskSpace()

        val diskMap = input.map { it.digitToInt() }.foldIndexed(emptyList<DiskSpace>()) { idx, acc, d ->
            if(idx % 2 == 0) {
                acc + File(idx / 2, d)
            } else {
                acc + FreeSpace(d)
            }
        }.toMutableList()

        val numFiles = diskMap.count { it is File }
        var filesRemaining = numFiles
        var idx  = diskMap.size - 1
        while(filesRemaining > 0) {
            val workingOn = diskMap[idx]
            when(workingOn) {
                is File -> {
                    if(workingOn.id >= filesRemaining) {
                        // leave be, already moved
                        idx--
                    } else {
                        // find first free space
                        val firstFreeIdx = diskMap.indexOfFirst { it is FreeSpace && it.size >= workingOn.size }
                        if(firstFreeIdx < 0 || firstFreeIdx >= idx) {
                            // cannot move, so move on
                            idx--
                        } else {
                            val freeSpace = diskMap[firstFreeIdx] as FreeSpace

                            diskMap[firstFreeIdx] = workingOn
                            diskMap[idx] = FreeSpace(workingOn.size)
                            val leftOverSpace = freeSpace.size - workingOn.size
                            if(leftOverSpace > 0) {
                                diskMap.add(firstFreeIdx + 1, FreeSpace(leftOverSpace))
                            } else {
                                idx--
                            }
                        }
                        filesRemaining--
                    }
                }
                is FreeSpace -> {
                    idx --
                }
            }

        }

        val checkSum = diskMap.foldIndexed(Pair(0L, 0)){idx, acc, diskSpace ->
            when(diskSpace) {
                is File -> {
                    Pair(acc.first + (0 until diskSpace.size).sumOf{ diskSpace.id.toLong() * (it + acc.second) }, acc.second + diskSpace.size)
                }
                is FreeSpace -> Pair(acc.first, acc.second + diskSpace.size)
                else -> throw IllegalStateException("Cannot happen")
            }
        }.first
        return checkSum
    }

    private fun solve(): Long {
        val (files, freeSpaces) = input.map { it.digitToInt() }.foldIndexed(Pair(emptyList<Int>(), emptyList<Int>())) { idx, acc, d ->
            if(idx % 2 == 0) {
                Pair(acc.first + d, acc.second)
            } else {
                Pair(acc.first, acc.second + d)
            }
        }.let { Pair(it.first.toMutableList(), it.second.toMutableList()) }

        var fileId = 0
        var freeSpaceId = 0
        var position = 0L

        val listOfFileIds = mutableListOf<Int>()

        while(fileId < files.size) {
            if(fileId == freeSpaceId) {
                val fileLength = files[fileId]
                repeat(fileLength) {
                    listOfFileIds.add(fileId)
                    position++
                }
                fileId++
            } else {
                val freeSpaceLength = freeSpaces[freeSpaceId]
                repeat(freeSpaceLength) {
                    val lastFileId = files.size - 1
                    if(lastFileId>fileId) {
                        listOfFileIds.add(lastFileId)
                        position++

                        files[lastFileId]--
                        if(files[lastFileId] == 0) {
                            files.removeLast()
                        }
                    }
                }
                freeSpaceId++
            }
        }

        val checksum = listOfFileIds.foldIndexed(0L){idx, acc, fileId -> acc + idx * fileId}
        return checksum
    }
}

