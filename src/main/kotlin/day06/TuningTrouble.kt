package day06

import utils.FileReader
import utils.Part
import utils.Part1
import utils.Part2

class TuningTrouble {

    fun run(fileName: String, part: Part): Int {
        val dataStream = FileReader.readFile(fileName).first()
        return findMarker(dataStream, part)
    }

    fun findMarker(dataStream: String, part: Part): Int {
        val n = when (part) { Part1 -> 4; Part2 -> 14 }
        fun rec(chars: List<IndexedValue<Char>>): Int = when {
            chars.isEmpty() -> throw IllegalArgumentException("$dataStream does not have a marker")
            chars.take(n).map { it.value }.distinct().size == n -> chars[n].index
            else -> rec(chars.drop(1))
        }
        return rec(dataStream.withIndex().toList())
    }
}