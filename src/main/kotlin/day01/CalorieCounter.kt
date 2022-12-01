package day01

import utils.FileReader

class CalorieCounter {

    fun run(fileName: String, takeN: Int): Int {
        return findHighestN(calculateTotals(FileReader.readFile(fileName)), takeN)
    }

    fun calculateTotals(calories: List<String>): List<Int> {
        val nonEmpty: (String) -> Boolean = { it != "" }
        val empty: (String) -> Boolean = { !nonEmpty(it) }
        fun rec(cs: List<String>, acc: List<Int>): List<Int> {
            return when (cs) {
                emptyList<String>() -> acc
                else -> {
                    val total = cs.takeWhile { nonEmpty(it) }.sumOf { it.toInt() }
                    rec(cs.dropWhile { nonEmpty(it) }.dropWhile { empty(it) }, acc.plus(total))
                }
            }
        }
        return rec(calories, emptyList())
    }

    fun findHighestN(totals: List<Int>, n: Int): Int {
        return totals.sortedDescending().take(n).sum()
    }
}