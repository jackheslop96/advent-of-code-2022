package day09

import utils.FileReader
import utils.Part
import utils.Part1
import utils.Part2
import kotlin.math.abs
import kotlin.math.pow

typealias Coordinate = Pair<Int, Int>

class RopeBridge {

    fun run(fileName: String, part: Part): Int {
        fun rec(instructions: List<String>, rope: Rope): Rope {
            return when (instructions) {
                emptyList<Coordinate>() -> rope
                else -> {
                    val instruction = instructions.first()
                    val regex = """([A-Z]) ([\d]*)""".toRegex()
                    val (a, b) = regex.find(instruction)!!.destructured
                    val move = parseDirection(a)
                    val amount = b.toInt()
                    val updatedRope = (1..amount).fold(rope) { acc, _ ->
                        acc.updateKnots(move)
                    }
                    rec(instructions.drop(1), updatedRope)
                }
            }
        }

        val numberOfKnots = when (part) { Part1 -> 2; Part2 -> 10 }
        val instructions = FileReader.readFile(fileName)
        val rope = rec(instructions, Rope.apply(numberOfKnots))
        return rope.knots.last().visitedPositions.size
    }

    class Rope(val knots: List<Knot>) {

        fun updateKnots(move: Coordinate): Rope {
            fun rec(knots: List<Knot>, acc: List<Knot>): List<Knot> {
                return when (knots) {
                    emptyList<Knot>() -> acc
                    else -> {
                        val knot = knots.first()
                        val previousKnot = acc.lastOrNull()
                        val updatedKnot = if (previousKnot == null) knot.update(move) else knot.next(previousKnot)
                        rec(knots.drop(1), acc.plusElement(updatedKnot))
                    }
                }
            }
            return Rope(rec(knots, emptyList()))
        }

        companion object {
            fun apply(numberOfKnots: Int): Rope = Rope(List(numberOfKnots) { Knot.apply() })
        }
    }

    class Knot(val position: Coordinate, val visitedPositions: Set<Coordinate>) {

        private fun updateVisitedPositions(position: Coordinate): Set<Coordinate> {
            return visitedPositions.plusElement(position)
        }

        private fun updatePosition(move: Coordinate): Coordinate {
            return Pair(position.first + move.first, position.second + move.second)
        }

        fun update(move: Coordinate): Knot {
            val updatedPosition = updatePosition(move)
            return Knot(updatedPosition, updateVisitedPositions(updatedPosition))
        }

        fun next(previousKnot: Knot): Knot {

            fun distanceToPreviousKnotSquared(position: Coordinate): Double {
                val a = abs(previousKnot.position.first - position.first).toDouble()
                val b = abs(previousKnot.position.second - position.second).toDouble()
                return a.pow(2) + b.pow(2)
            }

            return if (distanceToPreviousKnotSquared(position) <= 2.0) {
                this
            } else {
                val move = possibleMoves.minByOrNull { distanceToPreviousKnotSquared(updatePosition(it)) } ?: throw Exception("No moves to choose from")
                return update(move)
            }
        }

        companion object {
            fun apply(): Knot {
                return apply(Pair(0, 0))
            }

            fun apply(position: Coordinate): Knot {
                return Knot(position, setOf(position))
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