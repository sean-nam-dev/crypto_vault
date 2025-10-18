package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun OutlinedActionTextField(
    text: String,
    labelText: String,
    placeHolderText: String,
    labelIcon: Int?,
    isError: Boolean,
    imeAction: ImeAction,
    onTextChange: (String) -> Unit,
    onLabelIconClick: (() -> Unit)?,
    onTrailingIconClickAction: () -> Unit,
    onImeDoneClickAction: (KeyboardActionScope.() -> Unit)?
) {
    Column(verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.outlined_action_text_field_column_arrangement))) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = labelText,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = getSp(R.dimen.sp_password_item_block_title),
                fontWeight = FontWeight.SemiBold
            )
            if (labelIcon != null && onLabelIconClick != null) {
                Icon(
                    imageVector = ImageVector.vectorResource(labelIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(
                            role = Role.Button,
                            onClick = onLabelIconClick
                        ),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
        OutlinedTextField(
            value = text,
            onValueChange = { onTextChange(it) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = getSp(R.dimen.sp_password_item_block_subtitle)
            ),
            placeholder = {
                Text(
                    text = placeHolderText,
                    fontSize = getSp(R.dimen.sp_password_item_block_subtitle)
                )
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_round_cancel),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(
                                role = Role.Button,
                                onClick = onTrailingIconClickAction
                            )
                    )
                }
            },
            supportingText = {
                if (isError) {
                    Text(text = stringResource(R.string.required))
                }
            },
            isError = isError,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = onImeDoneClickAction
            ),
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,

                cursorColor = MaterialTheme.colorScheme.onSecondary,

                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,

                focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSecondary,

                focusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.3f),
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.3f)
            )
        )
    }
}

@Preview
@Composable
private fun OutlinedActionTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    CryptoVaultTheme {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)) {
            OutlinedActionTextField(
                onTrailingIconClickAction = { text = "" },
                labelText = "Title",
                placeHolderText = "Hint",
                text = text,
                onTextChange = { text = it },
                imeAction = ImeAction.Done,
                onImeDoneClickAction = { focusManager.clearFocus() },
                labelIcon = R.drawable.ic_outline_lock,
                onLabelIconClick = {},
                isError = false
            )
            OutlinedActionTextField(
                onTrailingIconClickAction = { text = "" },
                labelText = "Title",
                placeHolderText = "Hint",
                text = text,
                onTextChange = { text = it },
                imeAction = ImeAction.Next,
                onImeDoneClickAction = null,
                labelIcon = R.drawable.ic_outline_lock,
                onLabelIconClick = {},
                isError = false
            )
        }
    }
}
