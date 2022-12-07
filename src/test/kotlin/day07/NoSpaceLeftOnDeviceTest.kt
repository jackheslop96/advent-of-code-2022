package day07

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import utils.Part1
import utils.Part2

internal class NoSpaceLeftOnDeviceTest {

    @Test
    fun whenGivenTestInput_thenFindDirectorySizes() {
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        val expected = listOf(584, 94853, 24933642, 48381165)
        assertEquals(expected, noSpaceLeftOnDevice.directorySizes("/day-07-test-input.txt"))
    }

    @Test
    fun whenGivenADirectory_thenAddDirectoryToMap() {
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        val command = "dir a"
        val expected = mapOf("a" to emptyMap<String, Any>())
        assertEquals(expected, noSpaceLeftOnDevice.executeCommand(command, emptyMap<String, Any>()))
    }

    @Test
    fun whenGivenAFile_thenAddFileToDirectoryMap() {
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        val command = "14848514 b.txt"
        val expected = mapOf("b.txt" to 14848514)
        assertEquals(expected, noSpaceLeftOnDevice.executeCommand(command, emptyMap<String, Any>()))
    }

    @Test
    fun whenGivenAFile_thenReturnAMap() {
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        val expected: Map<String, Any> = mapOf(
            "a" to mapOf(
                "e" to mapOf(
                    "i" to 584
                ),
                "f" to 29116,
                "g" to 2557,
                "h.lst" to 62596
            ),
            "b.txt" to 14848514,
            "c.dat" to 8504156,
            "d" to mapOf(
                "j" to 4060174,
                "d.log" to 8033020,
                "d.ext" to 5626152,
                "k" to 7214296
            )
        )
        assertEquals(expected, noSpaceLeftOnDevice.buildMap("/day-07-test-input.txt"))
    }

    @Test
    fun whenGivenDirectoryWithOneFile_returnFileSize() {
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        val mapEntry = 584
        assertEquals(584, noSpaceLeftOnDevice.directorySize(mapEntry))
    }

    @Test
    fun whenGivenDirectoryWithNestedDirectory_returnDirectorySize() {
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        val mapEntry = mapOf(
            "i" to 584
        )
        assertEquals(584, noSpaceLeftOnDevice.directorySize(mapEntry))
    }

    @Test
    fun whenGivenDirectoryWithNestedDirectoryAndFiles_returnDirectorySize() {
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        val mapEntry = mapOf(
            "e" to mapOf(
                "i" to 584
            ),
            "f" to 29116,
            "g" to 2557,
            "h.lst" to 62596
        )
        assertEquals(94853, noSpaceLeftOnDevice.directorySize(mapEntry))
    }

    @Test
    fun whenIRunPart1_thenIShouldGet95437() {
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        assertEquals(95437, noSpaceLeftOnDevice.run("/day-07-test-input.txt", Part1))
    }

    @Test
    fun whenIRunPart2_thenIShouldGet24933642() {
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        assertEquals(24933642, noSpaceLeftOnDevice.run("/day-07-test-input.txt", Part2))
    }
}