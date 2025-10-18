package com.sean.cryptovault.f_entrance.presentation.view_model

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sean.cryptovault.core.common.FileManager
import com.sean.cryptovault.f_entrance.presentation.ui_action.SecretWordAuthorizationUIAction
import com.sean.cryptovault.f_entrance.presentation.ui_state.SecretWordAuthorizationUIState
import com.sean.cryptovault.core.common.SEPARATOR
import com.sean.cryptovault.core.di.annotation.qualifier.SecretWordFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.StartDestinationFMAnnotation
import com.sean.cryptovault.core.navigation.ScreenNavigation
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SecretWordAuthorizationViewModel @AssistedInject constructor(
    @Assisted question: String,
    @StartDestinationFMAnnotation private val startDestinationFM: FileManager,
    @SecretWordFMAnnotation private val secretWordFM: FileManager
): ViewModel() {
    var secretWordAuthorizationUIState = mutableStateOf(
        SecretWordAuthorizationUIState(
            question = question,
            answer = "",
            isDialogActive = false
        )
    )

    fun onSecretWordAction(action: SecretWordAuthorizationUIAction) {
        when(action) {
            is SecretWordAuthorizationUIAction.OnAnswerValueChange -> {
                secretWordAuthorizationUIState.value = secretWordAuthorizationUIState.value.copy(answer = action.text)
            }
            is SecretWordAuthorizationUIAction.OnDoneAction -> {
                action.focusManager.clearFocus()
            }
            is SecretWordAuthorizationUIAction.OnRestoreButtonAction -> {
                val answer = secretWordFM.read().split(SEPARATOR).last()

                if (answer == secretWordAuthorizationUIState.value.answer.trim()) {
                    Toast.makeText(action.context, action.onSuccessText, Toast.LENGTH_SHORT).show()

                    action.navController.navigate(ScreenNavigation.Base.route) {
                        popUpTo(ScreenNavigation.Entrance.route) {
                            inclusive = true
                        }
                    }
                } else {
                    Toast.makeText(action.context, action.onFailureText, Toast.LENGTH_SHORT).show()
                }
            }
            is SecretWordAuthorizationUIAction.OnCreateButtonAction -> {
                secretWordAuthorizationUIState.value = secretWordAuthorizationUIState.value.copy(isDialogActive = true)
            }
            is SecretWordAuthorizationUIAction.OnDismissRequest -> {
                secretWordAuthorizationUIState.value = secretWordAuthorizationUIState.value.copy(isDialogActive = false)
            }
            is SecretWordAuthorizationUIAction.OnConfirmAction -> {
                startDestinationFM.assign(ScreenNavigation.PINCodeCreation.route)
                action.navController.navigate(ScreenNavigation.PINCodeCreation.route) {
                    popUpTo(ScreenNavigation.SecretWordAuthorization.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

@AssistedFactory
interface SecretWordAuthorizationViewModelFactory {
    fun create(question: String): SecretWordAuthorizationViewModel
}