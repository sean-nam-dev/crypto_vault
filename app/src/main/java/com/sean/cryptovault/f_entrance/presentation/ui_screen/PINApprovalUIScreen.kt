package com.sean.cryptovault.f_entrance.presentation.ui_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.f_entrance.presentation.ui_action.PINUIAction
import com.sean.cryptovault.f_entrance.presentation.ui.PINUserInterface
import com.sean.cryptovault.f_entrance.presentation.view_model.PINApprovalViewModel

@Composable
fun PINApprovalUIScreen(
    viewModel: PINApprovalViewModel,
    navController: NavController,
    originalPIN: String
) {
    PINUserInterface(
        pinUIState = viewModel.pinUIState.value,
        text = stringResource(R.string.re_enter_pin_code),
        onPINChange = { viewModel.onPINApprovalAction(PINUIAction.OnPINChange(it, originalPIN, navController)) },
        onBiometricUse = null,
        onBackSpace = { viewModel.onPINApprovalAction(PINUIAction.OnBackSpace(Unit)) }
    )
}


