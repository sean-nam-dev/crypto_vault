package com.sean.cryptovault.f_base.data.room.vault

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vault(
    @PrimaryKey(true)
    val id: Long = 0,
    @DrawableRes
    val imageId: Int,
    val service: String,
    val identifier: String,
    val password: String,
    val link: String,
    val isFavorite: Boolean = false
)
