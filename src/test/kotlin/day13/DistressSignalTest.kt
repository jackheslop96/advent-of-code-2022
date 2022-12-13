package day13

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import utils.Part1
import utils.Part2

internal class DistressSignalTest {

    @Test
    fun `must be able to parse a list of integers`() {
        val input = "[1,1,3,1,1]"
        val expected = listOf<Any>(1, 1, 3, 1, 1)
        assertEquals(expected, DistressSignal.parseInput(input))
    }

    @Test
    fun `must be able to parse a nested list`() {
        val input = "[[1],[2,3,4]]"
        val expected = listOf(listOf(1), listOf(2, 3, 4))
        assertEquals(expected, DistressSignal.parseInput(input))
    }

    @Test
    fun `must be able to parse a complex nested list`() {
        val input = "[1,[2,[3,[4,[5,6,7]]]],8,9]"
        val expected = listOf(1, listOf(2, listOf(3, listOf(4, listOf(5, 6, 7)))), 8, 9)
        assertEquals(expected, DistressSignal.parseInput(input))
    }

    @Test
    fun `must be able to parse a series of empty lists`() {
        val input = "[[[]]]"
        val expected = listOf<Any>(listOf<Any>(emptyList<Any>()))
        assertEquals(expected, DistressSignal.parseInput(input))
    }

    @Test
    fun `must be able to parse a series of lists and empty lists`() {
        val input = "[[0,[[],[],[10,6],0],[5],5,9],[[5],7]]"
        val expected = listOf(listOf(0, listOf(emptyList<Any>(), emptyList<Any>(), listOf(10, 6), 0), listOf(5), 5, 9), listOf(listOf(5), 7))
        assertEquals(expected, DistressSignal.parseInput(input))
    }

    @Test
    fun `correct order test 1`() {
        val left = listOf<Any>(1, 1, 3, 1, 1)
        val right = listOf<Any>(1, 1, 5, 1, 1)
        assertTrue(DistressSignal.areInputsInCorrectOrder(left, right))
    }

    @Test
    fun `correct order test 2`() {
        val left = listOf(listOf(1), listOf(2, 3, 4))
        val right = listOf(listOf(1), 4)
        assertTrue(DistressSignal.areInputsInCorrectOrder(left, right))
    }

    @Test
    fun `incorrect order test 1`() {
        val left = listOf(9)
        val right = listOf(listOf(8, 7, 6))
        assertFalse(DistressSignal.areInputsInCorrectOrder(left, right))
    }

    @Test
    fun `correct order test 3`() {
        val left = listOf(listOf(4, 4), 4, 4)
        val right = listOf(listOf(4, 4), 4, 4, 4)
        assertTrue(DistressSignal.areInputsInCorrectOrder(left, right))
    }

    @Test
    fun `incorrect order test 2`() {
        val left = listOf(7, 7, 7, 7)
        val right = listOf(7, 7, 7)
        assertFalse(DistressSignal.areInputsInCorrectOrder(left, right))
    }

    @Test
    fun `correct order test 4`() {
        val left = emptyList<Any>()
        val right = listOf(3)
        assertTrue(DistressSignal.areInputsInCorrectOrder(left, right))
    }

    @Test
    fun `incorrect order test 3`() {
        val left = listOf<Any>(listOf<Any>(emptyList<Any>()))
        val right = listOf<Any>(emptyList<Any>())
        assertFalse(DistressSignal.areInputsInCorrectOrder(left, right))
    }

    @Test
    fun `incorrect order test 4`() {
        val left = listOf(1, listOf(2, listOf(3, listOf(4, listOf(5, 6, 7)))), 8, 9)
        val right = listOf(1, listOf(2, listOf(3, listOf(4, listOf(5, 6, 0)))), 8, 9)
        assertFalse(DistressSignal.areInputsInCorrectOrder(left, right))
    }

    @Test
    fun `when I run part 1 then I should get 13`() {
        val result = DistressSignal().run("/day-13-test-input.txt", Part1)
        assertEquals(13, result)
    }

    @Test
    fun `when I run part 2 then I should get 140`() {
        val result = DistressSignal().run("/day-13-test-input.txt", Part2)
        assertEquals(140, result)
    }
}