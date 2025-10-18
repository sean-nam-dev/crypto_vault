package com.sean.cryptovault.f_base.presentation.ui_action

import androidx.navigation.NavController
import com.sean.cryptovault.f_base.data.room.vault.Vault

sealed class StatisticsVaultItemUIAction {
    data class OnEditClickAction(val navController: NavController, val vault: Vault): StatisticsVaultItemUIAction()
    data class OnPasswordVisibilityChange(val action: Unit = Unit): StatisticsVaultItemUIAction()
}