package day09

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.Part1
import kotlin.test.assertFailsWith

internal class RopeBridgeTest {

    @Test
    fun whenIRunPart1_thenIShouldGet13() {
        val rb = RopeBridge()
        assertEquals(13, rb.run("/day-09-test-input.txt", Part1))
    }

    @Test
    fun whenHeadIsOneSpaceToTheRight_thenTailShouldNotMove() {
        val head = RopeBridge.Head.apply(Pair(1, 0))
        val tail = RopeBridge.Tail.apply()
        val result = tail.next(head)
        assertEquals(Pair(0, 0), result.position)
        assertEquals(listOf(Pair(0, 0)), result.visitedPositions)
    }

    @Test
    fun whenHeadIsTwoSpacesToTheRight_thenTailShouldMoveOneSpaceToTheRight() {
        val head = RopeBridge.Head.apply(Pair(2, 0))
        val tail = RopeBridge.Tail.apply()
        val result = tail.next(head)
        assertEquals(Pair(1, 0), result.position)
        assertEquals(listOf(Pair(0, 0), Pair(1, 0)), result.visitedPositions)
    }

    @Test
    fun whenHeadIs1SpaceDiagonallyUpToTheRight_thenTailShouldNotMove() {
        val head = RopeBridge.Head.apply(Pair(1, 1))
        val tail = RopeBridge.Tail.apply()
        val result = tail.next(head)
        assertEquals(Pair(0, 0), result.position)
        assertEquals(listOf(Pair(0, 0)), result.visitedPositions)
    }

    @Test
    fun whenICallPossibleMoves_thenIShouldGetAllPossibleMoves() {
        val expected = listOf(
            Pair(-1, -1),
            Pair(-1, 0),
            Pair(-1, 1),
            Pair(0, -1),
            Pair(0, 0),
            Pair(0, 1),
            Pair(1, -1),
            Pair(1, 0),
            Pair(1, 1),
        )
        assertEquals(expected, RopeBridge.possibleMoves)
    }

    @Test
    fun whenGivenR4_thenReturnARightMove4Times() {
        val expected = listOf(
            Pair(1, 0),
            Pair(1, 0),
            Pair(1, 0),
            Pair(1, 0)
        )
        assertEquals(expected, RopeBridge.expandInstruction("R 4"))
    }

    @Test
    fun whenGivenU4_thenReturnAnUpMove4Times() {
        val expected = listOf(
            Pair(0, 1),
            Pair(0, 1),
            Pair(0, 1),
            Pair(0, 1)
        )
        assertEquals(expected, RopeBridge.expandInstruction("U 4"))
    }

    @Test
    fun whenGivenL3_thenReturnALeftMove3Times() {
        val expected = listOf(
            Pair(-1, 0),
            Pair(-1, 0),
            Pair(-1, 0)
        )
        assertEquals(expected, RopeBridge.expandInstruction("L 3"))
    }

    @Test
    fun whenGivenD1_thenReturnADownMove1Time() {
        val expected = listOf(
            Pair(0, -1)
        )
        assertEquals(expected, RopeBridge.expandInstruction("D 1"))
    }

    @Test
    fun whenGivenF5_thenThrowAnException() {
        assertFailsWith<Exception> {
            RopeBridge.expandInstruction("F 5")
        }
    }
}