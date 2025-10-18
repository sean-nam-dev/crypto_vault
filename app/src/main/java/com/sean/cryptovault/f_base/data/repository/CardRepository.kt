package com.sean.cryptovault.f_base.data.repository

import com.sean.cryptovault.f_base.data.room.card.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun insert(card: Card)

    suspend fun update(card: Card)

    suspend fun delete(card: Card)

    fun getAll(): Flow<List<Card>>

    fun getAllTitleSorted(): Flow<List<Card>>

    fun getAllSubtitledSorted(): Flow<List<Card>>

    fun search(value: String): Flow<List<Card>>

    fun getFavorites(): Flow<List<Card>>
}