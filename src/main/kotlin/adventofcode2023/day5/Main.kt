package adventofcode2023.day5

class Almanac(raw: String) {
    private val almanacSections: List<String> by lazy { raw.split("\n\n") }
    private val seeds: List<Long> by lazy {
        almanacSections.first().removePrefix("seeds: ").split(' ').map { it.toLong() }
    }
    private val maps: List<AlmanacMap> by lazy {
        almanacSections.subList(1, almanacSections.size).map { AlmanacMap.from(it) }
    }
    private val seedsPartTwo: List<Pair<Long, Long>> by lazy { almanacSections.first().removePrefix("seeds: ").split(' ')
        .map { it.toLong() }.chunked(2).map { it[0] to it[1] }
    }

    fun lowestLocationForSeeds(): Long = seeds.minOf { locationForSeed(it) }

    fun lowestLocationForSeedsPartTwo(): Long = seedsPartTwo.minOf {
        (it.first..<it.first+it.second).asSequence()
            .chunked(10000)
            .minOf { chunk -> chunk.minOf { seed -> locationForSeed(seed) } }
    }

    private fun locationForSeed(seed: Long): Long = maps.fold(seed) { current, map -> map.valueFor(current) }
}

data class AlmanacMap(private val description: String, private val rules: List<Rule>) {
    fun valueFor(original: Long): Long = rules
        .firstNotNullOfOrNull { it.apply(original) }
        ?: original

    companion object {
        fun from(raw: String): AlmanacMap {
            val lines = raw.lines()
            return AlmanacMap(
                description = lines.first(),
                rules = lines.subList(1, lines.size)
                    .map {
                        it.split(" ")
                            .let { rawRule ->
                                Rule(
                                    sourceStart = rawRule[1].toLong(),
                                    destinationStart = rawRule[0].toLong(),
                                    length = rawRule[2].toLong()
                                )
                            }
                    }
            )
        }
    }
}

data class Rule(private val sourceStart: Long, private val destinationStart: Long, private val length: Long) {
    fun apply(value: Long): Long? {
        if ((sourceStart..<sourceStart+length).contains(value)) {
            return destinationStart + (value - sourceStart)
        }

        return null
    }
}
