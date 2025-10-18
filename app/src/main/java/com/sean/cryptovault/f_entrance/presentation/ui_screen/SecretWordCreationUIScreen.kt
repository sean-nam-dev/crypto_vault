package com.sean.cryptovault.f_entrance.presentation.ui_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.f_entrance.presentation.ui.SecretWordCreationUI
import com.sean.cryptovault.f_entrance.presentation.ui_action.SecretWordCreationUIAction
import com.sean.cryptovault.f_entrance.presentation.view_model.SecretWordCreationViewModel

@Composable
fun SecretWordCreationUIScreen(
    viewModel: SecretWordCreationViewModel,
    navController: NavController,
    confirmedPIN: String,
    questionList: Array<String>
) {
    val warningText = stringResource(R.string.question_answer_field_is_blank)

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    SecretWordCreationUI(
        secretWordCreationUIState = viewModel.secretWordCreationUIState.value,
        questionList = questionList,
        onDropDownMenuAction = { viewModel.onSecretWordAction(SecretWordCreationUIAction.OnDropDownMenuCreationUIAction(Unit)) },
        onAnswerValueChange = { viewModel.onSecretWordAction(SecretWordCreationUIAction.OnAnswerValueChange(it)) },
        onAnswerBlockDoneAction = { viewModel.onSecretWordAction(SecretWordCreationUIAction.OnAnswerBlockDoneCreationUIAction(focusManager)) },
        onMenuDismissRequest = { viewModel.onSecretWordAction(SecretWordCreationUIAction.OnMenuDismissRequest(Unit)) },
        onMenuClickAction = {
            viewModel.onSecretWordAction(
                SecretWordCreationUIAction.OnMenuClickCreationUIAction(
                    questionList = questionList,
                    index = it
                )
            )
        },
        onBiometricChange = { viewModel.onSecretWordAction(SecretWordCreationUIAction.OnBiometricChange(it)) },
        onSecretWordConfirmButtonAction = {
            viewModel.onSecretWordAction(
                SecretWordCreationUIAction.OnSecretWordConfirmButtonAction(
                    context = context,
                    warning = warningText,
                    navController = navController,
                    confirmedPIN = confirmedPIN
                )
            )
        }
    )
}