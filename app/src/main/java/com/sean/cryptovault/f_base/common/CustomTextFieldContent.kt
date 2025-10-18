package com.sean.cryptovault.f_base.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.VisualTransformation

data class CustomTextFieldContent(
    val value: String,
    val label: String,
    val placeholder: String,
    val trailingIcon: Int,
    val isError: Boolean,
    val keyboardOptions: KeyboardOptions,
    val visualTransformation: VisualTransformation,
    val onValueChange: (String) -> Unit,
    val onTrailingIconClickAction: () -> Unit
)