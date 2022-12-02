package day02

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class ResultTest {

    @Test
    fun whenLetterIsX_thenReturnLoss() {
        val expected = Loss
        assertEquals(expected, Result.apply("X"))
    }

    @Test
    fun whenLetterIsY_thenReturnDraw() {
        val expected = Draw
        assertEquals(expected, Result.apply("Y"))
    }

    @Test
    fun whenLetterIsZ_thenReturnWin() {
        val expected = Win
        assertEquals(expected, Result.apply("Z"))
    }

    @Test
    fun whenLetterIsInvalid_thenThrowException() {
        assertFailsWith<IllegalArgumentException> {
            Result.apply("G")
        }
    }

    @Test
    fun whenIChooseRock_andMyOpponentChoosesRock_thenReturnDraw() {
        assertEquals(Draw, Rock.against(Rock))
    }

    @Test
    fun whenIChoosePaper_andMyOpponentChoosesRock_thenReturnWin() {
        assertEquals(Win, Paper.against(Rock))
    }

    @Test
    fun whenIChooseScissors_andMyOpponentChoosesRock_thenReturnLoss() {
        assertEquals(Loss, Scissors.against(Rock))
    }

    @Test
    fun whenIChooseRock_andMyOpponentChoosesPaper_thenReturnLoss() {
        assertEquals(Loss, Rock.against(Paper))
    }

    @Test
    fun whenIChoosePaper_andMyOpponentChoosesPaper_thenReturnDraw() {
        assertEquals(Draw, Paper.against(Paper))
    }

    @Test
    fun whenIChooseScissors_andMyOpponentChoosesPaper_thenReturnWin() {
        assertEquals(Win, Scissors.against(Paper))
    }

    @Test
    fun whenIChooseRock_andMyOpponentChoosesScissors_thenReturnWin() {
        assertEquals(Win, Rock.against(Scissors))
    }

    @Test
    fun whenIChoosePaper_andMyOpponentChoosesScissors_thenReturnLoss() {
        assertEquals(Loss, Paper.against(Scissors))
    }

    @Test
    fun whenIChooseScissors_andMyOpponentChoosesScissors_thenReturnDraw() {
        assertEquals(Draw, Scissors.against(Scissors))
    }
}