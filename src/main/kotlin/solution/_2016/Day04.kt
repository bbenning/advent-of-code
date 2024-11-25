package solution._2016

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import util.*
import util.longs.product
import util.mapping.Coord
import util.mapping.Direction

class Day04(val input: List<String>) {
    fun solve1(): Long {
        return solve()
    }

    fun solve2(): Long {
        val convertedRooms = rooms.map { room ->
            val name = room.encryptedName.map {
                if(it == '-') '-' else
                'a' + (((it - 'a') + room.id) % 26)
            }.joinToString("")

            room.copy(encryptedName = name)
        }

        return convertedRooms.first { it.encryptedName.contains("northpole") }.id.toLong()
    }

    private val regex = "([a-z\\-]+)-(\\d+)\\[([a-z]+)]".toRegex()

    private val rooms = input.map { s ->
        val (name, id, checksum) = regex.find(s)!!.destructured
        Room(name, id.toInt(), checksum.toSet())
    }

    data class Room(val encryptedName: String, val id: Int, val checksum: Set<Char>)

    private fun realRoom(room: Room): Boolean {
        val mappedRoom = room.encryptedName.replace("-","").groupBy { it }.mapValues { it.value.size }
        val validLetters: Set<Char> = mappedRoom.toList().sortedBy { -it.second * 100 + (it.first - 'a')}.take(5).map { it.first }.toSet()

        return validLetters == room.checksum
    }

    private fun solve(): Long {
        val realRooms = rooms.filter { realRoom(it) }

        return realRooms.sumOf { it.id }.toLong()
    }
}