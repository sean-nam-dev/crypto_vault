package com.sean.cryptovault.f_entrance.presentation.view_model

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sean.cryptovault.core.common.FileManager
import com.sean.cryptovault.f_entrance.presentation.ui_action.SecretWordCreationUIAction
import com.sean.cryptovault.f_entrance.presentation.ui_state.SecretWordCreationUIState
import com.sean.cryptovault.core.common.SEPARATOR
import com.sean.cryptovault.core.di.annotation.qualifier.BiometricAuthenticationFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.PinFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.SecretWordFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.StartDestinationFMAnnotation
import com.sean.cryptovault.core.navigation.ScreenNavigation
import javax.inject.Inject

class SecretWordCreationViewModel @Inject constructor(
    @PinFMAnnotation private val pinFM: FileManager,
    @SecretWordFMAnnotation private val secretWordFM: FileManager,
    @BiometricAuthenticationFMAnnotation private val biometricAuthenticationFM: FileManager,
    @StartDestinationFMAnnotation private val startDestinationFM: FileManager
): ViewModel() {
    var secretWordCreationUIState = mutableStateOf(
        SecretWordCreationUIState(
            question = "",
            index = 0,
            isMenuActive = false,
            answer = "",
            isBiometricEnabled = false
        )
    )

    fun onSecretWordAction(action: SecretWordCreationUIAction) {
        when(action) {
            is SecretWordCreationUIAction.OnDropDownMenuCreationUIAction -> {
                secretWordCreationUIState.value = secretWordCreationUIState
                    .value
                    .copy(isMenuActive = !secretWordCreationUIState.value.isMenuActive)
            }
            is SecretWordCreationUIAction.OnAnswerValueChange -> {
                secretWordCreationUIState.value = secretWordCreationUIState.value.copy(answer = action.text)
            }
            is SecretWordCreationUIAction.OnAnswerBlockDoneCreationUIAction -> {
                action.focusManager.clearFocus()
            }
            is SecretWordCreationUIAction.OnMenuDismissRequest -> {
                secretWordCreationUIState.value = secretWordCreationUIState.value.copy(isMenuActive = false)
            }
            is SecretWordCreationUIAction.OnMenuClickCreationUIAction -> {
                secretWordCreationUIState.value = secretWordCreationUIState.value.copy(
                    question = action.questionList[action.index],
                    index = action.index,
                    isMenuActive = false
                )
            }
            is SecretWordCreationUIAction.OnBiometricChange -> {
                secretWordCreationUIState.value = secretWordCreationUIState.value.copy(isBiometricEnabled = action.value)
            }
            is SecretWordCreationUIAction.OnSecretWordConfirmButtonAction -> {
                if (secretWordCreationUIState.value.question.isBlank() || secretWordCreationUIState.value.answer.isBlank()) {
                    Toast.makeText(action.context, action.warning, Toast.LENGTH_SHORT).show()
                } else {
                    pinFM.assign(action.confirmedPIN)
                    secretWordFM.assign(secretWordCreationUIState.value.index.toString() + SEPARATOR + secretWordCreationUIState.value.answer.trim())
                    biometricAuthenticationFM.assign(secretWordCreationUIState.value.isBiometricEnabled.toString())
                    startDestinationFM.assign(ScreenNavigation.PINCodeAuthorization.route)

                    action.navController.navigate(ScreenNavigation.Base.route) {
                        popUpTo(ScreenNavigation.Entrance.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}