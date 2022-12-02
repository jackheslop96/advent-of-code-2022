package day02

sealed interface Choice {
    fun against(choice: Choice): Result
    val points: Int

    companion object {
        fun apply(letter: String): Choice = when (letter) {
            in "A", "X" -> Rock
            in "B", "Y" -> Paper
            in "C", "Z" -> Scissors
            else -> throw IllegalArgumentException("Invalid letter!")
        }
    }
}

object Rock : Choice {
    override fun against(choice: Choice): Result = when (choice) {
        Rock -> Draw
        Paper -> Loss
        Scissors -> Win
    }
    override val points: Int = 1
}

object Paper : Choice {
    override fun against(choice: Choice): Result = when (choice) {
        Rock -> Win
        Paper -> Draw
        Scissors -> Loss
    }
    override val points: Int = 2
}

object Scissors : Choice {
    override fun against(choice: Choice): Result = when (choice) {
        Rock -> Loss
        Paper -> Win
        Scissors -> Draw
    }
    override val points: Int = 3
}