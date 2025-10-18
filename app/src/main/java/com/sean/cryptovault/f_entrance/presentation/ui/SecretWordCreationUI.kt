package com.sean.cryptovault.f_entrance.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sean.cryptovault.R
import com.sean.cryptovault.f_entrance.presentation.component.AnswerBlock
import com.sean.cryptovault.f_entrance.presentation.component.AuthenticationBlock
import com.sean.cryptovault.f_entrance.presentation.component.QuestionBlock
import com.sean.cryptovault.f_entrance.presentation.component.SecretDropDownMenu
import com.sean.cryptovault.f_entrance.presentation.component.SecretWordButton
import com.sean.cryptovault.f_entrance.presentation.ui_state.SecretWordCreationUIState
import com.sean.cryptovault.core.common.getDp

@Composable
fun SecretWordCreationUI(
    secretWordCreationUIState: SecretWordCreationUIState,
    questionList: Array<String>,
    onDropDownMenuAction: () -> Unit,
    onAnswerValueChange: (String) -> Unit,
    onAnswerBlockDoneAction: () -> Unit,
    onMenuDismissRequest: () -> Unit,
    onMenuClickAction: (Int) -> Unit,
    onBiometricChange: (Boolean) -> Unit,
    onSecretWordConfirmButtonAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(getDp(R.dimen.secret_word_block_padding)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.secret_word_block_padding))) {
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surfaceTint,
                        shape = RoundedCornerShape(10)
                    )
                    .padding(getDp(R.dimen.secret_word_block_padding))
            ) {
                QuestionBlock(
                    text = secretWordCreationUIState.question,
                    isMenuActive =secretWordCreationUIState.isMenuActive,
                    onClickAction = onDropDownMenuAction
                )
                AnswerBlock(
                    value = secretWordCreationUIState.answer,
                    ignoredOnValueChange = onAnswerValueChange,
                    onDoneAction = onAnswerBlockDoneAction
                )
                SecretDropDownMenu(
                    isMenuActive = secretWordCreationUIState.isMenuActive,
                    onDismissRequest = onMenuDismissRequest,
                    questionList = questionList,
                    onMenuClickAction = onMenuClickAction
                )
            }
            AuthenticationBlock(
                isBiometricActivated = secretWordCreationUIState.isBiometricEnabled,
                onBiometricChange = onBiometricChange
            )
        }
        SecretWordButton(
            title = stringResource(R.string.confirm),
            action = onSecretWordConfirmButtonAction
        )
    }
}