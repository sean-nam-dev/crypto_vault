package com.sean.cryptovault.f_base.data.room.card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card(
    @PrimaryKey(true)
    val id: Long = 0,
    val bank: String,
    val number: String,
    val validThru: String,
    val cvv: String,
    val fullName: String,
    val isFavorite: Boolean = false
)