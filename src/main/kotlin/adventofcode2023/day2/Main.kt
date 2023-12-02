package adventofcode2023.day2

const val GAME_ID_DELIMITER = ":"
const val RECORD_DELIMITER = ";"
const val COLOR_DELIMITER = ","

class GameSet(private val value: String) {
    private val games by lazy { value.lines().map { Game(it) } }

    fun sumOfIdsOfValidGamesGiven(totalBlue: Int, totalRed: Int, totalGreen: Int): Int =
        games.filter { it.isPossibleGiven(totalBlue, totalRed, totalGreen) }
            .map { it.id }
            .reduceOrNull { acc: Int, gameId: Int -> acc + gameId }
            ?: 0

    fun powerOfMinimumCubeSets(): Int =
        games.map { it.minimumCubeSetPower() }.reduce { acc: Int, power: Int -> acc + power }
}

class Game(private val value: String) {
    val id: Int by lazy { value.split(GAME_ID_DELIMITER).first().removePrefix("Game ").toInt() }
    private val records: List<Record> by lazy {
        value.split(GAME_ID_DELIMITER)[1].split(RECORD_DELIMITER).map { Record(it) }
    }

    fun isPossibleGiven(totalBlue: Int, totalRed: Int, totalGreen: Int): Boolean =
        records.none { !it.isPossibleGiven(totalBlue, totalRed, totalGreen) }

    fun minimumCubeSetPower(): Int =
        (records.maxOfOrNull { it.blue } ?: 0) * (records.maxOfOrNull { it.red }
            ?: 0) * (records.maxOfOrNull { it.green } ?: 0)
}

class Record(private val value: String) {
    val blue: Int by lazy { value.extractColorValue("blue") }
    val red: Int by lazy { value.extractColorValue("red") }
    val green: Int by lazy { value.extractColorValue("green") }

    private fun String.extractColorValue(color: String): Int =
        this.split(COLOR_DELIMITER)
            .firstOrNull { it.contains(color) }
            ?.removeSuffix(color)
            ?.trim()
            ?.toInt()
            ?: 0

    fun isPossibleGiven(totalBlue: Int, totalRed: Int, totalGreen: Int): Boolean =
        totalBlue >= blue && totalRed >= red && totalGreen >= green
}
