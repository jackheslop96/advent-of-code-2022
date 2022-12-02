package day02

import utils.FileReader
import utils.Part
import utils.Part1
import utils.Part2

class RockPaperScissors {

    fun run(fileName: String, part: Part): Int {
        val lines = FileReader.readFile(fileName)
        val regex = """([A-C]) ([X-Z])""".toRegex()
        return lines.fold(0) { acc, line ->
            val (abc, xyz) = regex.find(line)!!.destructured
            val opponentChoice = Choice.apply(abc)
            when (part) {
                Part1 -> {
                    val myChoice = Choice.apply(xyz)
                    val result = myChoice.against(opponentChoice)
                    acc + myChoice.points + result.points
                }
                Part2 -> {
                    val result = Result.apply(xyz)
                    val myChoice = result.myChoice(opponentChoice)
                    acc + myChoice.points + result.points
                }
            }
        }
    }
}