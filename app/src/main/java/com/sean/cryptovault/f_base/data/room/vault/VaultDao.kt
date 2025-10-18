package com.sean.cryptovault.f_base.data.room.vault

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface VaultDao {
    @Insert
    suspend fun insert(vault: Vault)

    @Update
    suspend fun update(vault: Vault)

    @Delete
    suspend fun delete(vault: Vault)

    @Query("DELETE FROM Vault")
    suspend fun deleteAll()

    @Query("SELECT * FROM Vault ORDER BY id ASC")
    fun getAllVaults(): Flow<List<Vault>>

    @Query("SELECT * FROM Vault ORDER BY service ASC")
    fun getAllVaultsServiceSorted(): Flow<List<Vault>>

    @Query("SELECT * FROM Vault ORDER BY identifier ASC")
    fun getAllVaultsIdentifierSorted(): Flow<List<Vault>>

    @Query("SELECT * FROM Vault WHERE service LIKE '%' || :searchString || '%' OR identifier LIKE '%' || :searchString || '%'")
    fun search(searchString: String): Flow<List<Vault>>

    @Query("SELECT password FROM Vault")
    fun getAllPasswords(): Flow<List<String>>

    @Query("SELECT * FROM Vault GROUP BY password HAVING COUNT(password) > 1")
    fun getDuplicatedPasswordVaults(): Flow<List<Vault>>

    @Query("SELECT * FROM Vault WHERE isFavorite = 1")
    fun getFavoriteVaults(): Flow<List<Vault>>
}