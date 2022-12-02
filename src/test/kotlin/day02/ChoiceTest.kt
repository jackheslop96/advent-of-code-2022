package day02

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class ChoiceTest {

    @Test
    fun whenLetterIsA_thenReturnRock() {
        val expected = Rock
        assertEquals(expected, Choice.apply("A"))
    }

    @Test
    fun whenLetterIsB_thenReturnPaper() {
        val expected = Paper
        assertEquals(expected, Choice.apply("B"))
    }

    @Test
    fun whenLetterIsC_thenReturnScissors() {
        val expected = Scissors
        assertEquals(expected, Choice.apply("C"))
    }

    @Test
    fun whenLetterIsX_thenReturnRock() {
        val expected = Rock
        assertEquals(expected, Choice.apply("X"))
    }

    @Test
    fun whenLetterIsY_thenReturnPaper() {
        val expected = Paper
        assertEquals(expected, Choice.apply("Y"))
    }

    @Test
    fun whenLetterIsZ_thenReturnScissors() {
        val expected = Scissors
        assertEquals(expected, Choice.apply("Z"))
    }

    @Test
    fun whenLetterIsInvalid_thenThrowException() {
        assertFailsWith<IllegalArgumentException> {
            Choice.apply("G")
        }
    }
}