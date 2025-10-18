package com.sean.cryptovault.f_entrance.di

import android.content.Context
import androidx.room.Room
import com.sean.cryptovault.core.common.getDb
import com.sean.cryptovault.f_base.data.room.card.CardDatabase
import com.sean.cryptovault.f_base.data.room.vault.VaultDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class DaoModule {
    @Provides
    @Reusable
    fun provideVaultDao(context: Context) = Room.getDb<VaultDatabase>(context, "vault").vaultDao()

    @Provides
    @Reusable
    fun provideCardDao(context: Context) = Room.getDb<CardDatabase>(context, "card").cardDao()
}