package day02

import utils.FileReader

class RockPaperScissors {

    sealed interface Choice {
        fun against(choice: Choice): Int
        val points: Int
    }

    object Rock : Choice {
        override fun against(choice: Choice): Int {
            return when (choice) {
                Rock -> 3
                Paper -> 0
                Scissors -> 6
            }
        }

        override val points: Int = 1
    }

    object Paper : Choice {
        override fun against(choice: Choice): Int {
            return when (choice) {
                Rock -> 6
                Paper -> 3
                Scissors -> 0
            }
        }

        override val points: Int = 2
    }

    object Scissors : Choice {
        override fun against(choice: Choice): Int {
            return when (choice) {
                Rock -> 0
                Paper -> 6
                Scissors -> 3
            }
        }

        override val points: Int = 3
    }

    fun letterToChoice(letter: String): Choice {
        return when (letter) {
            in "A", "X" -> Rock
            in "B", "Y" -> Paper
            in "C", "Z" -> Scissors
            else -> throw IllegalArgumentException("Invalid letter!")
        }
    }

    fun run(fileName: String): Int {
        val lines = FileReader.readFile(fileName)
        return lines.fold(0) { acc, line ->
            val regex = """([A-C]) ([X-Z])""".toRegex()
            val (opponent, me) = regex.find(line)!!.destructured
            val opponentChoice = letterToChoice(opponent)
            val myChoice = letterToChoice(me)
            acc + myChoice.points + myChoice.against(opponentChoice)
        }
    }
}