package com.sean.cryptovault.f_entrance.presentation.ui_action

import androidx.navigation.NavController

sealed class PINUIAction {
    data class OnPINChange(
        val pin: String,
        val originalPIN: String,
        val navController: NavController
    ): PINUIAction()
    data class OnBackSpace(val action: Unit): PINUIAction()
    data class OnBiometricUse(val action: Unit): PINUIAction()
}