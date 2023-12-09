package adventofcode2023.day6

import kotlin.math.round

data class BoatRacesBoard(private val races: List<BoatRace>) {

    fun multiplyWaysOfBeatingRaces(): Int = races.map { race -> race.countWaysOfBeating() }
        .reduce { acc, waysOfBeatingARace -> acc * waysOfBeatingARace }

    companion object {
        fun from(raw: String): BoatRacesBoard {
            val lines = raw.lines()
            val times = "(\\d+)".toRegex().findAll(lines[0]).map { it.value.toLong() }.toList()
            val distances = "(\\d+)".toRegex().findAll(lines[1]).map { it.value.toLong() }.toList()

            return BoatRacesBoard(times.mapIndexed { index, allowedTime -> BoatRace(allowedTime, distances[index]) })
        }

        fun fromRawPartTwo(raw: String): BoatRacesBoard {
            val lines = raw.lines()
            val findNumbersRegex = "(\\d+)".toRegex()

            return BoatRacesBoard(
                listOf(
                    BoatRace(
                        findNumbersRegex.findAll(lines[0]).map { it.value }.joinToString("").toLong(),
                        findNumbersRegex.findAll(lines[1]).map { it.value }.joinToString("").toLong()
                    )
                )
            )
        }
    }
}

data class BoatRace(private val allowedTime: Long, private val distanceRecord: Long) {
    fun countWaysOfBeating(): Int =
        searchMinHoldingTimeThatBeatRace(0, allowedTime)!! // Let's assume, for the sake of simplicity, that there is always a solution
            .let { (round(allowedTime / 2f).toInt() - it) * 2 }
            .let { if ((allowedTime % 2) == 0L) it + 1 else it } // correction factor for even allowed
            .toInt()

    private fun searchMinHoldingTimeThatBeatRace(minAllowedTime: Long, maxAllowedTime: Long): Long? {
        val timeToCheck = round((maxAllowedTime + minAllowedTime) / 2f).toLong()
        val distance = computeDistanceForBoat(timeToCheck)

        return if (distance > distanceRecord) {
            if (minAllowedTime == maxAllowedTime) {
                timeToCheck
            } else {
                searchMinHoldingTimeThatBeatRace(minAllowedTime, timeToCheck - 1) ?: timeToCheck
            }
        } else {
            if (minAllowedTime == maxAllowedTime) {
                null
            } else {
                searchMinHoldingTimeThatBeatRace(timeToCheck + 1, maxAllowedTime)
            }
        }
    }

    private fun computeDistanceForBoat(timeHoldingButton: Long): Long =
        timeHoldingButton * (allowedTime - timeHoldingButton)
}
