package adventofcode2023.day4

import kotlin.math.pow

class CardSet(raw: String) {
    private val cards: List<Card> by lazy { raw.lines().map { Card(it) } }
    private val cardsMap: Map<Int, Card> by lazy { cards.associateBy { it.id } }
    private val processedCards: List<Card> by lazy { cards.flatMap { processCard(it) } }

    fun sumAllCardPoints(): Int = cards.map { it.calculatePointsPart1() }.reduce { acc, cardPoints -> acc + cardPoints }

    fun totalProcessedCards(): Int = processedCards.size

    private fun processCard(card: Card): List<Card> {
        val processedCards = mutableListOf<Card>()
        processedCards.add(card)

        for (newCardId in card.id + 1..card.id + card.calculatePointsPart2()) {
            if (cardsMap.contains(newCardId)) {
                val newCard = cardsMap[newCardId]!!
                processedCards.addAll(processCard(newCard))
            }
        }

        return processedCards.toList()
    }
}

class Card(raw: String) {
    val id: Int by lazy { "(\\d+)(?=:)".toRegex().find(raw)!!.value.toInt() }
    private val winningNumbers: List<Int> by lazy { extractWinningNumbers(raw) }
    private val participantNumbers: List<Int> by lazy { extractParticipantNumbers(raw) }
    private val matchedNumbers: List<Int> by lazy { participantNumbers.intersect(winningNumbers.toSet()).toList() }

    fun calculatePointsPart1(): Int = matchedNumbers
        .let { 2f.pow(it.size - 1).toInt() }

    fun calculatePointsPart2() = matchedNumbers.size

    private fun extractWinningNumbers(raw: String) = raw
        .split('|')[0]
        .split(':')[1]
        .split(' ')
        .filterNot { it.isEmpty() }
        .map { it.toInt() }

    private fun extractParticipantNumbers(raw: String) = raw
        .split('|')[1]
        .split(' ')
        .filterNot { it.isEmpty() }
        .map { it.toInt() }
}
