package com.sean.cryptovault.f_base.presentation.ui_state

import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.VAULT_IMAGE_HASHMAP

data class VaultCreationUIState(
    val imageHashMap: HashMap<String, Int> = VAULT_IMAGE_HASHMAP,
    val serviceText: String = "",
    val identifierText: String = "",
    val linkText: String = "",
    val passwordText: String = "",
    val searchText: String = "",
    val currentImageId: Int = R.drawable.logo_google,
    val sliderValue: Float = 12f,
    val isSearchBlockVisible: Boolean = false,
    val isSearchBlockExpanded: Boolean = false,
    val isServiceError: Boolean = false,
    val isIdentifierError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isPasswordDialogOpened: Boolean = false,
    val isNumberActive: Boolean = true,
    val isSpecialSignActive: Boolean = true,
    val isUppercaseLetterActive: Boolean = true
)