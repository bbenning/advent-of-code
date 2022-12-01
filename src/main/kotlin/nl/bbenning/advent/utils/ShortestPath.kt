package nl.bbenning.advent.utils

object ShortestPath {

    data class Node(val name: String) {
        val vertices = mutableListOf<Vertex>()
    }

    data class Vertex(val to: Node, val distance: Int)

    class NavigationMap {
        companion object {
            private val INFINITE = Integer.MAX_VALUE
        }

        val nodes = mutableListOf<Node>()
        val d = mutableMapOf<Node, Int>()
        val pi = mutableMapOf<Node, Node>()

    }

    fun dijkstra(source: Int, edges: Array<IntArray>, nodes: Int) {
        // Initialize single source
        val d = IntArray(nodes) { Integer.MAX_VALUE }
        val pi = IntArray(nodes) { -1 }
        d[source] = 0

        val S: MutableList<Int> = ArrayList()
        val Q: MutableList<Int> = (0 until nodes).toMutableList()

        // Iterations
        while (Q.isNotEmpty()) {
            val u: Int = extractMin(Q, d)
            S.add(u)

            edges[u].forEachIndexed { v, vd ->
                if (vd != -1 && d[v] > d[u] + vd) {
                    d[v] = d[u] + vd
                    pi[v] = u
                }
            }
        }

        println("d: ${d.contentToString()}")
        println("pi: ${pi.contentToString()}")
    }

    fun extractMin(Q: MutableList<Int>, d: IntArray): Int {
        var minNode = Q[0]
        var minDistance: Int = d[0]

        Q.forEach {
            if (d[it] < minDistance) {
                minNode = it
                minDistance = d[it]
            }
        }

        Q.remove(minNode)
        return minNode
    }
}
