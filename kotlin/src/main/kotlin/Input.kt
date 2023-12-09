import java.io.File

class Input(private val day: Int, private val part: Int = 1) {
    val content: String get() {
        val bufferedReader = File("../inputs/day$day.txt").bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    val lines: List<String> get() {
        return content.split("\n").filter { it.isNotEmpty() }
    }

    val example: String get() = "LR\n" +
            "\n" +
            "11A = (11B, XXX)\n" +
            "11B = (XXX, 11Z)\n" +
            "11Z = (11B, XXX)\n" +
            "22A = (22B, XXX)\n" +
            "22B = (22C, 22C)\n" +
            "22C = (22Z, 22Z)\n" +
            "22Z = (22B, 22B)\n" +
            "XXX = (XXX, XXX)"
}
