import day01.CalorieCounter
import day02.RockPaperScissors
import day03.RucksackReorganisation
import day04.CampCleanup
import day05.SupplyStacks
import day06.TuningTrouble
import day07.NoSpaceLeftOnDevice
import day08.TreetopTreeHouse
import day09.RopeBridge
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

    // day 5
    val f5 = "/day-05-input.txt"
    println("Day 5 Part 1: ${SupplyStacks().run(f5, Part1)}")
    println("Day 5 Part 2: ${SupplyStacks().run(f5, Part2)}")

    // day 6
    val f6 = "/day-06-input.txt"
    println("Day 6 Part 1: ${TuningTrouble().run(f6, Part1)}")
    println("Day 6 Part 2: ${TuningTrouble().run(f6, Part2)}")

    // day 7
    val f7 = "/day-07-input.txt"
    println("Day 7 Part 1: ${NoSpaceLeftOnDevice().run(f7, Part1)}")
    println("Day 7 Part 2: ${NoSpaceLeftOnDevice().run(f7, Part2)}")

    // day 8
    val f8 = "/day-08-input.txt"
    println("Day 8 Part 1: ${TreetopTreeHouse().run(f8, Part1)}")
    println("Day 8 Part 2: ${TreetopTreeHouse().run(f8, Part2)}")

    // day 9
    val f9 = "/day-09-input.txt"
    println("Day 9 Part 1: ${RopeBridge().run(f9, Part1)}")
    println("Day 9 Part 2: ${RopeBridge().run(f9, Part2)}")
}
