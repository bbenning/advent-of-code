package nl.bbenning.advent._2022._07

import java.io.File

object Main {

    private enum class FileNodeType {
        FILE,
        DIRECTORY
    }

    private class FileNode(val name: String, val children: MutableMap<String, FileNode>?, val parent: FileNode?, val type: FileNodeType, val size: Long?)

    private fun traverseAndReturnDirectorieNamesPlusSizes(rootNode: FileNode): Map<FileNode, Long> {
        val sizeMap = mutableMapOf<FileNode, Long>()

        fun directorySize(curDirectoryNode: FileNode): Long {
            val size: Long = curDirectoryNode.children!!.map {
                if(it.value.type == FileNodeType.FILE) {
                    it.value.size!!
                } else {
                    directorySize(it.value)
                }
            }.sum()

            sizeMap[curDirectoryNode] = size
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
                if(!curDirectory.children!!.contains(name)) {
                    val newFileNode = FileNode(name, mutableMapOf(), curDirectory, FileNodeType.DIRECTORY, null)
                    curDirectory.children[name] = newFileNode // want it to fail in case it's wrong
                }
                curDirectory
            } else {
                val splitCmdLine = commandLine.split(" ")
                val name = splitCmdLine[1]
                if(!curDirectory.children!!.contains(name)) {
                    val size = splitCmdLine[0].toLong()
                    val newFileNode = FileNode(name, null, curDirectory, FileNodeType.FILE, size)
                    curDirectory.children[name] = newFileNode // want it to fail in case it's wrong
                }
                curDirectory
            }
        }

        val sizeMap = traverseAndReturnDirectorieNamesPlusSizes(root)

        println("Answer to 07a: ${sizeMap.filterValues { it <= 100000 }.values.sum()}")
        val unusedSpace = 70000000 - sizeMap[root]!!
        val needMoreSpace = 30000000 - unusedSpace
        println("Answer to 07b: ${sizeMap.filterValues { it > needMoreSpace }.values.sorted().take(1)}")

    }
}