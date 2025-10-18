package com.sean.cryptovault.f_entrance.presentation.ui_state

data class SecretWordCreationUIState(
    val question: String,
    val index: Int,
    val isMenuActive: Boolean,
    val answer: String,
    val isBiometricEnabled: Boolean
)