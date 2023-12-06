import kotlin.math.*

fun getRange(t: Double, s: Double): Int {
    val x1 = (-t - sqrt(t.pow(2) - 4 * s)) / -2
    val x2 = (-t + sqrt(t.pow(2) - 4 * s)) / -2
    return maxOf(0.0, ceil(maxOf(x1, x2)) - floor(minOf(x1, x2)) - 1).toInt()
}

fun day6() {
    Input(6).lines.apply {
        val ans = map { Regex("(\\d+)").findAll(it).map { x -> x.value.toDouble() }.toList() }
            .let { it[0].zip(it[1]) }
            .let {
                it.map { (t, s) -> getRange(t, s) }.reduce { acc, t -> acc * t }
            }

        val ans2 = map { Regex("(\\d+)").findAll(it.replace(" ", "")).map { x -> x.value.toDouble() }.first() }
            .let {
                getRange(it[0], it[1])
            }

        println("part 1: $ans | part 2: $ans2")
    }
}
