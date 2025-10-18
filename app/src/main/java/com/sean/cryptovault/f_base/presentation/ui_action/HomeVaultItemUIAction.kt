package com.sean.cryptovault.f_base.presentation.ui_action

import android.content.Context
import androidx.navigation.NavController
import com.sean.cryptovault.f_base.data.room.vault.Vault
import com.sean.cryptovault.f_base.data.room.vault.VaultDao

sealed class HomeVaultItemUIAction {
    data class OnPasswordVisibilityChange(val action: Unit = Unit): HomeVaultItemUIAction()
    data class OnOptionMenuVisibilityChange(val action: Unit = Unit): HomeVaultItemUIAction()
    data class OnBrowseClickAction(
        val context: Context,
        val link: String,
        val errorText: String
    ): HomeVaultItemUIAction()
    data class OnFavoriteClickAction(
        val vaultDao: VaultDao,
        val vault: Vault
    ): HomeVaultItemUIAction()
    data class OnEditClickAction(
        val navController: NavController,
        val vault: Vault
    ): HomeVaultItemUIAction()
    data class OnDeleteClickAction(
        val vaultDao: VaultDao,
        val vault: Vault
    ): HomeVaultItemUIAction()
}