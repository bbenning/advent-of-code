package solution._2023

import util.*

class Day22(val input: List<String>) {
    fun solve1(): Int {
        val mapOfBricksAndBricksThatItNeeds = fallenBricks.fold(emptyMap<Brick, List<Brick>>()) { acc, brick ->
            val fall1Step = brick.fall()
            val supportedBy = fallenBricks.filter { it != brick && it.collidesWith(fall1Step) }

            acc + Pair(brick, supportedBy)
        }
        return fallenBricks.filter { fallenBrick -> mapOfBricksAndBricksThatItNeeds.values.all { fallenBrick !in it || it.size > 1}}.size
    }

    fun solve2(): Int {
        // should be much, much faster by building chains of falling blocks, but don't feel like it.
        val fallenBricksSet = fallenBricks.toSet()

        val bricksWithDependents = bricks.associateWith { brickToRemove ->
            val fallWithRemoval = letThemFall(emptyMap(), bricks - brickToRemove).toSet()
            (fallWithRemoval - fallenBricksSet)
        }

        return bricksWithDependents.values.sumOf { it.size }
    }

    data class Coord3D(val x: Int, val y: Int, val z: Int)
    data class Brick(val start: Coord3D, val end: Coord3D, val id: Int) {
        val rangeX = minOf(start.x, end.x)  .. maxOf(start.x, end.x)
        val rangeY = minOf(start.y, end.y)  .. maxOf(start.y, end.y)
        val rangeZ = minOf(start.z, end.z)  .. maxOf(start.z, end.z)

        fun fall(): Brick = this.copy(start=start.copy(z = start.z - 1), end=end.copy(z = end.z - 1))
        fun fallUp(): Brick = this.copy(start=start.copy(z = start.z + 1), end=end.copy(z = end.z + 1))

        fun collidesWith(other: Brick): Boolean =
            (this.rangeX intersect other.rangeX).isNotEmpty() &&
            (this.rangeY intersect other.rangeY).isNotEmpty() &&
            (this.rangeZ intersect other.rangeZ).isNotEmpty()
    }

    private val bricks = input.mapIndexed { idx, str ->
        val (brick1Str, brick2Str) = str.split("~")

        val (x1,y1,z1) = brick1Str.ints()
        val (x2,y2,z2) = brick2Str.ints()
        Brick(Coord3D(x1,y1,z1), Coord3D(x2,y2,z2), idx)
    }.sortedBy { it.rangeZ.first }

    private fun letThemFall(acc: Map<Int,List<Brick>>, remaining: List<Brick>) : List<Brick> {
        if(remaining.isEmpty()) {
            return acc.values.flatten()
        }

        var toFall = remaining.first()
        while(true) {
            val fall1Step = toFall.fall()

            if(fall1Step.rangeZ.first >= 1 &&  (acc[fall1Step.rangeZ.first]?:emptyList()).none { it.collidesWith(fall1Step) }) {
                toFall = fall1Step
            } else {
                val listAtHeight = acc[toFall.rangeZ.last]?: emptyList()
                val newAcc = acc + Pair(toFall.rangeZ.last, listAtHeight + toFall)
                return letThemFall(newAcc, remaining.drop(1))
            }
        }
    }

    private val fallenBricks = letThemFall(emptyMap(), bricks)



}