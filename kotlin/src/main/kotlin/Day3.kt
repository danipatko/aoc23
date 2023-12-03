data class Match(val x: Int, val y: Int, val symbol: Char)

fun day3() {
    val r = Regex("(\\d+)")
    val symbol = Regex("[^\\\\.\\d]")

    Input(3).lines.apply {
        buildMap<Match, List<Int>> {
            this@apply.forEachIndexed { i, it ->
                r.findAll(it).forEach { number ->
                    val range = IntRange(maxOf(number.range.first - 1, 0), minOf(number.range.last + 1, it.length - 1))
                    IntRange(maxOf(i - 1, 0), minOf(i + 1, this@apply.size - 1)).map { row ->
                        symbol.findAll(this@apply[row].substring(range)).forEach { match ->
                            this.compute(Match(range.first + match.range.first, row, match.value[0])) { _, v ->
                                v?.apply { addFirst(number.groupValues[0].toInt()) } ?: mutableListOf(number.groupValues[0].toInt())
                            }
                        }
                    }
                }
            }
        }.apply {
            val ans = this.entries.sumOf { it.value.sum() }
            val ans2 = this.entries.filter { it.key.symbol == '*' && it.value.count() == 2 }.sumOf { it.value.reduce { acc, i -> acc * i } }

            println("part 1: $ans | part 2: $ans2")
        }
    }
}
