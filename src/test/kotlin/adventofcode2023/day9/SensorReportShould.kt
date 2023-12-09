package adventofcode2023.day9

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SensorReportShould {
    @Test
    fun `return the sum of the next value of every value history`() {
        val report = SensorReport.from("""
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent())

        val result = report.sumNextValueForEachHistory()

        assertEquals(114, result)
    }

    @Test
    fun `return the sum of the previous value of every value history`() {
        val report = SensorReport.from("""
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent())

        val result = report.sumPreviousValueForEachHistory()

        assertEquals(2, result)
    }
}