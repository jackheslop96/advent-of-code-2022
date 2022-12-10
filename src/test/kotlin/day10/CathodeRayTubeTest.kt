package day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CathodeRayTubeTest {

    @Test
    fun whenIRunPart1_thenReturn13140() {
        val crt = CathodeRayTube()
        assertEquals(13140, crt.part1("/day-10-test-input.txt"))
    }

    @Test
    fun whenIExecuteNoopInstruction_thenAdd1ToCycles() {
        val cc = CathodeRayTube.ClockCircuit.apply()
        val result = cc.execute("noop")
        assertEquals(listOf(1, 1), result.cycles)
    }

    @Test
    fun whenIExecuteAddxInstruction_withAPositiveNumber_thenUpdateRegisterAndAdd2ToCycles() {
        val cc = CathodeRayTube.ClockCircuit.apply()
        val result = cc.execute("addx 15")
        assertEquals(listOf(1, 1, 16), result.cycles)
    }

    @Test
    fun whenIExecuteAddxInstruction_withANegativeNumber_thenUpdateRegisterAndAdd2ToCycles() {
        val cc = CathodeRayTube.ClockCircuit.apply()
        val result = cc.execute("addx -11")
        assertEquals(listOf(1, 1, -10), result.cycles)
    }

    @Test
    fun whenIExecuteInstructionList_thenUpdateCycles() {
        val crt = CathodeRayTube()
        val instructions = listOf(
            "noop",
            "addx 3",
            "addx -5",
        )
        val expected = listOf(1, 1, 1, 4, 4, -1)
        assertEquals(expected, crt.executeInstructions(instructions).cycles)
    }

    @Test
    fun whenIRunPart2_thenReturnPixelLayout() {
        val crt = CathodeRayTube()
        val expected = listOf(
            "##..##..##..##..##..##..##..##..##..##..",
            "###...###...###...###...###...###...###.",
            "####....####....####....####....####....",
            "#####.....#####.....#####.....#####.....",
            "######......######......######......####",
            "#######.......#######.......#######....."
        )
        assertEquals(expected, crt.part2("/day-10-test-input.txt"))
    }
}