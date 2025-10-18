package com.sean.cryptovault.f_entrance.presentation.ui_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.f_entrance.presentation.ui_action.PINUIAction
import com.sean.cryptovault.f_entrance.presentation.ui.PINUserInterface
import com.sean.cryptovault.f_entrance.presentation.view_model.PINCreationViewModel

@Composable
fun PINCreationUIScreen(
    viewModel: PINCreationViewModel,
    navController: NavController
) {
    PINUserInterface(
        text = stringResource(R.string.create_pin_code),
        pinUIState = viewModel.pinUIState.value,
        onPINChange = { viewModel.onPINCreationAction(PINUIAction.OnPINChange(it, "", navController)) },
        onBiometricUse = null,
        onBackSpace = { viewModel.onPINCreationAction(PINUIAction.OnBackSpace(Unit)) }
    )
}