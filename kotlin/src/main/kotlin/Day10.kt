import java.util.*

private class Area(input: String) {
    lateinit var start: Pair<Int, Int>
    val mat: Array<Array<Pipe>> = input.split("\n").let { self ->
        MutableList(self.size) { x -> MutableList(self[x].length) { y -> Pipe(self[x][y].also { if(it == 'S') start = Pair(x, y) }) }.toTypedArray() }.toTypedArray()
    }
    val dists = MutableList(mat.size) { MutableList(mat[0].size) { -1 } }

    class Pipe(val char: Char) {
        override fun toString(): String = display[char] ?: "."

        fun left(other: Pipe): Boolean = neighRight[other.char]?.contains(char) ?: false
        fun right(other: Pipe): Boolean = neighRight[char]?.contains(other.char) ?: false
        fun up(other: Pipe): Boolean = neighDown[other.char]?.contains(char) ?: false
        fun down(other: Pipe): Boolean = neighDown[char]?.contains(other.char) ?: false

        companion object {
            val display = mapOf('S' to "S", '-' to "─", '|' to "│", 'F' to "┌", '7' to "┐", 'L' to "└", 'J' to "┘")
            val neighRight = mapOf('-' to setOf('J', '-', '7', 'S'), 'F' to setOf('J', '-', '7', 'S') , 'L' to setOf('J', '-', '7', 'S'), 'S' to setOf('J', '-', '7'))
            val neighDown = mapOf('|' to setOf('J', '|', 'L', 'S'), '7' to setOf('J', '|', 'L', 'S') , 'F' to setOf('J', '|', 'L', 'S'), 'S' to setOf('J', '|', 'L'))
        }
    }

    fun next(y : Int, x: Int): List<Pair<Int, Int>> = buildList {
            if(x > 0 && dists[y][x - 1] < 0 && mat[y][x].left(mat[y][x - 1])) add(Pair(y, x - 1))
            if(x < mat.size - 1 && dists[y][x + 1] < 0 && mat[y][x].right(mat[y][x + 1])) add(Pair(y, x + 1))
            if(y > 0 && dists[y - 1][x] < 0 && mat[y][x].up(mat[y - 1][x])) add(Pair(y - 1, x))
            if(y < mat[0].size - 1 && dists[y + 1][x] < 0 && mat[y][x].down(mat[y + 1][x])) add(Pair(y + 1, x))
        }


    fun solve(): Int {
        val Q: Queue<Pair<Int, Int>> = LinkedList()
        Q.add(start.also { (y, x) -> dists[y][x] = 0 })

        while(Q.isNotEmpty()) {
            Q.remove().also { (y, x) ->
                next(y, x).filter { (ny, nx) -> dists[ny][nx] < 0 }.forEach { (ny, nx) ->
                    dists[ny][nx] = dists[y][x] + 1
                    Q.add(Pair(ny, nx))
                }
            }
        }

        return dists.maxOf { it.max() }
    }

    override fun toString(): String {
        var result = ""
        for (x in mat.indices) {
            for (y in 0..<mat[x].size) result += if(dists[x][y] < 0) "." else mat[x][y]
            result += "\n"
        }
        return result
    }
}

fun day10() {
    Input(10).content.replace("\r", "").apply {
        Area(this).apply {
            val ans = solve()
            println("part 1: $ans")
            println(this)
        }
    }
}
