package day11

class Monkey(
    val index: Int,
    private val items: List<Long>,
    val operation: (Long) -> Long,
    val divisor: Int,
    val throwTo: (Boolean) -> Int,
    val itemsInspected: Long
) {

    fun test(value: Long): Boolean = value.mod(divisor) == 0

    private fun addItem(item: Long): Monkey =
        Monkey(index, items.plusElement(item), operation, divisor, throwTo, itemsInspected)

    private fun throwItem(): Monkey =
        Monkey(index, items.drop(1), operation, divisor, throwTo, itemsInspected + 1L)

    fun inspectItems(monkeys: MutableList<Monkey>, f: (Long) -> Long) {
        items.map { inspectAndThrow(it, monkeys, f) }
    }

    private fun inspectAndThrow(item: Long, monkeys: MutableList<Monkey>, f: (Long) -> Long) {
        val updatedItem = f(operation(item))
        val monkeyToThrowTo = throwTo(test(updatedItem))
        monkeys[index] = monkeys[index].throwItem()
        monkeys[monkeyToThrowTo] = monkeys[monkeyToThrowTo].addItem(updatedItem)
    }

    companion object {
        fun apply(input: List<String>): Monkey {
            return Monkey(
                parseIndex(input[0]),
                parseStartingItems(input[1]),
                parseOperation(input[2]),
                parseDivisor(input[3]),
                parseThrowTo(input.subList(4, 6)),
                0
            )
        }

        fun parseIndex(input: String): Int {
            val regex = """Monkey (.*):""".toRegex()
            val (index) = regex.find(input)!!.destructured
            return index.toInt()
        }

        fun parseStartingItems(input: String): List<Long> {
            val regex = """Starting items: (.*)""".toRegex()
            val (items) = regex.find(input)!!.destructured
            return items.split(", ").map { it.toLong() }
        }

        fun parseOperation(input: String): (Long) -> Long {
            val regex = """Operation: new = old (.) ([\d]*)""".toRegex()
            val (operation, amount) = regex.find(input)!!.destructured
            val value: (Long) -> Long = { x -> amount.toLongOrNull() ?: x }
            return when (operation) {
                "*" -> { x -> x * value(x) }
                "+" -> { x -> x + value(x) }
                "-" -> { x -> x - value(x) }
                "/" -> { x -> x / value(x) }
                else -> throw Exception("Invalid operation $operation")
            }
        }

        fun parseDivisor(input: String): Int {
            val regex = """Test: divisible by (.*)""".toRegex()
            val (arg) = regex.find(input)!!.destructured
            return arg.toInt()
        }

        fun parseThrowTo(input: List<String>): (Boolean) -> Int {
            val regex1 = """If true: throw to monkey (.*)""".toRegex()
            val (arg1) = regex1.find(input[0])!!.destructured

            val regex2 = """If false: throw to monkey (.*)""".toRegex()
            val (arg2) = regex2.find(input[1])!!.destructured

            return { if (it) arg1.toInt() else arg2.toInt() }
        }
    }
}