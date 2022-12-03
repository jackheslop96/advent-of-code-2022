package day03

import utils.FileReader
import utils.Part
import utils.Part1
import utils.Part2

class RucksackReorganisation {

    fun run(fileName: String, part: Part): Int {
        val lines = FileReader.readFile(fileName)
        return when (part) {
            Part1 -> {
                lines.fold(0) { acc, line ->
                    val rucksack = Rucksack(line)
                    acc + rucksack.itemsInBothCompartments.sumOf { charToInt(it) }
                }
            }
            Part2 -> {
                fun rec(rs: List<Rucksack>, acc: Int): Int {
                    return when (rs) {
                        emptyList<String>() -> acc
                        else -> {
                            val items = commonItems(rs.take(3))
                            rec(rs.drop(3), acc + items.sumOf { charToInt(it) })
                        }
                    }
                }
                rec(lines.map { Rucksack(it) }, 0)
            }
        }
    }

    class Rucksack(val input: String) {
        private val half = input.length / 2
        val compartment1: String = input.take(half)
        val compartment2: String = input.drop(half)

        val itemsInBothCompartments: Set<Char> = compartment1.toList().intersect(compartment2.toList())
    }

    fun charToInt(char: Char): Int {
        return if (char.isUpperCase()) {
            char.code - 38
        } else {
            char.code - 96
        }
    }

    fun commonItems(rucksacks: List<Rucksack>): Set<Char> {
        return rucksacks.fold(rucksacks.first().input.toSet()) { acc, rucksack ->
            acc.intersect(rucksack.input.toSet())
        }
    }
}