package utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class FileReaderTest {

    @Test
    fun whenValidFile_thenReturnLinesAsListOfStrings() {
        val expected = listOf(
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
        assertEquals(expected, FileReader.readFile("/day-01-test-input.txt"))
    }

    @Test
    fun whenInvalidFile_thenThrowException() {
        assertFailsWith<IllegalArgumentException> {
            FileReader.readFile("foo")
        }
    }
}