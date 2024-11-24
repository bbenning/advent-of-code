package solution._2023

import com.github.shiguruikai.combinatoricskt.combinations
import util.*

class Day24(val input: List<String>) {
    fun solve1(minXY: Long, maxXY: Long): Int {
        val intersections = solve()
        val qualifiedResults = intersections.filter { it.first.time >= 0.0 && it.second.time >= 0.0 && it.first.x >= minXY && it.first.y >= minXY && it.first.x <= maxXY && it.first.y <= maxXY}

        return qualifiedResults.size
    }

    fun solve2(): Long {
        return 0L
    }

    data class HailStone(val x: Long, val y: Long, val z: Long, val vx: Int, val vy: Int, val vz: Int)

    data class Intersection2D(val x: Double, val y: Double, val time: Double)
    data class Collision(val x: Double, val y: Double, val z: Double, val time: Double)

    val hailstones = input.map {
        val longs = it.longs()
        HailStone(longs[0], longs[1], longs[2], longs[3].toInt(), longs[4].toInt(), longs[5].toInt())
    }

    private fun collision(h1: HailStone, h2: HailStone): Collision? {
        // could in principle also always intersect, but that shouldn't be in the problem set.
        if(h1 == h2) {
            return Collision(h1.x.toDouble(), h1.y.toDouble(), h1.z.toDouble(), 0.0)
        }

//        if((h1.x != h2.x && h1.vx == h2.vx) || (h1.y != h2.y && h1.vy == h2.vy) || (h1.z != h2.z && h1.vz == h2.vz)) {
//            return null
//        }

        val t = if(h1.vx != h2.vx) {
            (h2.x.toDouble() - h1.x) / (h1.vx - h2.vx)
        } else if (h1.vy != h2.vy) {
            (h2.y.toDouble() - h1.y) / (h1.vy - h2.vy)
        } else if (h1.vz != h2.vz) {
            (h2.z.toDouble() - h1.z) / (h1.vz - h2.vz)
        } else {
            null
        }

        if(t != null &&
            h1.x + t * h1.vx == h2.x + t * h2.vx &&
            h1.y + t * h1.vy == h2.y + t * h2.vy &&
            h1.z + t * h1.vz == h2.z + t * h2.vz) {
            return Collision(h1.x + t * h1.vx, h1.y + t * h1.vy, h1.z + t * h1.vz, t)
        }

        return null
    }


    private fun intersectXY(h1: HailStone, h2: HailStone): Intersection2D? {
        // with some algebra:
        // t1 == (y2 + vy2 * x1 / vx2 - vy2 * x2 / vx2 - y1) / (vy1 - vy2 * vx1 / vx2)

        val divisor = (h1.vy.toDouble() - h2.vy.toDouble() * h1.vx / h2.vx)

        if(divisor == 0.0) return null

        val t = (h2.y.toDouble() + h2.vy.toDouble() * h1.x / h2.vx - h2.vy.toDouble() * h2.x / h2.vx - h1.y) / divisor
        val x = h1.x + h1.vx * t
        val y = h1.y + h1.vy * t
        return Intersection2D(x, y, t)
    }

    private fun solve(): List<Pair<Intersection2D, Intersection2D>> {
        return hailstones.combinations(2).mapNotNull { (h1, h2) ->
            val i1 = intersectXY(h1, h2)
            val i2 = intersectXY(h2, h1)
            if(i1 == null || i2 == null) null
            else Pair(i1, i2)
        }.toList()

    }
}