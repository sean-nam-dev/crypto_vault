package com.sean.cryptovault.f_entrance.presentation.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sean.cryptovault.f_base.common.PINDefaultAction
import com.sean.cryptovault.f_entrance.presentation.ui_action.PINUIAction
import com.sean.cryptovault.f_entrance.presentation.ui_state.PINUIState
import com.sean.cryptovault.core.navigation.ScreenNavigation

class PINCreationViewModel: ViewModel(), PINDefaultAction {
    var pinUIState = mutableStateOf(PINUIState(""))

    fun onPINCreationAction(action: PINUIAction) {
        when(action) {
            is PINUIAction.OnPINChange -> {
                pinUIState.value = onPINChange(pinUIState.value, action.pin)

                if (pinUIState.value.pin.length == 4) {
                    action
                        .navController
                        .navigate(ScreenNavigation.PINCodeApproval.withArgs(pinUIState.value.pin))

                    pinUIState.value = pinUIState.value.copy(pin = "")
                }
            }
            is PINUIAction.OnBiometricUse -> { /*No biometric authentication*/ }
            is PINUIAction.OnBackSpace -> { pinUIState.value = onBackSpace(pinUIState.value) }
        }
    }
}