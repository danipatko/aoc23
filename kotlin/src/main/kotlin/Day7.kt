class CamelCard(private val line: String, val bid: Int, private val part: Boolean) : Comparable<CamelCard> {
    private val rank: Int = buildMap<Char, Int> {
        line.map {
            compute(it) { _, v -> if(v != null) v + 1 else 1 }
        }
    }.let {
        val joker = part && it.contains('J')
        if(it.size == 1) {
            FIVE_OF_A_KIND
        } else if(it.size == 2 && it.values.any{ x -> x == 4 }) {
            if(joker) FIVE_OF_A_KIND else FOUR_OF_A_KIND
        } else if(it.size == 2 && it.values.any{ x -> x == 3 }) {
            if(joker) FIVE_OF_A_KIND else FULL_HOUSE
        } else if(it.size == 3 && it.values.any{ x -> x == 3 }) {
            if(joker) FOUR_OF_A_KIND else THREE_OF_A_KIND
        } else if(it.size == 3 && it.values.count{ x -> x == 2 } == 2) {
             if(joker && it['J'] == 1) FULL_HOUSE else if(joker && it['J'] == 2) FOUR_OF_A_KIND else TWO_PAIR
        } else if(it.size == 4 && it.values.any{ x -> x == 2 }) {
            if(joker) THREE_OF_A_KIND else ONE_PAIR
        } else {
            if(joker) ONE_PAIR else HIGH_CARD
        }
    }

    override fun compareTo(other: CamelCard): Int {
        compareValues(rank, other.rank).apply {
            if(this != 0) return this
        }
        for (i in 0..4) {
            compareValues(getVal(line[i]), getVal(other.line[i])).apply {
                if(this != 0) return this
            }
        }
        return 0
    }

    private fun getVal(c: Char): Int {
        return when(c) {
            'A' -> 14
            'K' -> 13
            'Q' -> 12
            'J' -> if(part) 1 else 11
            'T' -> 10
            else -> c.code - 48
        }
    }

    companion object {
        const val FIVE_OF_A_KIND = 6
        const val FOUR_OF_A_KIND = 5
        const val FULL_HOUSE = 4
        const val THREE_OF_A_KIND = 3
        const val TWO_PAIR = 2
        const val ONE_PAIR = 1
        const val HIGH_CARD = 0

        fun fromPart1(obj: CamelCard): CamelCard {
            // bruh xd
            return CamelCard(obj.line, obj.bid, true)
        }
    }
}

fun day7() {
    Input(7).lines.apply {
        map {
            Regex("^([2-9AKQJT]+) (\\d+)\$").find(it).let { x -> CamelCard(x!!.groupValues[1], x.groupValues[2].toInt(), false) }
        }.apply {
            val ans = sorted().mapIndexed { i, x -> x.bid * (i + 1) }.sum()
            val ans2 = map { CamelCard.fromPart1(it) }.sorted().mapIndexed { i, x -> x.bid * (i + 1) }.sum()

            println("part 1: $ans | part 2: $ans2")
        }
    }
}
