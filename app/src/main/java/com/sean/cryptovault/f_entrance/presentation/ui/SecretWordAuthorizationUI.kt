package com.sean.cryptovault.f_entrance.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.sean.cryptovault.R
import com.sean.cryptovault.f_entrance.presentation.component.AnswerBlock
import com.sean.cryptovault.f_entrance.presentation.component.BorderedSecretWordButton
import com.sean.cryptovault.f_entrance.presentation.component.SecretWordButton
import com.sean.cryptovault.f_entrance.presentation.ui_state.SecretWordAuthorizationUIState
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun SecretWordAuthorizationUI(
    secretWordAuthorizationUIState: SecretWordAuthorizationUIState,
    onAnswerValueChange: (String) -> Unit,
    onDoneAction: () -> Unit,
    onRestoreButtonAction: () -> Unit,
    onCreateButtonAction: () -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(getDp(R.dimen.secret_word_block_padding)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceTint,
                    shape = RoundedCornerShape(10)
                )
                .padding(getDp(R.dimen.secret_word_block_padding)),
            verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.secret_word_block_padding))
        ) {
            Text(
                text = secretWordAuthorizationUIState.question,
                fontSize = getSp(R.dimen.twenty_four_sp),
                color = MaterialTheme.colorScheme.primary,
                fontFamily = FontFamily(Font(R.font.titillium_web_semi_bold))
            )
            AnswerBlock(
                value = secretWordAuthorizationUIState.answer,
                ignoredOnValueChange = onAnswerValueChange,
                onDoneAction = onDoneAction
            )
        }
        if (secretWordAuthorizationUIState.isDialogActive) {
            AlertDialog(
                onDismissRequest = onDismissRequest,
                confirmButton = {
                    TextButton(
                        onClick = onConfirmAction,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.ok),
                            fontSize = getSp(R.dimen.twenty_sp),
                            fontFamily = FontFamily(Font(R.font.titillium_web_semi_bold))
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = onDismissRequest,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            fontSize = getSp(R.dimen.twenty_sp),
                            fontFamily = FontFamily(Font(R.font.titillium_web_semi_bold))
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.are_you_sure),
                        fontSize = getSp(R.dimen.twenty_four_sp),
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = FontFamily(Font(R.font.titillium_web_bold))
                    )
                },
                text = {
                    Text(
                        text = stringResource(R.string.all_data_will_be_cleared),
                        fontSize = getSp(R.dimen.twenty_sp),
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = FontFamily(Font(R.font.titillium_web_regular))
                    )
                },
                containerColor = MaterialTheme.colorScheme.surfaceTint
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.secret_word_block_padding))) {
            BorderedSecretWordButton(
                title = stringResource(R.string.restore_account),
                action = onRestoreButtonAction
            )
            SecretWordButton(
                title = stringResource(R.string.create_new_account),
                action = onCreateButtonAction
            )
        }
    }
}