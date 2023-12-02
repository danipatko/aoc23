import java.io.File

class Input(private val day: Int, private val part: Int = 1) {
    val content: String get() {
        val bufferedReader = File("../inputs/day$day.txt").bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    val lines: List<String> get() {
        return content.split("\n").filter { it.isNotEmpty() }
    }

    val example: String get() = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
                                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
                                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
                                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
                                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
}
