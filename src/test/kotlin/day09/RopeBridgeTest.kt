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
        assertEquals(setOf(Pair(0, 0)), result.visitedPositions)
    }

    @Test
    fun whenHeadIsTwoSpacesToTheRight_thenTailShouldMoveOneSpaceToTheRight() {
        val head = RopeBridge.Head.apply(Pair(2, 0))
        val tail = RopeBridge.Tail.apply()
        val result = tail.next(head)
        assertEquals(Pair(1, 0), result.position)
        assertEquals(setOf(Pair(0, 0), Pair(1, 0)), result.visitedPositions)
    }

    @Test
    fun whenHeadIs1SpaceDiagonallyUpToTheRight_thenTailShouldNotMove() {
        val head = RopeBridge.Head.apply(Pair(1, 1))
        val tail = RopeBridge.Tail.apply()
        val result = tail.next(head)
        assertEquals(Pair(0, 0), result.position)
        assertEquals(setOf(Pair(0, 0)), result.visitedPositions)
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
    fun whenGivenR_thenReturnARightMove() {
        assertEquals(Pair(1, 0), RopeBridge.parseDirection("R"))
    }

    @Test
    fun whenGivenU_thenReturnAnUpMove() {
        assertEquals(Pair(0, 1), RopeBridge.parseDirection("U"))
    }

    @Test
    fun whenGivenL3_thenReturnALeftMove() {
        assertEquals(Pair(-1, 0), RopeBridge.parseDirection("L"))
    }

    @Test
    fun whenGivenD1_thenReturnADownMove() {
        assertEquals(Pair(0, -1), RopeBridge.parseDirection("D"))
    }

    @Test
    fun whenGivenF_thenThrowAnException() {
        assertFailsWith<Exception> {
            RopeBridge.parseDirection("F")
        }
    }
}