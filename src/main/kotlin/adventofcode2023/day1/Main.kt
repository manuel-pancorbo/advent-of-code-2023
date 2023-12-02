package adventofcode2023.day1

typealias Line = String

class CalibrationDocument(private val rawDocument: String) {
    private val lines by lazy { rawDocument.trimIndent().lines() }

    fun value(): Int =
        lines.map { it.extractCalibrationValueFromLine() }
            .fold(0) { acc, element -> acc + element }
}

private fun Line.extractCalibrationValueFromLine(): Int {
    val simplifiedLine = this.reduceToDigits()
    val firstValue = simplifiedLine.firstOrNull() ?: 0
    val lastValue = simplifiedLine.lastOrNull()

    return "$firstValue$lastValue".toInt()
}

private fun Line.reduceToDigits(): String = when {
    this.isEmpty() -> this
    this.first().isDigit() -> this.first() + this.substring(startIndex = 1).reduceToDigits()
    this.startsWith("one") -> "1" + this.removePrefix("on").reduceToDigits()
    this.startsWith("two") -> "2" + this.removePrefix("tw").reduceToDigits()
    this.startsWith("three") -> "3" + this.removePrefix("thre").reduceToDigits()
    this.startsWith("four") -> "4" + this.removePrefix("fou").reduceToDigits()
    this.startsWith("five") -> "5" + this.removePrefix("fiv").reduceToDigits()
    this.startsWith("six") -> "6" + this.removePrefix("si").reduceToDigits()
    this.startsWith("seven") -> "7" + this.removePrefix("seve").reduceToDigits()
    this.startsWith("eight") -> "8" + this.removePrefix("eigh").reduceToDigits()
    this.startsWith("nine") -> "9" + this.removePrefix("nin").reduceToDigits()
    else -> this.substring(startIndex = 1).reduceToDigits()
}
