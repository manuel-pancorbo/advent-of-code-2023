package adventofcode2023.day3

import kotlin.math.abs

class Engine(raw: String) {
    private val schematic: List<List<Char>> by lazy { raw.lines().map { row -> row.map { it } } }
    private val numericValues: List<NumericValue> by lazy { extractNumericValues() }
    private val potentialGears: List<Gear> by lazy { extractPotentialGears() }

    fun sumAllParts() = numericValues
        .filter { hasAdjacentSymbols(it) }
        .map { it.value }
        .reduce { acc, numericValue -> acc + numericValue }

    fun sumAllGearRatios(): Int = potentialGears
        .map { getAdjacentNumbers(it) }
        .filter { it.size == 2 }
        .map { it.map { numericValue ->  numericValue.value }.reduce { acc, numericValue -> acc * numericValue } }
        .reduce { acc, gearRatio -> acc + gearRatio }

    private fun hasAdjacentSymbols(numericValue: NumericValue): Boolean {
        for (columnIndex in numericValue.startingPosition.columnIndex..numericValue.endingPosition.columnIndex) {
            if (hasAdjacentSymbols(numericValue.startingPosition.rowIndex, columnIndex)) {
                return true
            }
        }

        return false
    }

    private fun getAdjacentNumbers(gear: Gear): List<NumericValue> = numericValues
        .filter { it.isAdjacentToAPart(gear.position) }

    private fun hasAdjacentSymbols(rowIndex: Int, columnIndex: Int): Boolean =
        this.isASymbol(rowIndex - 1, columnIndex - 1) ||
                this.isASymbol(rowIndex - 1, columnIndex) ||
                this.isASymbol(rowIndex - 1, columnIndex + 1) ||
                this.isASymbol(rowIndex, columnIndex - 1) ||
                this.isASymbol(rowIndex, columnIndex) ||
                this.isASymbol(rowIndex, columnIndex + 1) ||
                this.isASymbol(rowIndex + 1, columnIndex - 1) ||
                this.isASymbol(rowIndex + 1, columnIndex) ||
                this.isASymbol(rowIndex + 1, columnIndex + 1)

    private fun isASymbol(rowIndex: Int, columnIndex: Int): Boolean = when {
        rowIndex < 0 || rowIndex >= schematic.size -> false
        columnIndex < 0 || columnIndex >= schematic[rowIndex].size -> false
        schematic[rowIndex][columnIndex].isAValidSymbol() -> true
        else -> false
    }

    private fun extractNumericValues(): List<NumericValue> {
        return schematic.flatMapIndexed { rowIndex, row ->
            val numericValues = mutableListOf<NumericValue>()
            var previousValueWasADigit = false
            for (columnIndex in row.indices) {
                val value = schematic[rowIndex][columnIndex]
                if (value.isDigit() && !previousValueWasADigit) {
                    val fullValue = row.joinToString("").substring(columnIndex).takeWhile { it.isDigit() }
                    numericValues.add(
                        NumericValue(
                            fullValue.toInt(),
                            PartPosition(rowIndex, columnIndex),
                            PartPosition(rowIndex, columnIndex + fullValue.length - 1)
                        )
                    )
                    previousValueWasADigit = true
                } else {
                    if (!value.isDigit()) {
                        previousValueWasADigit = false
                    }
                }
            }
            numericValues.toList()
        }
    }

    private fun extractPotentialGears(): List<Gear> = schematic.flatMapIndexed { rowIndex, row ->
        row.mapIndexedNotNull { columnIndex, value ->
            if (value == '*') {
                Gear(PartPosition(rowIndex, columnIndex))
            } else {
                null
            }
        }
    }
}

data class NumericValue(val value: Int, val startingPosition: PartPosition, val endingPosition: PartPosition) {
    fun isAdjacentToAPart(position: PartPosition): Boolean {
        if (abs(position.rowIndex - startingPosition.rowIndex) > 1) return false

        return position.columnIndex >= (startingPosition.columnIndex - 1) &&
                position.columnIndex <= (endingPosition.columnIndex + 1)
    }
}

data class Gear(val position: PartPosition)
data class PartPosition(val rowIndex: Int, val columnIndex: Int)

private fun Char.isAValidSymbol(): Boolean = !this.isDigit() && this != '.'
