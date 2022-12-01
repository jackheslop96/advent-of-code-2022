import day01.CalorieCounter

fun main(args : Array<String>) {

    // day 1
    val f1 = "/day-01-input.txt"
    println("Day 1 Part 1: ${CalorieCounter().run(f1, 1)}")
    println("Day 1 Part 2 ${CalorieCounter().run(f1, 3)}")
}
