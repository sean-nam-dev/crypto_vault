package com.sean.cryptovault.f_base.presentation.ui_state

data class CardCreationUIState(
    val bankText: String = "",
    val numberText: String = "",
    val validThruText: String = "",
    val cvvText: String = "",
    val fullNameText: String = "",
    val isBankError: Boolean = false,
    val isNumberError: Boolean = false,
    val isValidThruError: Boolean = false,
    val isFullNameError: Boolean = false,
)