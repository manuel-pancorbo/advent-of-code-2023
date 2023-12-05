package adventofcode2023.day3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EngineSchematicShould {
    @Test
    fun `return the value of the sum of all parts`() {
        val engine = Engine("""
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent())

        val result = engine.sumAllParts()

        assertEquals(4361, result)
    }

    @Test
    fun `return the value of the sum of all gears ratio`() {
        val engine = Engine("""
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent())

        val result = engine.sumAllGearRatios()

        assertEquals(467835, result)
    }
}