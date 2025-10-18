package com.sean.cryptovault.f_base.presentation.view_model

import androidx.lifecycle.ViewModel
import com.sean.cryptovault.f_base.data.room.vault.Vault
import com.sean.cryptovault.core.navigation.ScreenNavigation
import com.sean.cryptovault.f_base.common.PasswordManager
import com.sean.cryptovault.f_base.presentation.ui_action.StatisticsVaultItemUIAction
import com.sean.cryptovault.f_base.presentation.ui_state.StatisticsVaultItemUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StatisticsVaultItemViewModel(
    val vault: Vault,
    problemList: List<String>
): ViewModel() {
    private val _state = MutableStateFlow(
        StatisticsVaultItemUIState(
            problemList = PasswordManager.checkProblems(password = vault.password, problemList)
        )
    )
    val state = _state.asStateFlow()

    fun onStatisticsVaultItemAction(action: StatisticsVaultItemUIAction) {
        when(action) {
            is StatisticsVaultItemUIAction.OnEditClickAction -> {
                action
                    .navController
                    .navigate(
                        ScreenNavigation.VaultEdition.withArgs(
                            action.vault.id.toString(),
                            action.vault.imageId.toString(),
                            action.vault.service,
                            action.vault.identifier,
                            action.vault.password,
                            action.vault.link.ifEmpty { " " }.replace('/', '|'),
                            action.vault.isFavorite.toString()
                        )
                    ) {
                        launchSingleTop = true
                    }
            }
            is StatisticsVaultItemUIAction.OnPasswordVisibilityChange -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
        }
    }
}