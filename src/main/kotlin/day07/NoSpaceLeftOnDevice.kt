package day07

import utils.FileReader
import utils.Part
import utils.Part1
import utils.Part2

class NoSpaceLeftOnDevice {

    fun run(fileName: String, part: Part): Int {
        val ds = directorySizes(fileName)
        return when (part) {
            Part1 -> ds.filter { it <= 100000 }.sum()
            Part2 -> {
                val usedSpace = ds.last()
                val unusedSpace = 70000000 - usedSpace
                val required = 30000000 - unusedSpace
                ds.find { it >= required } ?: throw Exception("No directory big enough")
            }
        }
    }

    private val dirRegex = """^dir (.*)$""".toRegex()
    private val fileRegex = """^(\d*) (.*)$""".toRegex()
    private val cdRegex = """^\$ cd ([A-Za-z0-9]*)$""".toRegex()
    private val moveOutOneLevel = "$ cd .."

    fun directorySizes(fileName: String): List<Int> {
        fun rec(map: Map<*, *>): List<Map<*, *>> {
            val innerMaps = map.values.flatMap { x ->
                when (x) {
                    is Map<*, *> -> rec(x)
                    else -> emptyList()
                }
            }
            return listOf(map) + innerMaps
        }

        return rec(buildMap(fileName)).map { directorySize(it) }.sorted()
    }

    fun directorySize(mapEntry: Any?): Int {
        return when (mapEntry) {
            is Int -> mapEntry
            is Map<*, *> -> mapEntry.values.sumOf { directorySize(it) }
            else -> throw Exception("$mapEntry has an unexpected type")
        }
    }

    class Acc(val commands: List<String>, val directoryMap: Map<*, *>)

    fun buildMap(fileName: String): Map<*, *> {
        fun rec(acc: Acc): Acc {
            return when (acc.commands) {
                emptyList<String>() -> acc
                else -> {
                    val command = acc.commands.first()
                    val updatedCommands = acc.commands.drop(1)
                    if (command == moveOutOneLevel) {
                        acc
                    } else if (command.matches(cdRegex)) {
                        val (dir) = cdRegex.find(command)!!.destructured
                        when (val directory = acc.directoryMap[dir]) {
                            is Map<*, *> -> {
                                val innerAcc = rec(Acc(updatedCommands, directory))
                                val innerMap = mapOf(dir to innerAcc.directoryMap)
                                rec(Acc(innerAcc.commands.drop(1), acc.directoryMap.plus(innerMap)))
                            }
                            else -> throw Exception("Directory $dir is not a map")
                        }
                    } else {
                        rec(Acc(updatedCommands, executeCommand(command, acc.directoryMap)))
                    }
                }
            }
        }

        val commands = FileReader.readFile(fileName)
        return rec(Acc(commands, emptyMap<String, Any>())).directoryMap
    }

    fun executeCommand(command: String, map: Map<*, *>): Map<*, *> {
        return if (command.matches(dirRegex)) {
            val (dir) = dirRegex.find(command)!!.destructured
            map.plus(mapOf(dir to emptyMap<String, Any>()))
        } else if (command.matches(fileRegex)) {
            val (size, file) = fileRegex.find(command)!!.destructured
            map.plus(file to size.toInt())
        } else {
            map
        }
    }
}