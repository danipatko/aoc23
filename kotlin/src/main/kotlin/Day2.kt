fun day2() {
    val r = Regex("(1[3-9]|[2-9]\\d) red|(1[4-9]|[2-9]\\d) green|(1[5-9]|[2-9]\\d) blue")
    val r2 = Regex("(\\d+) (red|green|blue)")
    val game = Regex("Game (\\d+):")

    Input(2).lines.apply {
        val ans = filter { !r.containsMatchIn(it) }.sumOf { game.find(it)!!.groupValues[1].toInt() }
        val ans2 = sumOf {
            buildMap {
                r2.findAll(it).forEach { x ->
                    val k = x.groupValues[1].toInt()
                    compute(x.groupValues[2]) { _, value -> maxOf(k, value ?: k) }
                }
            }.values.reduce { acc, i -> acc * i }
        }

        println("part 1: $ans | part 2: $ans2")
    }
}
