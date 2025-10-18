package com.sean.cryptovault.f_base.domain

import com.sean.cryptovault.f_base.data.repository.CardRepository
import com.sean.cryptovault.f_base.data.room.card.Card
import com.sean.cryptovault.f_base.data.room.card.CardDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardDao: CardDao
): CardRepository {
    override suspend fun insert(card: Card) {
        cardDao.insert(card)
    }

    override suspend fun update(card: Card) {
        cardDao.update(card)
    }

    override suspend fun delete(card: Card) {
        cardDao.delete(card)
    }

    override fun getAll(): Flow<List<Card>> {
        return cardDao.getAllCards()
    }

    override fun getAllTitleSorted(): Flow<List<Card>> {
        return cardDao.getAllCardsTitleSorted()
    }

    override fun getAllSubtitledSorted(): Flow<List<Card>> {
        return cardDao.getAllCardsSubtitleSorted()
    }

    override fun search(value: String): Flow<List<Card>> {
        return cardDao.search(value)
    }

    override fun getFavorites(): Flow<List<Card>> {
        return cardDao.getFavoriteCards()
    }
}