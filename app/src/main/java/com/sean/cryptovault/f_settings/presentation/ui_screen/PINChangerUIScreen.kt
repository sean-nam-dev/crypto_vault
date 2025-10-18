package com.sean.cryptovault.f_settings.presentation.ui_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.FileManager
import com.sean.cryptovault.f_settings.presentation.ui.PINChangerUI
import com.sean.cryptovault.f_settings.presentation.ui_action.PINChangerUIAction
import com.sean.cryptovault.f_settings.presentation.view_model.PINChangerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PINChangerUIScreen(
    viewModel: PINChangerViewModel,
    navController: NavController,
    pinFM: FileManager,
    secretWordFM: FileManager,
    startDestinationFM: FileManager
) {
    val pinChangerUIState = viewModel.state.collectAsState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current

    val pinCodeMustContainOnlyFourText = stringResource(R.string.pin_code_must_contain_only_4_digits)
    val newPINCodeWasSuccessfully = stringResource(R.string.new_pin_code_was_successfully_saved)
    val secretWordMustContain = stringResource(R.string.secret_word_must_contain_at_least)
    val newSecretWordWasSuccessfully = stringResource(R.string.new_secret_word_was_successfully_saved)

    PINChangerUI(
        pinChangerUIState = pinChangerUIState.value,
        scrollBehavior = scrollBehavior,
        pinFM = pinFM,
        secretWordFM = secretWordFM,
        onNavAction = { viewModel.onPINChangerAction(PINChangerUIAction.OnNavAction(navController)) },
        onPINValueChange = { viewModel.onPINChangerAction(PINChangerUIAction.OnPINValueChange(it)) },
        onSecretWordValueChange = { viewModel.onPINChangerAction(PINChangerUIAction.OnSecretWordValueChange(it)) },
        onPINTrailingIconClickAction = {
            viewModel.onPINChangerAction(
                PINChangerUIAction.OnPINTrailingIconClickAction(
                    pinFM = pinFM,
                    startDestinationFM = startDestinationFM,
                    context = context,
                    lengthErrorText = pinCodeMustContainOnlyFourText,
                    correctText = newPINCodeWasSuccessfully
                )
            )
        },
        onSecretWordTrailingIconClickAction = {
            viewModel.onPINChangerAction(
                PINChangerUIAction.OnSecretWordTrailingIconClickAction(
                    secretWordFM = secretWordFM,
                    context = context,
                    errorText = secretWordMustContain,
                    correctText = newSecretWordWasSuccessfully
                )
            )
        }
    )
}







