package com.sean.cryptovault.core.common

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sean.cryptovault.f_base.common.cryptography.CryptoManager

inline fun <reified T : RoomDatabase> Room.getDb(
    context: Context,
    name: String
): T {
    return databaseBuilder(context, T::class.java, name)
        .openHelperFactory(CryptoManager().getFactory(context.filesDir))
        .build()
}