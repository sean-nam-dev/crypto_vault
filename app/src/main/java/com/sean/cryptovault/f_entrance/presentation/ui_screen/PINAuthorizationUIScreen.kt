package com.sean.cryptovault.f_entrance.presentation.ui_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.f_entrance.presentation.ui_action.PINAuthorizationUIAction
import com.sean.cryptovault.f_entrance.presentation.ui.PINUserInterface
import com.sean.cryptovault.f_entrance.presentation.view_model.PINAuthorizationViewModel

@Composable
fun PINAuthorizationUIScreen(
    viewModel: PINAuthorizationViewModel,
    navController: NavController
) {
    val incorrectPINCodeText = stringResource(R.string.incorrect_pin_code_tries_remaining)
    val biometricAuthenticationText = stringResource(R.string.biometric_authentication)
    val warningText = stringResource(R.string.cancel)

    val context = LocalContext.current
    val activity = LocalContext.current as FragmentActivity
    val executor = ContextCompat.getMainExecutor(context)

    PINUserInterface(
        pinUIState = viewModel.pinUIState.value,
        text = stringResource(R.string.enter_pin_code),
        onPINChange = {
            viewModel.onPINAuthorizationAction(
                PINAuthorizationUIAction.OnPINChange(
                    pin = it,
                    warning = incorrectPINCodeText,
                    context = context,
                    navController = navController
                )
            )
        },
        onBiometricUse = {
            viewModel.onPINAuthorizationAction(
                PINAuthorizationUIAction.OnBiometricUse(
                    title = biometricAuthenticationText,
                    negativeButtonText = warningText,
                    activity = activity,
                    executor = executor,
                    navController = navController
                )
            )
        },
        onBackSpace = { viewModel.onPINAuthorizationAction(PINAuthorizationUIAction.OnBackSpace(Unit)) }
    )
}