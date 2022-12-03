package day03

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.Part1
import utils.Part2

internal class RucksackReorganisationTest {

    @Test
    fun whenIHaveAnInputLine_thenSplitTheLineIntoTwoCompartments() {
        val input = "vJrwpWtwJgWrhcsFMMfFFhFp"
        val rucksack = RucksackReorganisation.Rucksack(input)
        assertEquals("vJrwpWtwJgWr", rucksack.compartment1)
        assertEquals("hcsFMMfFFhFp", rucksack.compartment2)
    }

    @Test
    fun whenIHaveARucksack_thenFindItemsInBothCompartments() {
        val rucksack = RucksackReorganisation.Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp")
        assertEquals(setOf('p'), rucksack.itemsInBothCompartments)
    }

    @Test
    fun whenIHaveLowercaseA_thenReturn1() {
        val ro = RucksackReorganisation()
        assertEquals(1, ro.charToInt('a'))
    }

    @Test
    fun whenIHaveLowercaseB_thenReturn2() {
        val ro = RucksackReorganisation()
        assertEquals(2, ro.charToInt('b'))
    }

    @Test
    fun whenIHaveLowercaseZ_thenReturn26() {
        val ro = RucksackReorganisation()
        assertEquals(26, ro.charToInt('z'))
    }

    @Test
    fun whenIHaveUppercaseA_thenReturn27() {
        val ro = RucksackReorganisation()
        assertEquals(27, ro.charToInt('A'))
    }

    @Test
    fun whenIHaveUppercaseB_thenReturn28() {
        val ro = RucksackReorganisation()
        assertEquals(28, ro.charToInt('B'))
    }

    @Test
    fun whenIHaveUppercaseZ_thenReturn52() {
        val ro = RucksackReorganisation()
        assertEquals(52, ro.charToInt('Z'))
    }

    @Test
    fun whenIUseTheTestFile_andItIsPart1_thenIShouldGet157() {
        val ro = RucksackReorganisation()
        assertEquals(157, ro.run("/day-03-test-input.txt", Part1))
    }

    @Test
    fun whenIHaveTheFirst3Rucksacks_thenReturnLowercaseR() {
        val ro = RucksackReorganisation()
        val rucksacks = listOf(
            RucksackReorganisation.Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp"),
            RucksackReorganisation.Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"),
            RucksackReorganisation.Rucksack("PmmdzqPrVvPwwTWBwg")
        )
        assertEquals(setOf('r'), ro.commonItems(rucksacks))
    }

    @Test
    fun whenIUseTheTestFile_andItIsPart2_thenIShouldGet70() {
        val ro = RucksackReorganisation()
        assertEquals(70, ro.run("/day-03-test-input.txt", Part2))
    }
}