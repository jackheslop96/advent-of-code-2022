package day05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.Part1
import utils.Part2

internal class SupplyStacksTest {

    @Test
    fun whenGivenStacks_thenReturnListOfLists() {
        val ss = SupplyStacks()
        val input = listOf(
            "    [D]",
            "[N] [C]",
            "[Z] [M] [P]",
            " 1   2   3"
        )
        val expected = listOf(
            listOf("Z", "N"),
            listOf("M", "C", "D"),
            listOf("P")
        )
        assertEquals(expected, ss.parseInput(input))
    }

    @Test
    fun whenGivenAnInstructionToMove1Crate_thenAmendTheLists() {
        val ss = SupplyStacks()
        val stacks = listOf(
            listOf("Z", "N"),
            listOf("M", "C", "D"),
            listOf("P")
        )
        val instruction = "move 1 from 2 to 1"
        val expected = listOf(
            listOf("Z", "N", "D"),
            listOf("M", "C"),
            listOf("P")
        )
        assertEquals(expected, ss.applyInstruction(stacks, instruction, Part1))
    }

    @Test
    fun whenGivenAnInstructionToMOveMultipleCrates_thenAmendTheLists() {
        val ss = SupplyStacks()
        val stacks = listOf(
            listOf("Z", "N", "D"),
            listOf("M", "C"),
            listOf("P")
        )
        val instruction = "move 3 from 1 to 3"
        val expected = listOf(
            emptyList(),
            listOf("M", "C"),
            listOf("P", "D", "N", "Z")
        )
        assertEquals(expected, ss.applyInstruction(stacks, instruction, Part1))
    }

    @Test
    fun whenRunningPart1_thenReturnCMZ() {
        val ss = SupplyStacks()
        assertEquals("CMZ", ss.run("/day-05-test-input.txt", Part1))
    }

    @Test
    fun whenRunningPart2_thenReturnMCD() {
        val ss = SupplyStacks()
        assertEquals("MCD", ss.run("/day-05-test-input.txt", Part2))
    }

}