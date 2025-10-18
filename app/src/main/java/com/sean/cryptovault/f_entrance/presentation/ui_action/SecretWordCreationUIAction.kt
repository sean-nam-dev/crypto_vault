package com.sean.cryptovault.f_entrance.presentation.ui_action

import android.content.Context
import androidx.compose.ui.focus.FocusManager
import androidx.navigation.NavController

sealed class SecretWordCreationUIAction {
    data class OnDropDownMenuCreationUIAction(val action: Unit): SecretWordCreationUIAction()
    data class OnAnswerValueChange(val text: String): SecretWordCreationUIAction()
    data class OnAnswerBlockDoneCreationUIAction(val focusManager: FocusManager): SecretWordCreationUIAction()
    data class OnMenuDismissRequest(val action: Unit): SecretWordCreationUIAction()
    data class OnMenuClickCreationUIAction(
        val questionList: Array<String>,
        val index: Int
    ): SecretWordCreationUIAction() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as OnMenuClickCreationUIAction

            if (!questionList.contentEquals(other.questionList)) return false
            return index == other.index
        }

        override fun hashCode(): Int {
            var result = questionList.contentHashCode()
            result = 31 * result + index
            return result
        }
    }

    data class OnBiometricChange(val value: Boolean): SecretWordCreationUIAction()
    data class OnSecretWordConfirmButtonAction(
        val context: Context,
        val warning: String,
        val navController: NavController,
        val confirmedPIN: String
    ): SecretWordCreationUIAction()
}