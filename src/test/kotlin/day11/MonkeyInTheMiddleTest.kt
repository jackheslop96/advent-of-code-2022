package day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.Part1
import utils.Part2

internal class MonkeyInTheMiddleTest {

    @Test
    fun `must parse index`() {
        val input = "Monkey 0:"
        val result = Monkey.parseIndex(input)
        assertEquals(0, result)
    }

    @Test
    fun `must parse operation when multiplication`() {
        val input = "Operation: new = old * 19"
        val result = Monkey.parseOperation(input)
        val expected: (Long) -> Long = { it * 19 }
        assertEquals(expected(1), result(1))
        assertEquals(expected(2), result(2))
        assertEquals(expected(10), result(10))
    }

    @Test
    fun `must parse operation when addition`() {
        val input = "Operation: new = old + 6"
        val result = Monkey.parseOperation(input)
        val expected: (Long) -> Long = { it + 6 }
        assertEquals(expected(1), result(1))
        assertEquals(expected(2), result(2))
        assertEquals(expected(10), result(10))
    }

    @Test
    fun `must parse operation when subtraction`() {
        val input = "Operation: new = old - 5"
        val result = Monkey.parseOperation(input)
        val expected: (Long) -> Long = { it - 5 }
        assertEquals(expected(1), result(1))
        assertEquals(expected(2), result(2))
        assertEquals(expected(10), result(10))
    }

    @Test
    fun `must parse operation when division`() {
        val input = "Operation: new = old / 7"
        val result = Monkey.parseOperation(input)
        val expected: (Long) -> Long = { it / 7 }
        assertEquals(expected(1), result(1))
        assertEquals(expected(2), result(2))
        assertEquals(expected(10), result(10))
    }

    @Test
    fun `must parse operation when squaring`() {
        val input = "Operation: new = old * old"
        val result = Monkey.parseOperation(input)
        val expected: (Long) -> Long = { it * it }
        assertEquals(expected(1), result(1))
        assertEquals(expected(2), result(2))
        assertEquals(expected(10), result(10))
    }

    @Test
    fun `must parse divisor`() {
        val input = "Test: divisible by 23"
        val result = Monkey.parseDivisor(input)
        assertEquals(23, result)
    }

    @Test
    fun `must parse throw to`() {
        val input = listOf(
            "If true: throw to monkey 2",
            "If false: throw to monkey 3"
        )
        val result = Monkey.parseThrowTo(input)
        val expected: (Boolean) -> Int = { if (it) 2 else 3 }
        assertEquals(expected(true), result(true))
        assertEquals(expected(false), result(false))
    }

    @Test
    fun `must parse each monkey input as a monkey`() {
        val input = listOf(
            "Monkey 0:",
            "  Starting items: 79, 98",
            "  Operation: new = old * 19",
            "  Test: divisible by 23",
            "    If true: throw to monkey 2",
            "    If false: throw to monkey 3"
        )

        val result = Monkey.apply(input)

        val expectedOperation: (Long) -> Long = { it * 19 }

        assertEquals(0, result.index)

        assertEquals(expectedOperation(1), result.operation(1))
        assertEquals(expectedOperation(2), result.operation(2))
        assertEquals(expectedOperation(10), result.operation(10))

        assertEquals(23, result.divisor)

        assertEquals(0, result.itemsInspected)
    }

    @Test
    fun `when running part 1 must return 10605`() {
        val mitm = MonkeyInTheMiddle()
        assertEquals(10605, mitm.run("/day-11-test-input.txt", Part1))
    }

    @Test
    fun `when running part 2 must return 2713310158`() {
        val mitm = MonkeyInTheMiddle()
        assertEquals(2713310158, mitm.run("/day-11-test-input.txt", Part2))
    }
}