package com.sean.cryptovault.f_base.presentation.ui_state

data class ButtonState(
    val text: String,
    val isPressed: Boolean,
    val onClickAction: () -> Unit
)
