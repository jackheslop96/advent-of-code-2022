package day02

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class RockPaperScissorsTest {

    @Test
    fun whenLetterIsA_thenReturnRock() {
        val rps = RockPaperScissors()
        val expected = RockPaperScissors.Rock
        assertEquals(expected, rps.letterToChoice("A"))
    }

    @Test
    fun whenLetterIsB_thenReturnPaper() {
        val rps = RockPaperScissors()
        val expected = RockPaperScissors.Paper
        assertEquals(expected, rps.letterToChoice("B"))
    }

    @Test
    fun whenLetterIsC_thenReturnScissors() {
        val rps = RockPaperScissors()
        val expected = RockPaperScissors.Scissors
        assertEquals(expected, rps.letterToChoice("C"))
    }

    @Test
    fun whenLetterIsX_thenReturnRock() {
        val rps = RockPaperScissors()
        val expected = RockPaperScissors.Rock
        assertEquals(expected, rps.letterToChoice("X"))
    }

    @Test
    fun whenLetterIsY_thenReturnPaper() {
        val rps = RockPaperScissors()
        val expected = RockPaperScissors.Paper
        assertEquals(expected, rps.letterToChoice("Y"))
    }

    @Test
    fun whenLetterIsZ_thenReturnScissors() {
        val rps = RockPaperScissors()
        val expected = RockPaperScissors.Scissors
        assertEquals(expected, rps.letterToChoice("Z"))
    }

    @Test
    fun whenLetterInvalid_thenThrowException() {
        val rps = RockPaperScissors()
        assertFailsWith<IllegalArgumentException> {
            rps.letterToChoice("G")
        }
    }

    @Test
    fun whenIChooseRock_thenGiveMe1Point() {
        assertEquals(1, RockPaperScissors.Rock.points)
    }

    @Test
    fun whenIChoosePaper_thenGiveMe2Points() {
        assertEquals(2, RockPaperScissors.Paper.points)
    }

    @Test
    fun whenIChooseScissors_thenGiveMe3Points() {
        assertEquals(3, RockPaperScissors.Scissors.points)
    }

    @Test
    fun whenIChooseRock_andMyOpponentChoosesRock_thenGiveMe3Points() {
        assertEquals(3, RockPaperScissors.Rock.against(RockPaperScissors.Rock))
    }

    @Test
    fun whenIChoosePaper_andMyOpponentChoosesRock_thenGiveMe6Points() {
        assertEquals(6, RockPaperScissors.Paper.against(RockPaperScissors.Rock))
    }

    @Test
    fun whenIChooseScissors_andMyOpponentChoosesRock_thenGiveMe0Points() {
        assertEquals(0, RockPaperScissors.Scissors.against(RockPaperScissors.Rock))
    }

    @Test
    fun whenIChooseRock_andMyOpponentChoosesPaper_thenGiveMe0Points() {
        assertEquals(0, RockPaperScissors.Rock.against(RockPaperScissors.Paper))
    }

    @Test
    fun whenIChoosePaper_andMyOpponentChoosesPaper_thenGiveMe3Points() {
        assertEquals(3, RockPaperScissors.Paper.against(RockPaperScissors.Paper))
    }

    @Test
    fun whenIChooseScissors_andMyOpponentChoosesPaper_thenGiveMe6Points() {
        assertEquals(6, RockPaperScissors.Scissors.against(RockPaperScissors.Paper))
    }

    @Test
    fun whenIChooseRock_andMyOpponentChoosesScissors_thenGiveMe6Points() {
        assertEquals(6, RockPaperScissors.Rock.against(RockPaperScissors.Scissors))
    }

    @Test
    fun whenIChoosePaper_andMyOpponentChoosesScissors_thenGiveMe0Points() {
        assertEquals(0, RockPaperScissors.Paper.against(RockPaperScissors.Scissors))
    }

    @Test
    fun whenIChooseScissors_andMyOpponentChoosesScissors_thenGiveMe3Points() {
        assertEquals(3, RockPaperScissors.Scissors.against(RockPaperScissors.Scissors))
    }

    @Test
    fun whenIUseTheTestFile_thenIShouldGet15Points() {
        val rps = RockPaperScissors()
        assertEquals(15, rps.run("/day-02-test-input.txt"))
    }
}