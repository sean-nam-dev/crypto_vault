package com.sean.cryptovault.f_base.common

import com.sean.cryptovault.f_entrance.presentation.ui_state.PINUIState

interface PINDefaultAction {
    fun onPINChange(pinUIState: PINUIState, value: String) = pinUIState.copy(pin = pinUIState.pin + value)
    fun onBackSpace(pinUIState: PINUIState): PINUIState {
        return if (pinUIState.pin.isNotBlank()) {
            pinUIState.copy(pin = pinUIState.pin.dropLast(1))
        } else {
            pinUIState
        }
    }
}