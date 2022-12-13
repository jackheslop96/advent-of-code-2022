package day12

import utils.FileReader
import utils.Part
import utils.Part1
import utils.Part2

typealias Grid = List<List<Char>>
typealias Vector = Pair<Int, Int>
typealias GridValue = Pair<Vector, Char>

class HillClimbingAlgorithm {

    fun run(fileName: String, part: Part): Int {
        val grid = FileReader.readFile(fileName).map { it.toList() }
        // easier to solve both parts if we start at the end and move backwards
        val (start, end) = when (part) {
            Part1 -> Pair(findGridValue(grid, 'E'), 'S')
            Part2 -> Pair(findGridValue(grid, 'E'), 'a')
        }
        val queue = mutableListOf(start)
        val paths: MutableMap<GridValue, Int> = mutableMapOf()
        paths[start] = 0

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            possibleMoves
                .mapNotNull { (current.first + it).toGridValue(grid) }
                .filter { !paths.containsKey(it) }
                .filter { current.second.canMoveTo(it.second) }
                .forEach { paths[it] = paths[current]!! + 1; queue.add(it) }
        }

        return paths
            .filter { it.key.second == end }
            .values
            .minOrNull() ?: throw Exception("Something went wrong")
    }

    companion object {
        fun Char.canMoveTo(next: Char): Boolean {
            val thisUpdated = when (this) { 'E' -> 'z'; else -> this }
            val nextUpdated = when (next) { 'S' -> 'a'; else -> next }
            return thisUpdated.code <= nextUpdated.code + 1
        }

        val possibleMoves = listOf(
            Vector(0, -1), // up
            Vector(0, 1), // down
            Vector(-1, 0), // left
            Vector(1, 0) // right
        )

        fun findGridValue(grid: Grid, char: Char): GridValue {
            fun rec(x: Int = 0, y: Int = 0): GridValue {
                return if (grid[y][x] == char) {
                    GridValue(Pair(x, y), grid[y][x])
                } else {
                    if (x + 1 == grid[y].size) {
                        rec(0, y + 1)
                    } else {
                        rec(x + 1, y)
                    }
                }
            }
            return rec()
        }

        fun Vector.toGridValue(grid: Grid): GridValue? {
            val x = this.first
            val y = this.second
            return try {
                GridValue(Vector(x, y), grid[y][x])
            } catch (e: Exception) {
                null
            }
        }

        operator fun Vector.plus(vector: Vector): Vector {
            return Vector(this.first + vector.first, this.second + vector.second)
        }
    }
}