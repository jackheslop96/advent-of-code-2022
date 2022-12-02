package day02

sealed interface Result {
    val points: Int
    fun myChoice(opponentChoice: Choice): Choice

    companion object {
        fun apply(letter: String): Result = when (letter) {
            "X" -> Loss
            "Y" -> Draw
            "Z" -> Win
            else -> throw IllegalArgumentException("Invalid letter!")
        }
    }
}

object Win : Result {
    override val points: Int = 6
    override fun myChoice(opponentChoice: Choice): Choice = when (opponentChoice) {
        Rock -> Paper
        Paper -> Scissors
        Scissors -> Rock
    }
}

object Draw : Result {
    override val points: Int = 3
    override fun myChoice(opponentChoice: Choice): Choice = opponentChoice
}

object Loss : Result {
    override val points: Int = 0
    override fun myChoice(opponentChoice: Choice): Choice = when (opponentChoice) {
        Rock -> Scissors
        Paper -> Rock
        Scissors -> Paper
    }
}