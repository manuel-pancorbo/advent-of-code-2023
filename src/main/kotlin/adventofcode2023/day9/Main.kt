package adventofcode2023.day9

data class SensorReport(private val values: List<ValueHistory>) {

    fun sumNextValueForEachHistory(): Int = values.map { it.nextValue }.sumOf { it }
    fun sumPreviousValueForEachHistory(): Int = values.map { it.previousValue }.sumOf { it }

    companion object {
        fun from(raw: String): SensorReport =
            raw.lines()
                .map {
                    "(-?\\d+)".toRegex().findAll(it)
                        .map { occurrence -> occurrence.value.toInt() }
                        .let { values -> ValueHistory(values.toList()) }
                }
                .let { SensorReport(it) }
    }
}

data class ValueHistory(val history: List<Int>) {

    val nextValue: Int by lazy { predictNextValue(history) }
    val previousValue: Int by lazy { predictPreviousValue(history) }

    private fun predictNextValue(sequence: List<Int>): Int {
        if (sequence.all { it == 0 }) {
            return 0
        }

        return sequence.last() + predictNextValue(sequence.zipWithNext { a, b -> b - a })
    }

    private fun predictPreviousValue(sequence: List<Int>): Int {
        if (sequence.all { it == 0 }) {
            return 0
        }

        return sequence.first() - predictPreviousValue(sequence.zipWithNext { a, b -> b - a })
    }
}
