fun day9() {
    Input(9).lines.map { it.split(" ").map { x -> x.toInt() } }.apply {
        map {
            buildList {
                var copy = it.toMutableList()
                while (!copy.all { x -> x == 0 }) {
                    copy = buildList { copy.forEachIndexed { i, _ -> if(i < copy.size - 1) add(copy[i + 1] - copy[i]) } }
                        .toMutableList()
                        .also { x -> addFirst(Pair(x.first(), x.last())) }
                }
            }.let { l ->
                l.reduce { x, y -> Pair(x.first + y.second, y.first - x.second) }
                    .let { x -> Pair(x.first + it.last(), it.first() - x.second) }
            }
        }.apply {
            val ans = sumOf { x -> x.first }
            val ans2 = sumOf { x -> x.second }

            println("part 1: $ans | part 2: $ans2")
        }
    }
}

// 2043677056
// 1062