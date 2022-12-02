package day02

import org.junit.jupiter.api.Test
import utils.Part1
import utils.Part2
import kotlin.test.assertEquals

internal class RockPaperScissorsTest {

    @Test
    fun whenIUseTheTestFile_andItIsPart1_thenIShouldGet15Points() {
        val rps = RockPaperScissors()
        assertEquals(15, rps.run("/day-02-test-input.txt", Part1))
    }

    @Test
    fun whenIUseTheTestFile_andItIsPart2_thenIShouldGet12Points() {
        val rps = RockPaperScissors()
        assertEquals(12, rps.run("/day-02-test-input.txt", Part2))
    }
}