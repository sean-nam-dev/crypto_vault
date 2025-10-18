package com.sean.cryptovault.f_base.data.room.vault

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Vault::class], version = 1, exportSchema = false)
abstract class VaultDatabase: RoomDatabase() {
    abstract fun vaultDao(): VaultDao
}
