package com.sean.cryptovault.f_base.presentation.ui_action

import android.content.Context
import androidx.navigation.NavController

sealed class CardCreationUIAction {
    data class OnNavAction(val navController: NavController): CardCreationUIAction()
    data class OnSaveAction(
        val navController: NavController,
        val context: Context,
        val toastText: String
    ): CardCreationUIAction()

    data class OnBankValueChange(val text: String): CardCreationUIAction()
    data class OnNumberValueChange(val text: String): CardCreationUIAction()
    data class OnCVVValueChange(val text: String): CardCreationUIAction()
    data class OnValidThruValueChange(val text: String): CardCreationUIAction()
    data class OnFullNameValueChange(val text: String): CardCreationUIAction()

    data class OnBankTrailingIconClickAction(val action: Unit = Unit): CardCreationUIAction()
    data class OnNumberTrailingIconClickAction(val action: Unit = Unit): CardCreationUIAction()
    data class OnCVVTrailingIconClickAction(val action: Unit = Unit): CardCreationUIAction()
    data class OnValidThruTrailingIconClickAction(val action: Unit = Unit): CardCreationUIAction()
    data class OnFullNameTrailingIconClickAction(val action: Unit = Unit): CardCreationUIAction()
}