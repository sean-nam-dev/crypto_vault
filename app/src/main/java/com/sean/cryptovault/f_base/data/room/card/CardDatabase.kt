package com.sean.cryptovault.f_base.data.room.card

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Card::class], version = 1, exportSchema = false)
abstract class CardDatabase: RoomDatabase() {
    abstract fun cardDao(): CardDao
}