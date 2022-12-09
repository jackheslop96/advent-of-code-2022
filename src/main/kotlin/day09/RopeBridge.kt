package day09

import utils.FileReader
import utils.Part
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

typealias Coordinate = Pair<Int, Int>

class RopeBridge {

    fun run(fileName: String, part: Part): Int {
        fun rec(instructions: List<Coordinate>, head: Head, tail: Tail): Tail {
            return when (instructions) {
                emptyList<Coordinate>() -> tail
                else -> {
                    val updatedHead = head.update(instructions.first())
                    val updatedTail = tail.next(updatedHead)
                    rec(instructions.drop(1), updatedHead, updatedTail)
                }
            }
        }

        val instructions = FileReader.readFile(fileName).flatMap { expandInstruction(it) }
        val tail = rec(instructions, Head.apply(), Tail.apply())
        println("***")
        return tail.visitedPositions.toList().size
    }

    sealed interface RopeEnd {
        val position: Coordinate
    }

    class Head(override val position: Coordinate) : RopeEnd {

        fun update(coordinate: Coordinate): Head {
            val updatedPosition = Pair(position.first + coordinate.first, position.second + coordinate.second)
            return Head(updatedPosition)
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

    class Tail(override val position: Coordinate, val visitedPositions: Sequence<Coordinate>) : RopeEnd {

        private fun updateVisitedPositions(position: Coordinate): Sequence<Coordinate> {
            return if (visitedPositions.contains(position)) visitedPositions else visitedPositions.plusElement(position)
        }

        private fun update(coordinate: Coordinate): Tail {
            val updatedPosition = Pair(position.first + coordinate.first, position.second + coordinate.second)
            return Tail(updatedPosition, updateVisitedPositions(updatedPosition))
        }

        fun next(head: Head): Tail {

            val threshold: Double = sqrt(2.0)

            fun distanceToHead(tail: Tail): Double {
                val a = abs(head.position.first.toDouble() - tail.position.first.toDouble())
                val b = abs(head.position.second.toDouble() - tail.position.second.toDouble())
                return sqrt(a.pow(2) + b.pow(2))
            }

            return if (distanceToHead(this) <= threshold) {
                this
            } else {
                val tails = possibleMoves.fold(emptyList<Pair<Tail, Double>>()) { acc, move ->
                    val updatedTail = this.update(move)
                    acc.plusElement(Pair(updatedTail, distanceToHead(updatedTail)))
                }

                return tails.minByOrNull { it.second }?.first ?: throw Exception("No tails to choose from")
            }
        }

        companion object {
            fun apply(): Tail {
                return apply(Pair(0, 0))
            }

            fun apply(position: Coordinate): Tail {
                return Tail(position, sequenceOf(position))
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

        fun expandInstruction(instruction: String): List<Coordinate> {
            val regex = """([A-Z]) ([\d]*)""".toRegex()
            val (direction, amount) = regex.find(instruction)!!.destructured
            return when (direction) {
                "R" -> List(amount.toInt()) { Pair(1, 0) }
                "U" -> List(amount.toInt()) { Pair(0, 1) }
                "L" -> List(amount.toInt()) { Pair(-1, 0) }
                "D" -> List(amount.toInt()) { Pair(0, -1) }
                else -> throw Exception("$direction was not recognised as a valid direction")
            }
        }
    }
}