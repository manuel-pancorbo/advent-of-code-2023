package adventofcode2023.day11

import adventofcode2023.day11.EMPTY_SPACE_DISTANCE_FACTOR
import adventofcode2023.day11.EMPTY_SPACE_DISTANCE_FACTOR_FOR_PART_TWO
import adventofcode2023.day11.Universe
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UniverseShould {
    @Test
    fun `calculate the sum of the distances of the different pairs of galaxies`() {
        val universe = Universe.from(INPUT.trimIndent(), EMPTY_SPACE_DISTANCE_FACTOR)

        val result = universe.sumOfTheShortestDistancesBetweenGalaxies()

        assertEquals(374, result)
    }

    @Test
    fun `calculate the sum of the distances of the different pairs of galaxies with distance factor of part 2`() {
        val universe = Universe.from(INPUT.trimIndent(), EMPTY_SPACE_DISTANCE_FACTOR_FOR_PART_TWO)

        val result = universe.sumOfTheShortestDistancesBetweenGalaxies()

        assertEquals(82000210, result)
    }
}

const val INPUT = """
...#......
.......#..
#.........
..........
......#...
.#........
.........#
..........
.......#..
#...#.....
"""