package day13

import utils.FileReader
import utils.Part
import utils.Part1
import utils.Part2

class DistressSignal {

    fun run(fileName: String, part: Part): Int {
        val lines = FileReader.readFile(fileName)
        return when (part) {
            Part1 -> part1(lines)
            Part2 -> part2(lines)
        }
    }

    fun part1(lines: List<String>): Int {
        fun rec(lines: List<String>, pairIndex: Int = 1, acc: Int = 0): Int {
            return when {
                lines.isEmpty() -> acc
                else -> {
                    when {
                        lines.first().isEmpty() -> rec(lines.drop(1), pairIndex + 1, acc)
                        else -> {
                            val left = parseInput(lines[0])
                            val right = parseInput(lines[1])
                            val updatedAcc = if (areInputsInCorrectOrder(left, right)) acc + pairIndex else acc
                            rec(lines.drop(2), pairIndex, updatedAcc)
                        }
                    }
                }
            }
        }
        return rec(lines)
    }

    fun part2(lines: List<String>): Int {
        val packet2 = "[[2]]"
        val packet6 = "[[6]]"
        val updatedLines = lines.filter { it.isNotEmpty() }.plusElement(packet2).plusElement(packet6)
        val orderedPackets = updatedLines.sortedWith { a, b ->
            val left = parseInput(a)
            val right = parseInput(b)
            if (areInputsInCorrectOrder(left, right)) -1 else 1
        }.withIndex()
        val indexOfPacket2 = orderedPackets.find { it.value == packet2 }!!.index + 1
        val indexOfPacket6 = orderedPackets.find { it.value == packet6 }!!.index + 1
        return indexOfPacket2 * indexOfPacket6
    }

    companion object {
        fun parseInput(input: String): List<*> {
            fun rec(chars: List<Char>, acc: Pair<Int, List<Any>> = Pair(0, emptyList())): Pair<Int, List<Any>> {
                return when {
                    chars.isEmpty() || chars.first() == ']' -> Pair(acc.first + 1, acc.second)
                    chars.first() == ',' -> rec(chars.drop(1), Pair(acc.first + 1, acc.second))
                    chars.first() == '[' -> {
                        val list = rec(chars.drop(1))
                        val updatedList = acc.second.plusElement(list.second)
                        val updatedAcc = Pair(acc.first + list.first + 1, updatedList)
                        rec(chars.drop(list.first + 1), updatedAcc)
                    }
                    else -> {
                        val number = chars.takeWhile { it.isDigit() }.joinToString("")
                        val updatedList = acc.second.plusElement(number.toInt())
                        val updatedAcc = Pair(acc.first + number.length, updatedList)
                        rec(chars.drop(number.length), updatedAcc)
                    }
                }
            }

            return when (val r = rec(input.toList()).second.first()) {
                is List<*> -> r
                else -> throw Exception("Result wasn't nested inside a list")
            }
        }

        fun areInputsInCorrectOrder(left: List<*>, right: List<*>): Boolean {
            fun rec(left: List<*>, right: List<*>): Boolean {
                return when {
                    left.isEmpty() -> true
                    right.isEmpty() -> false
                    else -> {
                        val l1 = left.first()
                        val r1 = right.first()
                        when {
                            l1 is Int && r1 is Int -> {
                                when {
                                    l1 < r1 -> true
                                    l1 > r1 -> false
                                    else -> rec(left.drop(1), right.drop(1))
                                }
                            }
                            l1 is List<*> && r1 is List<*> -> rec(l1, r1)
                            l1 is Int && r1 is List<*> -> rec(listOf(l1), r1)
                            l1 is List<*> && r1 is Int -> rec(l1, listOf(r1))
                            else -> throw Exception("One or both of $l1 and $r1 are in an invalid state")
                        }
                    }
                }
            }
            return rec(left, right)
        }
    }
}