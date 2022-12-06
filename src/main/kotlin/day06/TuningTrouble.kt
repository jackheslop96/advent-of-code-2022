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
        val n = when (part) {
            Part1 -> 4
            Part2 -> 14
        }

        fun firstNCharactersAreDistinct(chars: List<IndexedValue<Char>>): Boolean =
            chars.take(n).map { it.value }.distinct().size == n

        fun rec(chars: List<IndexedValue<Char>>): Int {
            return when (chars) {
                emptyList<IndexedValue<Char>>() -> {
                    throw IllegalArgumentException("$dataStream does not have a marker")
                }
                else -> {
                    if (firstNCharactersAreDistinct(chars)) {
                        chars[n].index
                    } else {
                        rec(chars.drop(1))
                    }
                }
            }
        }

        return rec(dataStream.withIndex().toList())
    }
}