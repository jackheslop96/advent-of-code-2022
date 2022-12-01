package day01

import utils.FileReader

class CalorieCounter {

    fun run(fileName: String, takeN: Int): Int {
        return findHighestN(calculateTotals(FileReader.readFile(fileName)), takeN)
    }

    fun calculateTotals(calories: List<String>): List<Int> {
        fun rec(cs: List<String>, acc: List<Int>): List<Int> {
            return when (cs) {
                emptyList<String>() -> acc
                else -> {
                    val total = cs.takeWhile { it.isNotEmpty() }.sumOf { it.toInt() }
                    rec(cs.dropWhile { it.isNotEmpty() }.dropWhile { it.isEmpty() }, acc.plus(total))
                }
            }
        }
        return rec(calories, emptyList())
    }

    fun findHighestN(totals: List<Int>, n: Int): Int {
        return totals.sortedDescending().take(n).sum()
    }
}