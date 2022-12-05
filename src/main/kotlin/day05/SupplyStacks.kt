package day05

import utils.FileReader
import utils.Part
import utils.Part1
import utils.Part2

class SupplyStacks {

    fun run(fileName: String, part: Part): String {
        val lines = FileReader.readFile(fileName)
        val stacks = parseInput(lines.takeWhile { it.trim().isNotEmpty() })
        val instructions = lines.takeLastWhile { it.trim().isNotEmpty() }

        val finalStacks = instructions.fold(stacks) { acc, instruction ->
            applyInstruction(acc, instruction, part)
        }

        return finalStacks.joinToString("") { it.last() }
    }

    fun parseInput(input: List<String>): List<List<String>> {
        val reversed = input.reversed()
        val stacks = reversed.drop(1)
        val numberOfStacks = reversed.first().last().toString().toInt()

        return (1..numberOfStacks).fold(emptyList()) { rows: List<List<String>>, i ->
            val index = (4 * i) - 3
            val r: List<String> = stacks.map { x -> try {
                x[index].toString().trim()
            } catch (e: Exception) {
                ""
            } }.takeWhile { it.isNotEmpty() }
            rows.plusElement(r)
        }
    }

    fun applyInstruction(stacks: List<List<String>>, instruction: String, part: Part): List<List<String>> {
        val regex = """move ([\d]*) from ([\d]*) to ([\d]*)""".toRegex()
        val (a, b, c) = regex.find(instruction)!!.destructured
        val numberToMove = a.toInt()
        val fromStack = b.toInt()
        val toStack = c.toInt()

        val temp = stacks[fromStack - 1].takeLast(numberToMove)
        val crates = when (part) {
            Part1 -> temp.reversed()
            Part2 -> temp
        }

        return stacks.withIndex().fold(emptyList()) { acc, (i, s) ->
            when (i) {
                fromStack - 1 -> acc.plusElement(s.dropLast(numberToMove))
                toStack - 1 -> acc.plusElement(s.plus(crates))
                else -> acc.plusElement(s)
            }
        }
    }
}