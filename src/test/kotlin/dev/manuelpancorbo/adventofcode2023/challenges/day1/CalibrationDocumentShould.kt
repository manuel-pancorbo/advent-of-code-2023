package dev.manuelpancorbo.adventofcode2023.challenges.day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalibrationDocumentShould {
    @Test
    fun `return the calibration value in a document with a single line with just two digits`() {
        val calibrationDocument = CalibrationDocument("pqr3stu8vwx")

        val value = calibrationDocument.value()

        assertEquals(38, value)
    }

    @Test
    fun `return the calibration value in a document with a single line with several digits`() {
        val calibrationDocument = CalibrationDocument("a1b2c3d4e5f")

        val value = calibrationDocument.value()

        assertEquals(15, value)
    }

    @Test
    fun `return the calibration value in a document with a single line with just one digit`() {
        val calibrationDocument = CalibrationDocument("treb7uchet")

        val value = calibrationDocument.value()

        assertEquals(77, value)
    }

    @Test
    fun `return the calibration value in a document with a single line with words representing digits`() {
        val calibrationDocument = CalibrationDocument("twone")

        val value = calibrationDocument.value()

        assertEquals(21, value)
    }

    @Test
    fun `return the calibration value in a document with more than one line`() {
        val calibrationDocument = CalibrationDocument(
            """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """
        )

        val value = calibrationDocument.value()

        assertEquals(281, value)
    }
}