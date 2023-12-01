fun main(args: Array<String>) {
    val r = Regex("(\\d)")
    val r2 = Regex("(?=(\\d|one|two|three|four|five|six|seven|eight|nine))")

    val map = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
    val ans = Input(1).lines.map { r.findAll(it).map { x -> x.groupValues[1].toInt() } }.sumOf { it.first() * 10 + it.last() }
    val ans2 = Input(1).lines.map { r2.findAll(it).map { x -> if(x.groupValues[1].length > 1) map[x.groupValues[1]]!! else x.groupValues[1].toInt() } }.sumOf { it.first() * 10 + it.last() }

    println("part 1: $ans | part 2: $ans2")
}