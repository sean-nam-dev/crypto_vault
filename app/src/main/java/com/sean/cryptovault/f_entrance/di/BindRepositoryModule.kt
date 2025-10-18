package com.sean.cryptovault.f_entrance.di

import com.sean.cryptovault.f_base.data.repository.CardRepository
import com.sean.cryptovault.f_base.data.repository.VaultRepository
import com.sean.cryptovault.f_base.domain.CardRepositoryImpl
import com.sean.cryptovault.f_base.domain.VaultRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
@Suppress("FunctionName")
interface BindRepositoryModule {
    @Binds
    fun bindVaultRepositoryImpl_to_VaultRepository(
        vaultRepositoryImpl: VaultRepositoryImpl
    ): VaultRepository

    @Binds
    fun bindCardRepositoryImpl_to_CardRepository(
        cardRepositoryImpl: CardRepositoryImpl
    ): CardRepository
}