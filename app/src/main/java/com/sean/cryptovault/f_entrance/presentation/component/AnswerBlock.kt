package com.sean.cryptovault.f_entrance.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun AnswerBlock(
    value: String,
    ignoredOnValueChange: (String) -> Unit,
    onDoneAction: () -> Unit
) {
    TextField(
        value = value,
        onValueChange = { ignoredOnValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(
            fontSize = getSp(R.dimen.twenty_sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.answer),
                fontSize = getSp(R.dimen.twenty_sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { onDoneAction() }),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}


@Preview
@Composable
private fun AnswerBlockPreview() {
    var value by remember { mutableStateOf("") }

    CryptoVaultTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .background(MaterialTheme.colorScheme.surfaceTint)
                    .padding(20.dp)
            ) {
                QuestionBlock(text = "Question", isMenuActive = true) {

                }
                AnswerBlock(
                    value = value,
                    ignoredOnValueChange = { value = it },
                    onDoneAction = {}
                )
            }
        }
    }
}