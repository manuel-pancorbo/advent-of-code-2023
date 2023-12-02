package adventofcode2023.day2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameSetShould {
    @Test
    fun `return right value from a game set with just one valid game`() {
        val gameSet = GameSet(
            """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        """.trimIndent()
        )

        val value = gameSet.sumOfIdsOfValidGamesGiven(totalBlue = 14, totalGreen = 13, totalRed = 12)

        assertEquals(1, value)
    }

    @Test
    fun `return 0 value from a game set with just one invalid game`() {
        val gameSet = GameSet(
            """
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        """.trimIndent()
        )

        val value = gameSet.sumOfIdsOfValidGamesGiven(totalBlue = 14, totalGreen = 13, totalRed = 12)

        assertEquals(0, value)
    }

    @Test
    fun `return accumulated value from a game set with several games`() {
        val gameSet = GameSet(
            """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent()
        )

        val value = gameSet.sumOfIdsOfValidGamesGiven(totalBlue = 14, totalGreen = 13, totalRed = 12)

        assertEquals(8, value)
    }

    @Test
    fun `return power of minimum cube set of a game set with just one game`() {
        val gameSet = GameSet(
            """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        """.trimIndent()
        )

        val value = gameSet.powerOfMinimumCubeSets()

        assertEquals(48, value)
    }

    @Test
    fun `return power of minimum cube set of a game set with just several games`() {
        val gameSet = GameSet(
            """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent()
        )

        val value = gameSet.powerOfMinimumCubeSets()

        assertEquals(2286, value)
    }
}
