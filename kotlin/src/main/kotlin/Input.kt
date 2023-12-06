import java.io.File

class Input(private val day: Int, private val part: Int = 1) {
    val content: String get() {
        val bufferedReader = File("../inputs/day$day.txt").bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    val lines: List<String> get() {
        return content.split("\n").filter { it.isNotEmpty() }
    }

    val example: String get() = "Time:      7  15   30\n" +
                                "Distance:  9  40  200"
}
