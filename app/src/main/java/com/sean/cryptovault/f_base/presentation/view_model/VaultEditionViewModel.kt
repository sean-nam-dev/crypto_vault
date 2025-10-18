package com.sean.cryptovault.f_base.presentation.view_model

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sean.cryptovault.f_base.data.room.vault.Vault
import com.sean.cryptovault.f_base.presentation.ui_action.VaultCreationUIAction
import com.sean.cryptovault.f_base.presentation.ui_state.VaultCreationUIState
import com.sean.cryptovault.f_base.common.PasswordGenerator
import com.sean.cryptovault.core.common.VAULT_IMAGE_HASHMAP
import com.sean.cryptovault.f_base.data.room.vault.VaultDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VaultEditionViewModel @AssistedInject constructor(
    @Assisted("id") private val id: Long,
    @Assisted("imageId") imageId: Int,
    @Assisted("service") service: String,
    @Assisted("username") username: String,
    @Assisted("password") password: String,
    @Assisted("link") link: String,
    @Assisted("isFavorite") private val isFavorite: Boolean,
    private val vaultDao: VaultDao
): ViewModel() {
    private val _state = MutableStateFlow(
        VaultCreationUIState(
            serviceText = service,
            identifierText = username,
            passwordText = password,
            linkText = link,
            currentImageId = imageId,
        )
    )
    val state = _state.asStateFlow()

    fun onVaultEditionAction(action: VaultCreationUIAction) {
        when(action) {
            is VaultCreationUIAction.OnNavAction -> {
                action.navController.popBackStack()
            }
            is VaultCreationUIAction.OnSaveAction -> {
                _state.update {
                    it.copy(
                        isServiceError = it.serviceText.isBlank(),
                        isIdentifierError = it.identifierText.isBlank(),
                        isPasswordError = it.passwordText.isBlank()
                    )
                }
                if (!_state.value.isServiceError && !_state.value.isIdentifierError && !_state.value.isPasswordError) {
                    viewModelScope.launch {
                        vaultDao.update(
                            Vault(
                                id = id,
                                imageId = _state.value.currentImageId,
                                service = _state.value.serviceText,
                                identifier = _state.value.identifierText,
                                password = _state.value.passwordText,
                                link = _state.value.linkText,
                                isFavorite = isFavorite
                            )
                        )
                    }
                    action.navController.popBackStack()
                } else {
                    Toast.makeText(action.context, action.toastText, Toast.LENGTH_SHORT).show()
                }
            }
            is VaultCreationUIAction.OnServiceValueChange -> {
                _state.update { it.copy(serviceText = action.text) }
            }
            is VaultCreationUIAction.OnIdentifierValueChange -> {
                _state.update { it.copy(identifierText = action.text) }
            }
            is VaultCreationUIAction.OnLinkValueChange -> {
                _state.update { it.copy(linkText = action.text) }
            }
            is VaultCreationUIAction.OnPasswordValueChange -> {
                _state.update { it.copy(passwordText = action.text) }
            }
            is VaultCreationUIAction.OnServiceTrailingIconClickAction -> {
                _state.update { it.copy(serviceText = "") }
            }
            is VaultCreationUIAction.OnIdentifierTrailingIconClickAction -> {
                _state.update { it.copy(identifierText = "") }
            }
            is VaultCreationUIAction.OnLinkTrailingIconClickAction -> {
                _state.update { it.copy(linkText = "") }
            }
            is VaultCreationUIAction.OnPasswordTrailingIconClickAction -> {
                _state.update { it.copy(isPasswordDialogOpened = true) }
            }
            is VaultCreationUIAction.OnImageClickAction -> {
                _state.update { it.copy(currentImageId = action.id) }
            }
            is VaultCreationUIAction.OnSearchBlockExpansionChange -> {
                _state.update { it.copy(isSearchBlockExpanded = !it.isSearchBlockExpanded) }
            }
            is VaultCreationUIAction.OnSearchBlockVisualChange -> {
                _state.update { it.copy(isSearchBlockVisible = !it.isSearchBlockVisible) }
            }
            is VaultCreationUIAction.OnSearchClickAction -> {
                action.focusManager.clearFocus()
            }
            is VaultCreationUIAction.OnSearchValueChange -> {
                val searchText = action.text.lowercase()

                val filteredHashMap = if (searchText.isBlank()) {
                    VAULT_IMAGE_HASHMAP
                } else {
                    VAULT_IMAGE_HASHMAP.filterKeys { it.lowercase().startsWith(searchText) }
                }

                _state.update { it.copy(imageHashMap = HashMap(filteredHashMap), searchText = action.text) }
            }
            is VaultCreationUIAction.OnNumberSwitchClickAction -> {
                _state.update { it.copy(isNumberActive = action.value) }
            }
            is VaultCreationUIAction.OnSpecialSignSwitchClickAction -> {
                _state.update { it.copy(isSpecialSignActive = action.value) }
            }
            is VaultCreationUIAction.OnUppercaseLetterSwitchClickAction -> {
                _state.update { it.copy(isUppercaseLetterActive = action.value) }
            }
            is VaultCreationUIAction.OnPasswordDialogDismissRequest -> {
                _state.update { it.copy(isPasswordDialogOpened = false) }
            }
            is VaultCreationUIAction.OnSliderValueChange -> {
                _state.update { it.copy(sliderValue = action.value) }
            }
            is VaultCreationUIAction.OnGenerateClickAction -> {
                _state.update {
                    it.copy(
                        passwordText = PasswordGenerator.getPassword(
                            it.sliderValue.toInt(),
                            it.isNumberActive,
                            it.isSpecialSignActive,
                            it.isUppercaseLetterActive
                        ),
                        isPasswordDialogOpened = false
                    )
                }
            }
        }
    }
}

@AssistedFactory
interface VaultEditionViewModelFactory {
    fun create(
        @Assisted("id") id: Long,
        @Assisted("imageId") imageId: Int,
        @Assisted("service") service: String,
        @Assisted("username") username: String,
        @Assisted("password") password: String,
        @Assisted("link") link: String,
        @Assisted("isFavorite") isFavorite: Boolean,
    ): VaultEditionViewModel
}