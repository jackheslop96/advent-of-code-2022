package day11

import utils.FileReader
import utils.Part
import utils.Part1
import utils.Part2

class MonkeyInTheMiddle {

    fun run(fileName: String, part: Part): Long {

        fun rec(lines: List<String>, monkeys: List<Monkey>): List<Monkey> {
            return when {
                lines.isEmpty() -> monkeys
                else -> {
                    val monkey = Monkey.apply(lines.take(6))
                    rec(lines.drop(7), monkeys.plusElement(monkey))
                }
            }
        }

        val lines = FileReader.readFile(fileName)
        val monkeys = rec(lines, emptyList()).toMutableList()

        val numberOfRounds = when (part) { Part1 -> 20; Part2 -> 10000 }

        val f: (Long) -> Long = when (part) {
            Part1 -> { x -> x / 3 }
            Part2 -> { x -> x % monkeys.map { it.divisor }.reduce { a, b -> a * b } }
        }

        for (round in 1..numberOfRounds) {
            monkeys.map { it.inspectItems(monkeys, f) }
        }

        return monkeys
            .map { it.itemsInspected }
            .sorted()
            .takeLast(2)
            .reduce { a, b -> a * b}
    }
}