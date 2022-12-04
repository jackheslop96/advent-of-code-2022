package day04

import utils.FileReader
import utils.Part
import utils.Part1
import utils.Part2

class CampCleanup {

    fun run(fileName: String, part: Part): Int {
        val lines = FileReader.readFile(fileName)
        val regex = """([\d]*)-([\d]*),([\d]*)-([\d]*)""".toRegex()

        fun range(min: String, max: String) = (min.toInt()..max.toInt()).toList()

        return lines.fold(0) { acc, line ->
            val (pair1_1, pair1_2, pair2_1, pair2_2) = regex.find(line)!!.destructured
            val range1 = range(pair1_1, pair1_2)
            val range2 = range(pair2_1, pair2_2)
            val predicate = when (part) {
                Part1 -> doPairsFullyOverlap(range1, range2)
                Part2 -> doPairsOverlapAtAll(range1, range2)
            }
            if (predicate) acc + 1 else acc
        }
    }

    fun doPairsFullyOverlap(range1: List<Int>, range2: List<Int>): Boolean {
        return range1.containsAll(range2) || range2.containsAll(range1)
    }

    fun doPairsOverlapAtAll(range1: List<Int>, range2: List<Int>): Boolean {
        return range1.intersect(range2).isNotEmpty()
    }
}