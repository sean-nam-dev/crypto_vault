package com.sean.cryptovault.f_settings.common

import androidx.compose.runtime.Immutable

@Immutable
data class IconTextButtonState(
    val iconId: Int,
    val text: String,
    val onClick: () -> Unit
)
