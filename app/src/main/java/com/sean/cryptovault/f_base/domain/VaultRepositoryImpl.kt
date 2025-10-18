package com.sean.cryptovault.f_base.domain

import com.sean.cryptovault.f_base.data.repository.VaultRepository
import com.sean.cryptovault.f_base.data.room.vault.Vault
import com.sean.cryptovault.f_base.data.room.vault.VaultDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VaultRepositoryImpl @Inject constructor (
    private val vaultDao: VaultDao
): VaultRepository {
    override suspend fun insert(vault: Vault) {
        vaultDao.insert(vault)
    }

    override suspend fun update(vault: Vault) {
        vaultDao.update(vault)
    }

    override suspend fun delete(vault: Vault) {
        vaultDao.delete(vault)
    }

    override fun getAll(): Flow<List<Vault>> {
        return vaultDao.getAllVaults()
    }

    override fun getAllServiceSorted(): Flow<List<Vault>> {
        return vaultDao.getAllVaultsServiceSorted()
    }

    override fun getAllIdentifierSorted(): Flow<List<Vault>> {
        return vaultDao.getAllVaultsIdentifierSorted()
    }

    override fun search(value: String): Flow<List<Vault>> {
        return vaultDao.search(value)
    }

    override fun getAllPasswords(): Flow<List<String>> {
        return vaultDao.getAllPasswords()
    }

    override fun getByDuplicatedPasswords(): Flow<List<Vault>> {
        return vaultDao.getDuplicatedPasswordVaults()
    }

    override fun getFavorites(): Flow<List<Vault>> {
        return vaultDao.getFavoriteVaults()
    }
}