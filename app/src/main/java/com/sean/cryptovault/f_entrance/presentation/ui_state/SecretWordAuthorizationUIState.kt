package com.sean.cryptovault.f_entrance.presentation.ui_state

data class SecretWordAuthorizationUIState(
    val question: String,
    val answer: String,
    val isDialogActive: Boolean
)
