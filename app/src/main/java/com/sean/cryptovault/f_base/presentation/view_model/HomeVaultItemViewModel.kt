package com.sean.cryptovault.f_base.presentation.view_model

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sean.cryptovault.core.common.Browser
import com.sean.cryptovault.core.navigation.ScreenNavigation
import com.sean.cryptovault.f_base.presentation.ui_action.HomeVaultItemUIAction
import com.sean.cryptovault.f_base.presentation.ui_state.HomeVaultItemUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeVaultItemViewModel: ViewModel() {
    private val _state = MutableStateFlow(HomeVaultItemUIState())
    val state = _state.asStateFlow()

    fun onHomeVaultItemAction(action: HomeVaultItemUIAction) {
        when(action) {
            is HomeVaultItemUIAction.OnPasswordVisibilityChange -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            is HomeVaultItemUIAction.OnOptionMenuVisibilityChange -> {
                _state.update { it.copy(isOptionMenuVisible = !it.isOptionMenuVisible) }

                if (!_state.value.isDeleteButtonActive) {
                    viewModelScope.launch {
                        delay(3000)
                        _state.update { it.copy(isDeleteButtonActive = true) }
                    }
                }
            }
            is HomeVaultItemUIAction.OnBrowseClickAction -> {
                try {
                    Browser.open(action.context, action.link)
                } catch (_: Exception) {
                    Toast.makeText(action.context, action.errorText, Toast.LENGTH_SHORT).show()
                }
            }
            is HomeVaultItemUIAction.OnFavoriteClickAction -> {
                viewModelScope.launch {
                    action.vaultDao.update(action.vault.copy(isFavorite = !action.vault.isFavorite))
                }
            }
            is HomeVaultItemUIAction.OnEditClickAction -> {
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
            is HomeVaultItemUIAction.OnDeleteClickAction -> {
                viewModelScope.launch {
                    action.vaultDao.delete(action.vault)
                }
            }
        }
    }
}