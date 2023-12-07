import java.io.File

class Input(private val day: Int, private val part: Int = 1) {
    val content: String get() {
        val bufferedReader = File("../inputs/day$day.txt").bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    val lines: List<String> get() {
        return content.split("\n").filter { it.isNotEmpty() }
    }

    val example: String get() = "32T3K 765\n" +
            "T55J5 684\n" +
            "KK677 28\n" +
            "KTJJT 220\n" +
            "QQQJA 483"
}
