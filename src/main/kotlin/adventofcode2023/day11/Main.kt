package adventofcode2023.day11

import kotlin.math.abs

const val EMPTY_SPACE_DISTANCE_FACTOR = 2
const val EMPTY_SPACE_DISTANCE_FACTOR_FOR_PART_TWO = 1000000

data class Universe(private val galaxies: List<Galaxy>) {

    fun sumOfTheShortestDistancesBetweenGalaxies() =
        calculateShortestDistances(galaxies.first(), galaxies.subList(1, galaxies.size))
            .sumOf { it.toLong() }

    private fun calculateShortestDistances(galaxy: Galaxy, universe: List<Galaxy>): List<Int> {
        if (universe.isEmpty()) {
            return emptyList()
        }

        val shortestDistances = universe.map { anotherGalaxy -> galaxy.distanceTo(anotherGalaxy) }

        if (universe.size == 1) {
            return shortestDistances
        }

        return shortestDistances.plus(calculateShortestDistances(universe.first(), universe.subList(1, universe.size)))
    }

    companion object {
        fun from(raw: String, emptySpaceFactor: Int): Universe =
            raw.lines()
                .map { it.map { element -> element } }
                .expand()
                .findGalaxiesApplying(emptySpaceFactor)
                .let { Universe(it) }

        private fun List<List<Char>>.expand(): List<List<Char>> {
            val expandedGalaxy = this.toMutableList()

            // Expand rows
            expandedGalaxy.forEachIndexed { rowIndex, row -> takeIf { row.all { it == '.' } }
                    ?.let { expandedGalaxy[rowIndex] = row.indices.map { '*' } }
            }

            // Expand columns
            for (columnIndex in 0..<expandedGalaxy.first().size) {
                expandedGalaxy.map { row -> row[columnIndex] }
                    .takeUnless { column -> column.contains('#') }
                    ?.let {
                        for (rowIndex in 0..<expandedGalaxy.size) {
                            val expandedColumn = expandedGalaxy[rowIndex].toMutableList()
                            expandedColumn[columnIndex] = '*'
                            expandedGalaxy[rowIndex] = expandedColumn.toList()
                        }
                    }
            }

            return expandedGalaxy.toList()
        }

        private fun List<List<Char>>.findGalaxiesApplying(emptySpaceFactor: Int): List<Galaxy> {
            var rowIndex = -1
            var columnIndex: Int

            return this.flatMap { row ->
                if (row.all { it == '*' }) rowIndex += emptySpaceFactor else rowIndex++
                columnIndex = 0
                row.mapNotNull { value ->
                    if (value == '*') columnIndex += emptySpaceFactor else columnIndex++
                    value.takeIf { it == '#' }
                        ?.let { Galaxy(rowIndex, columnIndex) }
                }
            }
        }
    }
}

data class Galaxy(private val x: Int, private val y: Int) {
    fun distanceTo(anotherGalaxy: Galaxy): Int = abs(x - anotherGalaxy.x) + abs(y - anotherGalaxy.y)
}
