package solution._2025

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.longs.product

data class Node(val id: Int, val x: Long, val y: Long, val z: Long)

data class Connection(val nodeA: Node, val nodeB: Node, val distSq: Long)

fun distanceSquared(a: Node, b: Node): Long {
    val dx = a.x - b.x
    val dy = a.y - b.y
    val dz = a.z - b.z
    return (dx * dx) + (dy * dy) + (dz * dz)
}

class Day08(val input: List<String>) {
    val nodes = input.mapIndexed { index, line ->
        val parts = line.split(",")
        Node(index, parts[0].trim().toLong(), parts[1].trim().toLong(), parts[2].trim().toLong())
    }

    private val sortedEdges by lazy{
        val potentialEdges = ArrayList<Connection>()
        for (i in nodes.indices) {
            for (j in i + 1 until nodes.size) {
                val dSq = distanceSquared(nodes[i], nodes[j])
                potentialEdges.add(Connection(nodes[i], nodes[j], dSq))
            }
        }
        potentialEdges.sortBy { it.distSq }
        potentialEdges
    }

    fun solve1(numberOfConnections: Int): Long {
        val ds = DisjointSet(nodes.size)
        var connectionsMade = 0

        for (edge in sortedEdges) {
            if (connectionsMade >= numberOfConnections) break

            ds.union(edge.nodeA.id, edge.nodeB.id)
            connectionsMade++
        }

        val circuitsMap = HashMap<Int, ArrayList<Node>>()
        for (node in nodes) {
            val rootId = ds.find(node.id)
            circuitsMap.computeIfAbsent(rootId) { ArrayList() }.add(node)
        }

        return circuitsMap.values
            .sortedByDescending { it.size }
            .take(3)
            .map { it.size.toLong() }
            .product()
    }

    fun solve2(): Long {
        val ds = DisjointSet(nodes.size)

        for (edge in sortedEdges) {
            if (ds.union(edge.nodeA.id, edge.nodeB.id)) {

                if (ds.sets == 1) {
                    val a = edge.nodeA
                    val b = edge.nodeB

                    return a.x * b.x
                }
            }
        }
        return 0L
    }
}