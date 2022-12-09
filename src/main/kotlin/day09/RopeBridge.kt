package day09

import utils.FileReader
import utils.Part
import kotlin.math.abs
import kotlin.math.pow

typealias Coordinate = Pair<Int, Int>

// need a list of knots
// each knot follows the knot at the previous index

class RopeBridge {

    fun run(fileName: String, part: Part): Int {
        fun rec(instructions: List<String>, rope: Rope): Tail {
            return when (instructions) {
                emptyList<Coordinate>() -> rope.tail
                else -> {
                    val instruction = instructions.first()
                    val regex = """([A-Z]) ([\d]*)""".toRegex()
                    val (a, b) = regex.find(instruction)!!.destructured
                    val move = parseDirection(a)
                    val amount = b.toInt()
                    val updatedRope = (1..amount).fold(rope) { acc, _ ->
                        acc.updateHeadAndTail(move)
                    }
                    rec(instructions.drop(1), updatedRope)
                }
            }
        }

        val instructions = FileReader.readFile(fileName)
        val tail = rec(instructions, Rope.apply())
        return tail.visitedPositions.size
    }

    class Rope(val head: Head, val tail: Tail) {

        fun updateHeadAndTail(move: Coordinate): Rope {
            val updatedHead = head.update(move)
            return Rope(updatedHead, tail.next(updatedHead))
        }

        companion object {
            fun apply(): Rope = Rope(Head.apply(), Tail.apply())
        }
    }

    sealed interface RopeEnd {
        val position: Coordinate

        fun updatePosition(move: Coordinate): Coordinate {
            return Pair(position.first + move.first, position.second + move.second)
        }
    }

    class Head(override val position: Coordinate) : RopeEnd {

        fun update(move: Coordinate): Head {
            return Head(updatePosition(move))
        }

        companion object {
            fun apply(): Head {
                return apply(Pair(0, 0))
            }

            fun apply(position: Coordinate): Head {
                return Head(position)
            }
        }
    }

    class Tail(override val position: Coordinate, val visitedPositions: Set<Coordinate>) : RopeEnd {

        private fun updateVisitedPositions(position: Coordinate): Set<Coordinate> {
            return visitedPositions.plusElement(position)
        }

        private fun update(move: Coordinate): Tail {
            val updatedPosition = updatePosition(move)
            return Tail(updatedPosition, updateVisitedPositions(updatedPosition))
        }

        fun next(head: Head): Tail {

            fun distanceToHeadSquared(position: Coordinate): Double {
                val a = abs(head.position.first - position.first).toDouble()
                val b = abs(head.position.second - position.second).toDouble()
                return a.pow(2) + b.pow(2)
            }

            return if (distanceToHeadSquared(position) <= 2.0) {
                this
            } else {
                val move = possibleMoves.minByOrNull { distanceToHeadSquared(updatePosition(it)) } ?: throw Exception("No moves to choose from")
                return update(move)
            }
        }

        companion object {
            fun apply(): Tail {
                return apply(Pair(0, 0))
            }

            fun apply(position: Coordinate): Tail {
                return Tail(position, setOf(position))
            }
        }
    }

    companion object {

        val possibleMoves: List<Coordinate> =
            (-1..1).fold(emptyList()) { outerAcc, x ->
                (-1..1).fold(outerAcc) { innerAcc, y ->
                    innerAcc.plusElement(Pair(x, y))
                }
            }

        fun parseDirection(direction: String): Coordinate {
            return when (direction) {
                "R" -> Pair(1, 0)
                "U" -> Pair(0, 1)
                "L" -> Pair(-1, 0)
                "D" -> Pair(0, -1)
                else -> throw Exception("$direction was not recognised as a valid direction")
            }
        }
    }
}