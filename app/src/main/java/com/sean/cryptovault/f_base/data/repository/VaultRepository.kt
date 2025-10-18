package com.sean.cryptovault.f_base.data.repository

import com.sean.cryptovault.f_base.data.room.vault.Vault
import kotlinx.coroutines.flow.Flow

interface VaultRepository {
    suspend fun insert(vault: Vault)

    suspend fun update(vault: Vault)

    suspend fun delete(vault: Vault)

    fun getAll(): Flow<List<Vault>>

    fun getAllServiceSorted(): Flow<List<Vault>>

    fun getAllIdentifierSorted(): Flow<List<Vault>>

    fun search(value: String): Flow<List<Vault>>

    fun getAllPasswords(): Flow<List<String>>

    fun getByDuplicatedPasswords(): Flow<List<Vault>>

    fun getFavorites(): Flow<List<Vault>>
}