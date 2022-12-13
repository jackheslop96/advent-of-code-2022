package day12

import day12.HillClimbingAlgorithm.Companion.isAllowedToMoveTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import utils.Part1
import utils.Part2

internal class HillClimbingAlgorithmTest {

    @Test
    fun `when I run part 1 then I should get 31`() {
        val hca = HillClimbingAlgorithm()
        assertEquals(31, hca.run("/day-12-test-input.txt", Part1))
    }

    @Test
    fun `when I run part 2 then I should get 29`() {
        val hca = HillClimbingAlgorithm()
        assertEquals(29, hca.run("/day-12-test-input.txt", Part2))
    }

    @Test
    fun `when I have a grid then find starting point S`() {
        val grid = listOf(
            listOf('a', 'a', 'a'),
            listOf('a', 'a', 'a'),
            listOf('a', 'S', 'a')
        )

        val result = HillClimbingAlgorithm.findGridValue(grid, 'S')

        assertEquals(1, result.first.first)
        assertEquals(2, result.first.second)
        assertEquals('S', result.second)
    }

    @Test
    fun `when I am at a then I can move to S`() {
        assertTrue('a'.isAllowedToMoveTo('S'))
    }

    @Test
    fun `when I am at b then I can move to S`() {
        assertTrue('b'.isAllowedToMoveTo('S'))
    }

    @Test
    fun `when I am at b then I can move to a`() {
        assertTrue('b'.isAllowedToMoveTo('a'))
    }

    @Test
    fun `when I am at c then I can't move to c`() {
        assertFalse('c'.isAllowedToMoveTo('a'))
    }

    @Test
    fun `when I am at c then I can't move to S`() {
        assertFalse('c'.isAllowedToMoveTo('S'))
    }

    @Test
    fun `when I am at E then I can't move to x`() {
        assertFalse('E'.isAllowedToMoveTo('x'))
    }

    @Test
    fun `when I am at E then I can move to y`() {
        assertTrue('E'.isAllowedToMoveTo('y'))
    }

    @Test
    fun `when I am at E then I can move to z`() {
        assertTrue('E'.isAllowedToMoveTo('z'))
    }
}