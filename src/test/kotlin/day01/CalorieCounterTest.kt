package day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CalorieCounterTest {

    @Test
    fun whenListHasBlocksOfValues_thenReturnTotalOfEachBlock() {
        val calorieCounter = CalorieCounter()
        val input = listOf(
            "1000",
            "2000",
            "3000",
            "",
            "4000",
            "",
            "5000",
            "6000",
            "",
            "7000",
            "8000",
            "9000",
            "",
            "10000"
        )

        val expected = listOf(
            6000,
            4000,
            11000,
            24000,
            10000
        )
        assertEquals(expected, calorieCounter.calculateTotals(input))
    }

    @Test
    fun whenListOfValues_thenReturnHighestValue() {
        val calorieCounter = CalorieCounter()
        val input = listOf(
            6000,
            4000,
            11000,
            24000,
            10000
        )
        assertEquals(24000, calorieCounter.findHighestN(input, 1))
    }

    @Test
    fun whenListOfValues_thenReturnSumOf3HighestValues() {
        val calorieCounter = CalorieCounter()
        val input = listOf(
            6000,
            4000,
            11000,
            24000,
            10000
        )
        assertEquals(45000, calorieCounter.findHighestN(input, 3))
    }
}