package day14

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.Part1
import utils.Part2

internal class RegolithReservoirTest {

    @Test
    fun `when I have an input line of fixed x then find the coordinates that contain rock`() {
        val path = "498,4 -> 498,6"
        val expected = setOf(Pair(498, 4), Pair(498, 5), Pair(498, 6))
        assertEquals(expected, RegolithReservoir.parsePath(path))
    }

    @Test
    fun `when I have an input line of fixed y then find the coordinates that contain rock`() {
        val path = "503,4 -> 502,4"
        val expected = setOf(Pair(503, 4), Pair(502, 4))
        assertEquals(expected, RegolithReservoir.parsePath(path))
    }

    @Test
    fun `when I have multiple paths then find the coordinates that contain rock`() {
        val path = "498,4 -> 498,6 -> 496,6"
        val expected = setOf(Pair(498, 4), Pair(498, 5), Pair(498, 6), Pair(497, 6), Pair(496, 6))
        assertEquals(expected, RegolithReservoir.parsePaths(path))
    }

    @Test
    fun `when I run part 1 then I should get 24`() {
        val rr = RegolithReservoir()
        assertEquals(24, rr.run("/day-14-test-input.txt", Part1))
    }

    @Test
    fun `when I run part 2 then I should get 93`() {
        val rr = RegolithReservoir()
        assertEquals(93, rr.run("/day-14-test-input.txt", Part2))
    }
}