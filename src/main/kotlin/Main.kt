import day01.CalorieCounter
import day02.RockPaperScissors
import day03.RucksackReorganisation
import day04.CampCleanup
import utils.Part1
import utils.Part2

fun main(args : Array<String>) {

    // day 1
    val f1 = "/day-01-input.txt"
    println("Day 1 Part 1: ${CalorieCounter().run(f1, 1)}")
    println("Day 1 Part 2 ${CalorieCounter().run(f1, 3)}")

    // day 2
    val f2 = "/day-02-input.txt"
    println("Day 2 Part 1: ${RockPaperScissors().run(f2, Part1)}")
    println("Day 2 Part 2: ${RockPaperScissors().run(f2, Part2)}")

    // day 3
    val f3 = "/day-03-input.txt"
    println("Day 3 Part 1: ${RucksackReorganisation().run(f3, Part1)}")
    println("Day 3 Part 2: ${RucksackReorganisation().run(f3, Part2)}")

    // day 4
    val f4 = "/day-04-input.txt"
    println("Day 4 Part 1: ${CampCleanup().run(f4, Part1)}")
    println("Day 4 Part 2: ${CampCleanup().run(f4, Part2)}")
}
