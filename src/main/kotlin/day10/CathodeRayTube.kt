package day10

import utils.FileReader

class CathodeRayTube {

    fun part1(fileName: String): Int {
        val instructions = FileReader.readFile(fileName)
        val c = executeInstructions(instructions)
        return c.signalStrength(20) +
                c.signalStrength(60) +
                c.signalStrength(100) +
                c.signalStrength(140) +
                c.signalStrength(180) +
                c.signalStrength(220)
    }

    fun part2(fileName: String): List<String> {
        val instructions = FileReader.readFile(fileName)
        val c = executeInstructions(instructions)

        fun rec(cycles: List<Int>, acc: List<String>): List<String> {
            return when (cycles) {
                emptyList<Int>() -> acc
                else -> {
                    val pixels = cycles.take(40).withIndex().map { (index, register) ->
                        if (register - 1 <= index && register + 1 >= index) "#" else "."
                    }
                    rec(cycles.drop(40), acc.plusElement(pixels.joinToString("")))
                }
            }
        }

        return rec(c.cycles, emptyList()).dropLast(1)
    }

    class ClockCircuit(val cycles: List<Int>) {

        fun execute(instruction: String): ClockCircuit {
            val currentValue = cycles.lastOrNull() ?: 1
            return when {
                instruction == "noop" -> ClockCircuit(cycles.plusElement(currentValue))
                instruction.matches(addRegex) -> {
                    val (amount) = addRegex.find(instruction)!!.destructured
                    val updatedCycles = cycles
                        .plusElement(currentValue)
                        .plusElement(currentValue + amount.toInt())
                    ClockCircuit(updatedCycles)
                }
                else -> throw Exception("Instruction $instruction not recognised")
            }
        }

        fun signalStrength(n: Int): Int = n * cycles[n - 1]

        companion object {
            fun apply(): ClockCircuit = ClockCircuit(listOf(1))
            val addRegex = """addx (-?[\d]*)""".toRegex()
        }
    }

    fun executeInstructions(instructions: List<String>): ClockCircuit {
        return instructions.fold(ClockCircuit.apply()) { acc, instruction ->
            acc.execute(instruction)
        }
    }
}