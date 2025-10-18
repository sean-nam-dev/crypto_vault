package com.sean.cryptovault.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun CustomTextField(
    value: String,
    label: String,
    placeholder: String,
    trailingIcon: Int,
    isError: Boolean,
    visualTransformation: VisualTransformation,
    keyboardOptions: KeyboardOptions,
    onValueChange: (String) -> Unit,
    onTrailingIconClickAction: () -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(fontSize = getSp(R.dimen.sp_primary_text)),
        label = {
            Text(
                text = label,
                fontSize = getSp(R.dimen.sp_secondary_text),
                fontWeight = FontWeight.SemiBold
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                fontSize = getSp(R.dimen.sp_primary_text)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(trailingIcon),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(
                        role = Role.Button,
                        onClick = onTrailingIconClickAction
                    )
            )
        },
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Black.copy(alpha = 0.1f),
            unfocusedContainerColor = Color.Black.copy(alpha = 0.1f),
            errorContainerColor = Color.Black.copy(alpha = 0.1f),

            focusedIndicatorColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,

            focusedTextColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
            errorTextColor = MaterialTheme.colorScheme.onSecondary,

            focusedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSecondary,

            focusedTrailingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unfocusedTrailingIconColor = Color.Transparent,

            focusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),

            cursorColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    var value by remember { mutableStateOf("") }

    CryptoVaultTheme {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)) {
            CustomTextField(
                value = value,
                label = "Label",
                placeholder = "PlaceHolder",
                trailingIcon = R.drawable.ic_round_settings_suggest,
                onValueChange = { value = it },
                isError = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
//                keyboardActions = KeyboardActions(
//                    onNext = null
//                ),
                onTrailingIconClickAction = {},
                visualTransformation = VisualTransformation.None
            )
            CustomTextField(
                value = value,
                label = "Label",
                placeholder = "PlaceHolder",
                trailingIcon = R.drawable.ic_round_cancel,
                onValueChange = { value = it },
                isError = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
//                keyboardActions = KeyboardActions(
//                    onDone = null
//                ),
                onTrailingIconClickAction = {},
                visualTransformation = VisualTransformation.None
            )
        }
    }
}