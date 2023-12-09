fun lcm(x: Long, y: Long): Long {
    var a = x
    var b = y
    while (b > 0) {
        val tmp = b
        b = a % b
        a = tmp
    }
    return x * (y / a)
}

private fun traverse(from: String, instructions: String, graph: Map<String, Array<String>>, callback: (curr: String) -> Boolean): Int {
    var curr = from
    var i = 0
    var steps = 0

    while (callback(curr)) {
        curr = graph[curr]!![if(instructions[i] == 'L') 0 else 1]
        i = if(i + 1 >= instructions.length) 0 else i + 1
        steps++
    }

    return steps
}

fun day8() {
    Input(8).content.replace("\r", "").split("\n\n")
        .also {
            val instructions = it[0].replace(Regex("[^RL]"), "")
            val graph = buildMap graph@{
                it[1].split("\n").map { l ->
                    Regex("^([0-9A-Z]{3}) = \\(([0-9A-Z]{3}), ([0-9A-Z]{3})\\)\$").findAll(l).apply {
                        forEach { r -> this@graph[r.groupValues[1]] = arrayOf(r.groupValues[2], r.groupValues[3]) }
                    }
                }
            }

            val ans = traverse("AAA", instructions, graph) { c -> c != "ZZZ" }
            val ans2 = graph.keys.filter { x -> x.endsWith('A') }.map { x -> traverse(x, instructions, graph) { c -> !c.endsWith('Z') }.toLong() }.reduce { x, y -> lcm(x, y) }

            println("part 1: $ans | part 2: $ans2")
        }
}


