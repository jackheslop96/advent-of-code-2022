package day08

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import utils.Part1
import utils.Part2

internal class TreetopTreeHouseTest {

    @Test
    fun whenIRunPart1_thenIShouldGet21() {
        val tth = TreetopTreeHouse()
        assertEquals(21, tth.run("/day-08-test-input.txt", Part1))
    }

    @Test
    fun whenATreeIsVisibleFromTheTop_thenReturnTrue() {
        val tth = TreetopTreeHouse()
        val trees = listOf(
            listOf(3, 3, 0, 3, 3),
            listOf(3, 3, 0, 3, 3),
            listOf(3, 3, 3, 3, 3),
            listOf(3, 3, 3, 3, 3),
            listOf(3, 3, 3, 3, 3)
        )
        val coordinate = Pair(2, 2)
        assertTrue(tth.isTreeVisible(trees, coordinate))
    }

    @Test
    fun whenATreeIsVisibleFromTheRight_thenReturnTrue() {
        val tth = TreetopTreeHouse()
        val trees = listOf(
            listOf(3, 3, 3, 3, 3),
            listOf(3, 3, 3, 3, 3),
            listOf(3, 3, 3, 0, 0),
            listOf(3, 3, 3, 3, 3),
            listOf(3, 3, 3, 3, 3)
        )
        val coordinate = Pair(2, 2)
        assertTrue(tth.isTreeVisible(trees, coordinate))
    }

    @Test
    fun whenATreeIsVisibleFromTheLeft_thenReturnTrue() {
        val tth = TreetopTreeHouse()
        val trees = listOf(
            listOf(3, 3, 3, 3, 3),
            listOf(3, 3, 3, 3, 3),
            listOf(0, 0, 3, 3, 3),
            listOf(3, 3, 3, 3, 3),
            listOf(3, 3, 3, 3, 3)
        )
        val coordinate = Pair(2, 2)
        assertTrue(tth.isTreeVisible(trees, coordinate))
    }

    @Test
    fun whenATreeIsVisibleFromTheBottom_thenReturnTrue() {
        val tth = TreetopTreeHouse()
        val trees = listOf(
            listOf(3, 3, 3, 3, 3),
            listOf(3, 3, 3, 3, 3),
            listOf(3, 3, 3, 3, 3),
            listOf(3, 3, 0, 3, 3),
            listOf(3, 3, 0, 3, 3)
        )
        val coordinate = Pair(2, 2)
        assertTrue(tth.isTreeVisible(trees, coordinate))
    }

    @Test
    fun whenATreeIsNotVisible_thenReturnFalse() {
        val tth = TreetopTreeHouse()
        val trees = listOf(
            listOf(3, 0, 3, 7, 3),
            listOf(2, 5, 5, 1, 2),
            listOf(6, 5, 3, 3, 2),
            listOf(3, 3, 5, 4, 9),
            listOf(3, 5, 3, 9, 0)
        )
        val coordinate = Pair(2, 2)
        assertFalse(tth.isTreeVisible(trees, coordinate))
    }

    @Test
    fun whenIRunPart2_thenIShouldGet8() {
        val tth = TreetopTreeHouse()
        assertEquals(8, tth.run("/day-08-test-input.txt", Part2))
    }

    @Test
    fun whenTreeIsTheMiddle5InSecondRow_thenScenicScoreIs4() {
        val tth = TreetopTreeHouse()
        val trees = listOf(
            listOf(3, 0, 3, 7, 3),
            listOf(2, 5, 5, 1, 2),
            listOf(6, 5, 3, 3, 2),
            listOf(3, 3, 5, 4, 9),
            listOf(3, 5, 3, 9, 0)
        )
        val coordinate = Pair(2, 1)
        assertEquals(4, tth.scenicScore(trees, coordinate))
    }

    @Test
    fun whenTreeIsTheMiddle5InSecFourthRow_thenScenicScoreIs8() {
        val tth = TreetopTreeHouse()
        val trees = listOf(
            listOf(3, 0, 3, 7, 3),
            listOf(2, 5, 5, 1, 2),
            listOf(6, 5, 3, 3, 2),
            listOf(3, 3, 5, 4, 9),
            listOf(3, 5, 3, 9, 0)
        )
        val coordinate = Pair(2, 3)
        assertEquals(8, tth.scenicScore(trees, coordinate))
    }
}