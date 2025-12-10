package util

class DisjointSet(val size: Int) {
    private val parent = IntArray(size) { it }
    private val rank = IntArray(size) { 0 }

    var sets = size

    fun find(i: Int): Int {
        if (parent[i] != i) parent[i] = find(parent[i])
        return parent[i]
    }

    fun union(p: Int, q: Int): Boolean {
        val rootP = find(p)
        val rootQ = find(q)
        if (rootP == rootQ) return false

        if (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ
        else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP
        else {
            parent[rootQ] = rootP
            rank[rootP]++
        }

        sets--
        return true
    }
}
