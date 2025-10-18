package com.sean.cryptovault.f_settings.presentation.view_model

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.sean.cryptovault.core.common.SEPARATOR
import com.sean.cryptovault.core.navigation.ScreenNavigation
import com.sean.cryptovault.f_settings.presentation.ui_action.PINChangerUIAction
import com.sean.cryptovault.f_settings.presentation.ui_state.PINChangerUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PINChangerViewModel: ViewModel() {
    private val _state = MutableStateFlow(PINChangerUIState())
    val state = _state.asStateFlow()

    fun onPINChangerAction(action: PINChangerUIAction) {
        when(action) {
            is PINChangerUIAction.OnNavAction -> {
                action.navController.popBackStack()
            }
            is PINChangerUIAction.OnPINValueChange -> {
                if (action.text.length <= 4) {
                    _state.update { it.copy(pinValue = action.text) }
                }
            }
            is PINChangerUIAction.OnSecretWordValueChange -> {
                _state.update { it.copy(secretWordValue = action.text) }
            }
            is PINChangerUIAction.OnPINTrailingIconClickAction -> {
                if (_state.value.pinValue.length >= 4) {
                    _state.update { it.copy(isPINError = false) }
                    action.pinFM.assign(_state.value.pinValue.take(4))
                    action.startDestinationFM.assign(ScreenNavigation.PINCodeAuthorization.route)
                    Toast.makeText(action.context, action.correctText, Toast.LENGTH_SHORT).show()
                } else {
                    _state.update { it.copy(isPINError = true) }
                    Toast.makeText(action.context, action.lengthErrorText, Toast.LENGTH_SHORT).show()
                }
            }
            is PINChangerUIAction.OnSecretWordTrailingIconClickAction -> {
                if (_state.value.secretWordValue.trim().isBlank()) {
                    _state.update { it.copy(isSecretWordError = true) }
                    Toast.makeText(action.context, action.errorText, Toast.LENGTH_SHORT).show()
                } else {
                    _state.update { it.copy(isSecretWordError = false) }
                    action.secretWordFM.assign(
                        action
                            .secretWordFM
                            .read()
                            .split(SEPARATOR)
                            .first() + SEPARATOR + _state.value.secretWordValue.trim()
                    )
                    Toast.makeText(action.context, action.correctText, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}