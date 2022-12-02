package day02

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class ChoiceTest {

    @Test
    fun whenLetterIsA_thenReturnRock() {
        assertEquals(Rock, Choice.apply("A"))
    }

    @Test
    fun whenLetterIsB_thenReturnPaper() {
        assertEquals(Paper, Choice.apply("B"))
    }

    @Test
    fun whenLetterIsC_thenReturnScissors() {
        assertEquals(Scissors, Choice.apply("C"))
    }

    @Test
    fun whenLetterIsX_thenReturnRock() {
        assertEquals(Rock, Choice.apply("X"))
    }

    @Test
    fun whenLetterIsY_thenReturnPaper() {
        assertEquals(Paper, Choice.apply("Y"))
    }

    @Test
    fun whenLetterIsZ_thenReturnScissors() {
        assertEquals(Scissors, Choice.apply("Z"))
    }

    @Test
    fun whenLetterIsInvalid_thenThrowException() {
        assertFailsWith<IllegalArgumentException> {
            Choice.apply("G")
        }
    }
}