
class Tr(private val from: Long, private val to: Long, private val offset: Long) {
    fun translate(n: Long): Long? {
        return if(n in from..to) n + offset else null
    }

    fun translate(r: LongRange, ranges: MutableList<LongRange>): Boolean {
        offset(LongRange(maxOf(r.first, from), minOf(r.last, to))).takeIf { it.first < it.last }?.apply {
            ranges.add(this)
            return true
        }
        return false
    }

    private fun offset(r: LongRange): LongRange {
        return LongRange(r.first + offset, r.last + offset)
    }

    companion object {
        fun parse(line: String): Tr {
            return Regex("(\\d+)").findAll(line).map { y -> y.value.toLong() }.toList().let {
                Tr(it[1], it[1] + it[2] - 1, it[0] - it[1])
            }
        }
    }
}

fun day5() {
    lateinit var l: MutableList<Long>
    lateinit var l2: MutableList<LongRange>

    Input(5).content.split(Regex("(\\w+)-to-(\\w+) map:"))
        .map { it.replace(Regex("^\\n|\\n{2}|\\n$"), "") }
        .apply {
            Regex("(\\d+)").findAll(this.removeFirst()).map { it.value.toLong() }.apply {
                l = this.toMutableList()
                l2 = this.chunked(2).map { LongRange(it[0], it[0] + it[1] - 1) }.toMutableList()
            }
        }.map { it.split("\n").map { x -> Tr.parse(x) } }
        .onEach { set ->
            // part 1
            l = MutableList<Long>(l.size) { -1 }.apply l@{
                set.forEach { t ->
                    this.forEachIndexed { i, _ ->
                        if(this[i] < 0) t.translate(l[i]).takeIf { it != null }?.apply {
                            this@l[i] = this
                        }
                    }
                }
            }.mapIndexed { i, x -> if(x < 0) l[i] else x }.toMutableList()

            // part 2
            l2 = buildList l2@{
                MutableList(l2.size) { false }.apply processed@{
                    l2.forEachIndexed { i, range ->
                        set.forEach {
                            if(it.translate(range, this@l2)) this@processed[i] = true
                        }
                    }
                }.forEachIndexed { i, x -> if(!x) add(l2[i]) }
            }.toMutableList()
        }

    val ans = l.min()
    val ans2 = l2.minOf { it.first }
    println("part 1: $ans | part 2: $ans2")
}

// 199602917, 2254686
