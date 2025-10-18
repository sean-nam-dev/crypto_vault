package com.sean.cryptovault.f_base.presentation.ui_action

import android.content.Context
import androidx.compose.ui.focus.FocusManager
import androidx.navigation.NavController

sealed class VaultCreationUIAction {
    data class OnNavAction(val navController: NavController): VaultCreationUIAction()
    data class OnSaveAction(
        val navController: NavController,
        val context: Context,
        val toastText: String
    ): VaultCreationUIAction()

    data class OnServiceValueChange(val text: String): VaultCreationUIAction()
    data class OnIdentifierValueChange(val text: String): VaultCreationUIAction()
    data class OnLinkValueChange(val text: String): VaultCreationUIAction()
    data class OnPasswordValueChange(val text: String): VaultCreationUIAction()
    data class OnServiceTrailingIconClickAction(val action: Unit = Unit): VaultCreationUIAction()
    data class OnIdentifierTrailingIconClickAction(val action: Unit = Unit): VaultCreationUIAction()
    data class OnLinkTrailingIconClickAction(val action: Unit = Unit): VaultCreationUIAction()
    data class OnPasswordTrailingIconClickAction(val action: Unit = Unit): VaultCreationUIAction()

    data class OnSearchValueChange(val text: String): VaultCreationUIAction()
    data class OnSearchBlockVisualChange(val action: Unit = Unit): VaultCreationUIAction()
    data class OnSearchClickAction(val focusManager: FocusManager): VaultCreationUIAction()
    data class OnSearchBlockExpansionChange(val action: Unit = Unit): VaultCreationUIAction()
    data class OnImageClickAction(val id: Int): VaultCreationUIAction()

    data class OnNumberSwitchClickAction(val value: Boolean): VaultCreationUIAction()
    data class OnSpecialSignSwitchClickAction(val value: Boolean): VaultCreationUIAction()
    data class OnUppercaseLetterSwitchClickAction(val value: Boolean): VaultCreationUIAction()
    data class OnPasswordDialogDismissRequest(val action: Unit = Unit): VaultCreationUIAction()
    data class OnSliderValueChange(val value: Float): VaultCreationUIAction()
    data class OnGenerateClickAction(val action: Unit = Unit): VaultCreationUIAction()
}