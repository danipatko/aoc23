import java.io.File

class Input(private val day: Int, private val part: Int = 1) {
    val content: String get() {
        val bufferedReader = File("../inputs/day$day.txt").bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    val lines: List<String> get() {
        return content.replace("\r", "").split("\n").filter { it.isNotEmpty() }
    }

    val example: String get() = "..........\n" +
            ".S------7.\n" +
            ".|F----7|.\n" +
            ".||....||.\n" +
            ".||....||.\n" +
            ".|L-7F-J|.\n" +
            ".|..||..|.\n" +
            ".L--JL--J.\n" +
            "..........\n"
}
