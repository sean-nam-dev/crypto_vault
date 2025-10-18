package com.sean.cryptovault.f_settings.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.FileManager
import com.sean.cryptovault.core.common.SEPARATOR
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.component.CustomTextField
import com.sean.cryptovault.core.component.TopAppBarFrame
import com.sean.cryptovault.f_settings.presentation.ui_state.PINChangerUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PINChangerUI(
    pinChangerUIState: PINChangerUIState,
    scrollBehavior: TopAppBarScrollBehavior,
    pinFM: FileManager,
    secretWordFM: FileManager,
    onNavAction: () -> Unit,
    onPINValueChange: (String) -> Unit,
    onSecretWordValueChange: (String) -> Unit,
    onPINTrailingIconClickAction: () -> Unit,
    onSecretWordTrailingIconClickAction: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarFrame(
                title = stringResource(R.string.pin_and_secret_word),
                navIconId = R.drawable.ic_round_arrow_back,
                onNavAction = onNavAction,
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = getDp(R.dimen.pin_changer_ui_screen_padding)),
            verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.pin_changer_ui_screen_padding))
        ) {
            CustomTextField(
                value = pinChangerUIState.pinValue,
                label = stringResource(R.string.pin_code),
                placeholder = pinFM.read(),
                trailingIcon = R.drawable.ic_round_check,
                isError = pinChangerUIState.isPINError,
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done
                ),
                onValueChange = onPINValueChange,
                onTrailingIconClickAction = onPINTrailingIconClickAction
            )
            CustomTextField(
                value = pinChangerUIState.secretWordValue,
                label = stringResource(R.string.secret_word),
                placeholder = secretWordFM.read().split(SEPARATOR).last(),
                trailingIcon = R.drawable.ic_round_check,
                isError = pinChangerUIState.isSecretWordError,
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Done
                ),
                onValueChange = onSecretWordValueChange,
                onTrailingIconClickAction = onSecretWordTrailingIconClickAction
            )
        }
    }
}