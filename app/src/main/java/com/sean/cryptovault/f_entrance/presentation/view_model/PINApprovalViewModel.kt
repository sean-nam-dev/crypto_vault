package com.sean.cryptovault.f_entrance.presentation.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sean.cryptovault.core.navigation.ScreenNavigation
import com.sean.cryptovault.f_base.common.PINDefaultAction
import com.sean.cryptovault.f_entrance.presentation.ui_action.PINUIAction
import com.sean.cryptovault.f_entrance.presentation.ui_state.PINUIState

class PINApprovalViewModel: ViewModel(), PINDefaultAction {
    var pinUIState = mutableStateOf(PINUIState(""))

    fun onPINApprovalAction(action: PINUIAction) {
        when(action) {
            is PINUIAction.OnPINChange -> {
                pinUIState.value = onPINChange(pinUIState.value, action.pin)

                if (pinUIState.value.pin.length == 4) {
                    if (pinUIState.value.pin == action.originalPIN) {
                        action
                            .navController
                            .navigate(ScreenNavigation.SecretWordCreation.withArgs(pinUIState.value.pin)) {
                                popUpTo(ScreenNavigation.PINCodeCreation.route)
                            }
                    } else {
                        action.navController.popBackStack()
                    }
                }
            }
            is PINUIAction.OnBiometricUse -> { /*No biometric authentication*/ }
            is PINUIAction.OnBackSpace -> { pinUIState.value = onBackSpace(pinUIState.value) }
        }
    }
}