package day04

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import utils.Part1
import utils.Part2

internal class CampCleanupTest {

    @Test
    fun whenFirstElfHas2To8_andSecondElfHas3To7_thenReturnTrue() {
        val cc = CampCleanup()
        assertTrue(cc.doPairsFullyOverlap((2..8).toList(), (3..7).toList()))
    }

    @Test
    fun whenFirstElfHas1To2_andSecondElfHas4To5_thenReturnFalse() {
        val cc = CampCleanup()
        assertFalse(cc.doPairsFullyOverlap((1..2).toList(), (4..5).toList()))
    }

    @Test
    fun whenFirstElfHas3To7_andSecondElfHas2To8_thenReturnTrue() {
        val cc = CampCleanup()
        assertTrue(cc.doPairsFullyOverlap((3..7).toList(), (2..8).toList()))
    }

    @Test
    fun whenGivenATestInput_andItIsPart1_thenFindTheNumberOfPairsForReconsideration() {
        val cc = CampCleanup()
        assertEquals(2, cc.run("/day-04-test-input.txt", Part1))
    }

    @Test
    fun whenFirstElfHas5To7_andSecondElfHas7To9_thenReturnTrue() {
        val cc = CampCleanup()
        assertTrue(cc.doPairsOverlapAtAll((5..7).toList(), (7..9).toList()))
    }

    @Test
    fun whenFirstElfHas5To7_andSecondElfHas8To9_thenReturnFalse() {
        val cc = CampCleanup()
        assertFalse(cc.doPairsOverlapAtAll((5..7).toList(), (8..9).toList()))
    }

    @Test
    fun whenFirstElfHas7To9_andSecondElfHas5To7_thenReturnTrue() {
        val cc = CampCleanup()
        assertTrue(cc.doPairsOverlapAtAll((7..9).toList(), (5..7).toList()))
    }

    @Test
    fun whenGivenATestInput_andItIsPart2_thenFindTheNumberOfPairsForReconsideration() {
        val cc = CampCleanup()
        assertEquals(4, cc.run("/day-04-test-input.txt", Part2))
    }
}