package com.sean.cryptovault.f_entrance.presentation.ui_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.f_entrance.presentation.ui.SecretWordAuthorizationUI
import com.sean.cryptovault.f_entrance.presentation.ui_action.SecretWordAuthorizationUIAction
import com.sean.cryptovault.f_entrance.presentation.view_model.SecretWordAuthorizationViewModel

@Composable
fun SecretWordAuthorizationUIScreen(
    viewModel: SecretWordAuthorizationViewModel,
    navController: NavController
) {
    val youCanChangeText = stringResource(R.string.you_can_change_pin_code)
    val wrongAnswerText = stringResource(R.string.wrong_answer)

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    SecretWordAuthorizationUI(
        secretWordAuthorizationUIState = viewModel.secretWordAuthorizationUIState.value,
        onAnswerValueChange = { viewModel.onSecretWordAction(SecretWordAuthorizationUIAction.OnAnswerValueChange(it)) },
        onDoneAction = { viewModel.onSecretWordAction(SecretWordAuthorizationUIAction.OnDoneAction(focusManager)) },
        onRestoreButtonAction = {
            viewModel.onSecretWordAction(
                SecretWordAuthorizationUIAction.OnRestoreButtonAction(
                    navController = navController,
                    context = context,
                    onSuccessText = youCanChangeText,
                    onFailureText = wrongAnswerText
                )
            )
        },
        onCreateButtonAction = { viewModel.onSecretWordAction(SecretWordAuthorizationUIAction.OnCreateButtonAction()) },
        onDismissRequest = { viewModel.onSecretWordAction(SecretWordAuthorizationUIAction.OnDismissRequest()) },
        onConfirmAction = {
            viewModel.onSecretWordAction(
                SecretWordAuthorizationUIAction.OnConfirmAction(navController = navController)
            )
        }
    )
}