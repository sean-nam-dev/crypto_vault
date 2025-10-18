package com.sean.cryptovault.f_settings.presentation.ui_state

data class PINChangerUIState(
    val pinValue: String = "",
    val secretWordValue: String = "",
    val isPINError: Boolean = false,
    val isSecretWordError: Boolean = false
)