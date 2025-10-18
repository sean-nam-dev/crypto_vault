package com.sean.cryptovault.f_entrance.presentation.ui_action

import android.content.Context
import androidx.compose.ui.focus.FocusManager
import androidx.navigation.NavController

sealed class SecretWordAuthorizationUIAction {
    data class OnAnswerValueChange(val text: String): SecretWordAuthorizationUIAction()
    data class OnDoneAction(val focusManager: FocusManager): SecretWordAuthorizationUIAction()
    data class OnRestoreButtonAction(
        val navController: NavController,
        val context: Context,
        val onSuccessText: String,
        val onFailureText: String
    ): SecretWordAuthorizationUIAction()
    data class OnCreateButtonAction(val action: Unit = Unit): SecretWordAuthorizationUIAction()
    data class OnDismissRequest(val action: Unit = Unit):  SecretWordAuthorizationUIAction()
    data class OnConfirmAction(val navController: NavController): SecretWordAuthorizationUIAction()
}