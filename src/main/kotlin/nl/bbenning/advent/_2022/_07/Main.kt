package nl.bbenning.advent._2022._07

import java.io.File

object Main {

    private enum class FileNodeType {
        FILE,
        DIRECTORY
    }

    private class FileNode(val name: String, val children: MutableMap<String, FileNode>?, val parent: FileNode?, val type: FileNodeType, val size: Long?)

    private fun traverseAndReturnDirectorieNamesPlusSizes(rootNode: FileNode): Map<String, Long> {
        val sizeMap = mutableMapOf<String, Long>()

        fun directorySize(curDirectoryNode: FileNode): Long {
            val size: Long = curDirectoryNode.children!!.map {
                if(it.value.type == FileNodeType.FILE) {
                    it.value.size!!
                } else {
                    directorySize(it.value)
                }
            }.sum()

            sizeMap[curDirectoryNode.name] = size
            return size
        }

        directorySize(rootNode)
        return sizeMap
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val inputStrings = File("./src/main/resources/inputs/2022/input07.txt").readLines()
        val root = FileNode("/", mutableMapOf(), null, FileNodeType.DIRECTORY, null)

        inputStrings.fold(root) {curDirectory, commandLine ->
            if(commandLine == "$ cd /") {
                root
            } else if(commandLine.startsWith("$ cd ..")) {
                curDirectory.parent!!
            } else if(commandLine.startsWith("$ cd ")) {
                val directory = commandLine.substring("$ cd ".length)
                curDirectory.children!![directory]!! // want it to fail in case it's wrong
            } else if(commandLine ==  "$ ls") {
                curDirectory
            } else if (commandLine.startsWith("dir ")){
                val name = commandLine.substring("dir ".length)
                val newFileNode = FileNode(name, mutableMapOf(), curDirectory, FileNodeType.DIRECTORY, null)
                curDirectory.children!![name] = newFileNode // want it to fail in case it's wrong
                curDirectory
            } else {
                val splitCmdLine = commandLine.split(" ")
                val size = splitCmdLine[0].toLong()
                val name = splitCmdLine[1]
                val newFileNode = FileNode(name, null, curDirectory, FileNodeType.FILE, size)
                curDirectory.children!![name] = newFileNode // want it to fail in case it's wrong
                curDirectory
            }
        }

        val sizeMap = traverseAndReturnDirectorieNamesPlusSizes(root)

        println(sizeMap)

        println("Answer to 07a: ${sizeMap.filterValues { it <= 100000 }.values.sum()}")
    }
}