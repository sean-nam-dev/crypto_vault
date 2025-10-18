package com.sean.cryptovault.f_base.presentation.ui_action

import android.content.ClipboardManager
import android.content.Context

sealed class PasswordGeneratorUIAction {
    data class OnSliderValueChange(val value: Float): PasswordGeneratorUIAction()
    data class OnIncludeButtonChange(val index: Int): PasswordGeneratorUIAction()
    data class OnCopyClickAction(
        val clipboardManager: ClipboardManager,
        val context: Context,
        val label: String,
        val toastText: String
    ): PasswordGeneratorUIAction()
    data class OnChangeClickAction(val action: Unit = Unit): PasswordGeneratorUIAction()
}