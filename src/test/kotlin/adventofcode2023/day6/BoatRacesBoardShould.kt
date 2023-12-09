package adventofcode2023.day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BoatRacesBoardShould {
    @Test
    fun `compute the multiplication of the ways of beating each race`() {
        val board = BoatRacesBoard.from("""
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent())

        val result = board.multiplyWaysOfBeatingRaces()

        assertEquals(288, result)
    }

    @Test
    fun `compute the multiplication of the ways of beating each race given part 2 conditions`() {
        val board = BoatRacesBoard.fromRawPartTwo("""
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent())

        val result = board.multiplyWaysOfBeatingRaces()

        assertEquals(71503, result)
    }
}