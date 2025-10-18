package com.sean.cryptovault.f_base.data.room.card

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Insert
    suspend fun insert(card: Card)

    @Update
    suspend fun update(card: Card)

    @Delete
    suspend fun delete(card: Card)

    @Query("SELECT * FROM Card ORDER BY id ASC")
    fun getAllCards(): Flow<List<Card>>

    @Query("SELECT * FROM Card ORDER BY bank ASC")
    fun getAllCardsTitleSorted(): Flow<List<Card>>

    @Query("SELECT * FROM Card ORDER BY fullName ASC")
    fun getAllCardsSubtitleSorted(): Flow<List<Card>>

    @Query("SELECT * FROM Card WHERE number LIKE '%' || :searchString || '%' OR fullName LIKE '%' || :searchString || '%' OR bank LIKE '%' || :searchString || '%'")
    fun search(searchString: String): Flow<List<Card>>

    @Query("DELETE FROM Card")
    suspend fun deleteAll()

    @Query("SELECT * FROM Card WHERE isFavorite = 1")
    fun getFavoriteCards(): Flow<List<Card>>
}