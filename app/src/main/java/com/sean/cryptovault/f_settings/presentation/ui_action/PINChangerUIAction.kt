package com.sean.cryptovault.f_settings.presentation.ui_action

import android.content.Context
import androidx.navigation.NavController
import com.sean.cryptovault.core.common.FileManager

sealed class PINChangerUIAction {
    data class OnNavAction(val navController: NavController): PINChangerUIAction()
    data class OnPINValueChange(val text: String): PINChangerUIAction()
    data class OnSecretWordValueChange(val text: String): PINChangerUIAction()
    data class OnPINTrailingIconClickAction(
        val pinFM: FileManager,
        val startDestinationFM: FileManager,
        val context: Context,
        val lengthErrorText: String,
        val correctText: String
    ): PINChangerUIAction()
    data class OnSecretWordTrailingIconClickAction(
        val secretWordFM: FileManager,
        val context: Context,
        val errorText: String,
        val correctText: String
    ): PINChangerUIAction()
}