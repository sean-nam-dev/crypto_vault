package com.sean.cryptovault.f_base.presentation.ui_state

import com.sean.cryptovault.f_base.common.PasswordGenerator

data class PasswordGeneratorUIState(
    val password: String = PasswordGenerator
        .getPassword(
            length = 12,
            isDigitIncluded = false,
            isSpecialSignIncluded = false,
            isUppercaseLetterIncluded = false
        ),
    val passwordLength: Int = 12,
    val isDigitIncluded: Boolean = false,
    val isSignIncluded: Boolean = false,
    val isUppercaseIncluded: Boolean = false
)
