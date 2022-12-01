package utils

object FileReader {

    fun readFile(fileName: String): List<String> {
        val resource = javaClass.getResourceAsStream(fileName)
        return resource?.bufferedReader()?.readLines() ?: throw IllegalArgumentException("File not found!")
    }
}