import kotlin.math.abs

fun day11() {
    Input(11).lines.apply input@{
        val l = buildList<Pair<Int, Int>> { this@input.forEachIndexed { y, it -> it.forEachIndexed { x, k -> if(k == '#') add(Pair(x, y)) } } }
        arrayOf(2, 1_000_000).map { it - 1 }.map { offset ->
            l.map { (x, y) -> Pair(x.toLong(), y.toLong()) }.toMutableList().apply {
                this.indices
                    .filterIndexed { row, _ -> l.none { it.second == row } }
                    .fold(0) { acc, it ->
                        forEachIndexed { i, (x, y) -> if(y > it + acc) this[i] = Pair(x, y + offset) }
                        acc + offset
                    }

                this@input[0].indices
                    .filterIndexed { col, _ -> l.none { it.first == col } }
                    .fold(0) { acc, it ->
                        forEachIndexed { i, (x, y) -> if(x > it + acc) this[i] = Pair(x + offset, y) }
                        acc + offset
                    }
            }.let { self ->
                self.foldIndexed(0L) { i, s, (fx, fy) ->
                    s + (i..<self.size).sumOf { j -> abs(fx - self[j].first) + abs(fy - self[j].second) }
                }
            }
        }.also { (ans, ans2) ->
            println("part 1: $ans | part 2: $ans2")
        }
    }
}

// 1: 10289334
// 2: 649862989626
