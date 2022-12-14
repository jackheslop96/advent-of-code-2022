package day14

import utils.FileReader
import utils.Part
import utils.Part1
import utils.Part2

typealias Vector = Pair<Int, Int>

class RegolithReservoir {

    private val pourPoint = Vector(500, 0)

    fun run(fileName: String, part: Part): Int {
        val rockCoordinates = FileReader.readFile(fileName).flatMap { parsePaths(it) }.toSet()
        val floor = rockCoordinates.maxOf { it.second } + 2
        tailrec fun rec(current: Vector, acc: Set<Vector>): Set<Vector> {
            val downOne = Vector(current.first, current.second + 1)
            val downOneLeftOne = Vector(current.first - 1, current.second + 1)
            val downOneRightOne = Vector(current.first + 1, current.second + 1)

            fun isInVoid(): Boolean = acc.find { it.first == current.first && it.second > current.second } == null

            fun isBlocked() = acc.contains(pourPoint)

            fun isFinished(): Boolean = when (part) {
                Part1 -> isInVoid() || isBlocked()
                Part2 -> isBlocked()
            }

            fun isOnFloor(): Boolean = when (part) {
                Part1 -> false
                Part2 -> current.second == floor - 1
            }

            return if (isOnFloor()) {
                rec(pourPoint, acc.plusElement(current))
            } else {
                if (isFinished()) {
                    acc
                } else {
                    if (!acc.contains(downOne)) {
                        rec(downOne, acc)
                    } else if (!acc.contains(downOneLeftOne)) {
                        rec(downOneLeftOne, acc)
                    } else if (!acc.contains(downOneRightOne)) {
                        rec(downOneRightOne, acc)
                    } else {
                        rec(pourPoint, acc.plusElement(current))
                    }
                }
            }
        }
        return rec(pourPoint, rockCoordinates).size - rockCoordinates.size
    }

    companion object {

        fun parsePaths(paths: String): Set<Vector> {
            fun rec(pathEnds: List<String>, acc: Set<Vector> = emptySet()): Set<Vector> {
                return when {
                    pathEnds.size <= 1 -> acc
                    else -> {
                        val path = "${pathEnds[0]} -> ${pathEnds[1]}"
                        rec(pathEnds.drop(1), acc.plus(parsePath(path)))
                    }
                }
            }
            val pathEnds = paths.split(" -> ")
            return rec(pathEnds)
        }

        fun parsePath(path: String): Set<Vector> {
            val regex = """([\d]*),([\d]*) -> ([\d]*),([\d]*)""".toRegex()
            val matches = regex.find(path)!!.destructured
            val fromX = matches.component1().toInt()
            val fromY = matches.component2().toInt()
            val toX = matches.component3().toInt()
            val toY = matches.component4().toInt()
            return if (fromX == toX) {
                val minY = minOf(fromY, toY); val maxY = maxOf(fromY, toY)
                (minY..maxY).fold(emptySet()) { acc, y ->
                    acc.plusElement(Vector(fromX, y))
                }
            } else if (fromY == toY) {
                val minX = minOf(fromX, toX); val maxX = maxOf(fromX, toX)
                (minX..maxX).fold(emptySet()) { acc, x ->
                    acc.plusElement(Vector(x, fromY))
                }
            } else {
                throw Exception("Path was not in a straight line")
            }
        }
    }
}