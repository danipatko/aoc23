import kotlin.math.pow

fun day4() {
    Input(4).lines.apply {
        map {
            it.split(":")[1].split("|").map { x -> x.split(Regex("\\s+")).filter { y -> y.isNotEmpty() }.map { y -> y.toInt() } }
        }.map {
            it[0].intersect(it[1]).count()
        }.apply pre@{
            val ans = sumOf { 2.0f.pow(it - 1).toInt() }
            val ans2 = MutableList(this.size) { 1 }.apply { forEachIndexed { i, _ -> recurse(i, this, this@pre) } }.sum()

            println("part 1: $ans | part 2: $ans2")
        }
    }
}

fun recurse(index: Int, list: MutableList<Int>, values: List<Int>) {
    IntRange(index + 1, index + values[index]).forEach { i ->
        recurse(i, list.apply { this[i]++ }, values)
    }
}
