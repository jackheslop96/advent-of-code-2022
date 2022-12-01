package day01

import utils.FileReader

class CalorieCounter {

    fun run(fileName: String, takeN: Int): Int {
        return findHighestN(calculateTotals(FileReader.readFile(fileName)), takeN)
    }

    fun calculateTotals(calories: List<String>): List<Int> {
        val totals = mutableListOf(0)
        for (calorie in calories) {
            if (calorie == "") {
                totals.add(0)
            } else {
                totals[totals.lastIndex] = totals.last() + calorie.toInt()
            }
        }
        return totals
    }

    fun findHighestN(totals: List<Int>, n: Int): Int {
        return totals.sortedDescending().take(n).sum()
    }
}