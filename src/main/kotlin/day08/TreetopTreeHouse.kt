package day08

import utils.FileReader
import utils.Part
import utils.Part1
import utils.Part2

class TreetopTreeHouse {

    fun run(fileName: String, part: Part): Int {
        val trees = FileReader
            .readFile(fileName)
            .map { it.map { char -> char.toString().toInt() } }

        val coordinates = trees.map { it.withIndex() }.withIndex()
            .flatMap { row ->
                row.value.map { column ->
                    Pair(column.index, row.index)
                }
            }

        return when (part) {
            Part1 -> coordinates.count { isTreeVisible(trees, it) }
            Part2 -> coordinates.maxOf { scenicScore(trees, it) }
        }
    }

    fun isTreeVisible(trees: List<List<Int>>, coordinate: Pair<Int, Int>): Boolean {
        val row = trees[coordinate.second]
        val maxX = row.size
        val maxY = trees.size
        val tree = row[coordinate.first]

        fun isTreeVisibleFromTop(): Boolean {
            val x = coordinate.first
            val range = 0 until coordinate.second
            return range.fold(true) { acc, y ->
                acc && trees[y][x] < tree
            }
        }

        fun isTreeVisibleFromRight(): Boolean {
            val y = coordinate.second
            val range = (coordinate.first + 1) until maxX
            return range.fold(true) { acc, x ->
                acc && trees[y][x] < tree
            }
        }

        fun isTreeVisibleFromLeft(): Boolean {
            val y = coordinate.second
            val range = 0 until coordinate.first
            return range.fold(true) { acc, x ->
                acc && trees[y][x] < tree
            }
        }

        fun isTreeVisibleFromBottom(): Boolean {
            val x = coordinate.first
            val range = (coordinate.second + 1) until maxY
            return range.fold(true) { acc, y ->
                acc && trees[y][x] < tree
            }
        }

        return isTreeVisibleFromTop() || isTreeVisibleFromRight() || isTreeVisibleFromLeft() || isTreeVisibleFromBottom()
    }

    fun scenicScore(trees: List<List<Int>>, coordinate: Pair<Int, Int>): Int {
        val row = trees[coordinate.second]
        val maxX = row.size
        val maxY = trees.size
        val tree = row[coordinate.first]

        fun lookingUp(): Int {
            val x = coordinate.first
            fun rec(y: Int, acc: Int = 0): Int {
                return if (y < 0) {
                    acc
                } else {
                    if (trees[y][x] < tree) {
                        rec(y - 1, acc + 1)
                    } else {
                        acc + 1
                    }
                }
            }
            return rec(coordinate.second - 1)
        }

        fun lookingDown(): Int {
            val x = coordinate.first
            fun rec(y: Int, acc: Int = 0): Int {
                return if (y >= maxY) {
                    acc
                } else {
                    if (trees[y][x] < tree) {
                        rec(y + 1, acc + 1)
                    } else {
                        acc + 1
                    }
                }
            }
            return rec(coordinate.second + 1)
        }

        fun lookingLeft(): Int {
            val y = coordinate.second
            fun rec(x: Int, acc: Int = 0): Int {
                return if (x < 0) {
                    acc
                } else {
                    if (trees[y][x] < tree) {
                        rec(x - 1, acc + 1)
                    } else {
                        acc + 1
                    }
                }
            }
            return rec(coordinate.first - 1)
        }

        fun lookingRight(): Int {
            val y = coordinate.second
            fun rec(x: Int, acc: Int = 0): Int {
                return if (x >= maxX) {
                    acc
                } else {
                    if (trees[y][x] < tree) {
                        rec(x + 1, acc + 1)
                    } else {
                        acc + 1
                    }
                }
            }
            return rec(coordinate.first + 1)
        }

        return lookingUp() * lookingDown() * lookingLeft() * lookingRight()
    }
}