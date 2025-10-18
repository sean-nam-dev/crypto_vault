package com.sean.cryptovault.f_base.presentation.ui_screen

import android.content.ClipboardManager
import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.sean.cryptovault.R
import com.sean.cryptovault.f_base.presentation.ui.PasswordGeneratorUI
import com.sean.cryptovault.f_base.presentation.ui_action.PasswordGeneratorUIAction
import com.sean.cryptovault.f_base.presentation.view_model.PasswordGeneratorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordGeneratorUIScreen(
    viewModel: PasswordGeneratorViewModel,
    onBurgerTopBarNavAction: () -> Unit
) {
    val passwordGeneratorUiState =  viewModel.state.collectAsState()

    val passwordText = stringResource(R.string.password)
    val successfullyCopied = stringResource(R.string.successfully_copied)

    val context = LocalContext.current
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    PasswordGeneratorUI(
        passwordGeneratorUIState = passwordGeneratorUiState.value,
        scrollBehavior = scrollBehavior,
        onBurgerTopBarNavAction = onBurgerTopBarNavAction,
        onSliderValueChange = { viewModel.onPasswordGeneratorAction(PasswordGeneratorUIAction.OnSliderValueChange(it)) },
        onIncludeButtonChange = { viewModel.onPasswordGeneratorAction(PasswordGeneratorUIAction.OnIncludeButtonChange(it)) },
        onCopyClickAction = {
            viewModel.onPasswordGeneratorAction(
                PasswordGeneratorUIAction.OnCopyClickAction(
                    clipboardManager = clipboard,
                    context = context,
                    label = passwordText,
                    toastText = successfullyCopied
                )
            )
        },
        onChangeClickAction = { viewModel.onPasswordGeneratorAction(PasswordGeneratorUIAction.OnChangeClickAction()) }
    )
}