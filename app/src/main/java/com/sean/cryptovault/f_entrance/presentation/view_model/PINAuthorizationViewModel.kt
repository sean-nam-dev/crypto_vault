package com.sean.cryptovault.f_entrance.presentation.view_model

import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sean.cryptovault.f_entrance.presentation.ui_action.PINAuthorizationUIAction
import com.sean.cryptovault.f_base.common.PINDefaultAction
import com.sean.cryptovault.f_entrance.presentation.ui_state.PINUIState
import com.sean.cryptovault.core.common.ENTRY_TRIES
import com.sean.cryptovault.core.common.FileManager
import com.sean.cryptovault.core.di.annotation.qualifier.PinFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.StartDestinationFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.TryCounterFMAnnotation
import com.sean.cryptovault.core.navigation.ScreenNavigation
import javax.inject.Inject

class PINAuthorizationViewModel @Inject constructor(
    @PinFMAnnotation private val pinFM: FileManager,
    @StartDestinationFMAnnotation private val startDestinationFM: FileManager,
    @TryCounterFMAnnotation private val tryCounterFM: FileManager
): ViewModel(), PINDefaultAction {
    var pinUIState = mutableStateOf(PINUIState(""))

    fun onPINAuthorizationAction(action: PINAuthorizationUIAction) {
        when(action) {
            is PINAuthorizationUIAction.OnPINChange -> {
                pinUIState.value = onPINChange(pinUIState.value, action.pin)

                if (pinUIState.value.pin.length == 4) {
                    if (tryCounterFM.read().toInt() > 1) {
                        if (pinFM.read() == pinUIState.value.pin) {
                            tryCounterFM.assign(ENTRY_TRIES)
                            action.navController.navigate(ScreenNavigation.Base.route) {
                                popUpTo(ScreenNavigation.Entrance.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            tryCounterFM.assign((tryCounterFM.read().toInt() - 1).toString())
                            Toast
                                .makeText(action.context, action.warning + tryCounterFM.read(), Toast.LENGTH_SHORT)
                                .show()
                            pinUIState.value = pinUIState.value.copy(pin = "")
                        }
                    } else {
                        startDestinationFM.assign(ScreenNavigation.SecretWordAuthorization.route)
                        tryCounterFM.assign(ENTRY_TRIES)
                        action
                            .navController
                            .navigate(ScreenNavigation.SecretWordAuthorization.route) {
                                popUpTo(ScreenNavigation.PINCodeAuthorization.route) {
                                    inclusive = true
                                }
                            }
                    }
                }
            }
            is PINAuthorizationUIAction.OnBiometricUse -> {
                val promptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle(action.title)
                    .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
                    .setNegativeButtonText(action.negativeButtonText)
                    .build()
                val biometricPrompt = BiometricPrompt(
                    action.activity,
                    action.executor,
                    object : BiometricPrompt.AuthenticationCallback() {
                        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                            super.onAuthenticationSucceeded(result)

                            tryCounterFM.assign(ENTRY_TRIES)
                            action.navController.navigate(ScreenNavigation.Base.route) {
                                popUpTo(ScreenNavigation.Entrance.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                )
                biometricPrompt.authenticate(promptInfo)
            }
            is PINAuthorizationUIAction.OnBackSpace -> { pinUIState.value = onBackSpace(pinUIState.value) }
        }
    }
}