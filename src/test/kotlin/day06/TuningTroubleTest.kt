package day06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.Part1
import utils.Part2
import kotlin.test.assertFailsWith

internal class TuningTroubleTest {

    @Test
    fun whenGivenTestInput_andPart1_thenReturn5() {
        val tt = TuningTrouble()
        assertEquals(7, tt.run("/day-06-test-input.txt", Part1))
    }

    @Test
    fun whenGivenbvwbjplbgvbhsrlpgdmjqwftvncz_thenReturn5() {
        val tt = TuningTrouble()
        assertEquals(5, tt.findMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", Part1))
    }

    @Test
    fun whenGivenzcfzfwzzqfrljwzlrfnpqdbhtmscgvjw_thenReturn11() {
        val tt = TuningTrouble()
        assertEquals(11, tt.findMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", Part1))
    }

    @Test
    fun whenGivenDataStreamWithNoMarker_thenThrowException() {
        val tt = TuningTrouble()
        assertFailsWith<IllegalArgumentException> {
            tt.findMarker("aaaaaaaaaa", Part1)
        }
    }

    @Test
    fun whenGivenTestInput_andPart2_thenReturn19() {
        val tt = TuningTrouble()
        assertEquals(19, tt.run("/day-06-test-input.txt", Part2))
    }

    @Test
    fun whenGivenbvwbjplbgvbhsrlpgdmjqwftvncz_thenReturn23() {
        val tt = TuningTrouble()
        assertEquals(23, tt.findMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", Part2))
    }
}