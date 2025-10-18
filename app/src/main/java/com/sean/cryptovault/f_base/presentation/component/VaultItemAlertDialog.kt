package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun VaultItemAlertDialog(
    onAlertDismissRequest: () -> Unit,
    onAlertConfirmAction: () -> Unit,
    onAlertDismissAction: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onAlertDismissRequest,
        confirmButton = {
            TextButton(onClick = onAlertConfirmAction) {
                Text(
                    text = stringResource(R.string.ok),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = getSp(R.dimen.sp_password_item_block_subtitle)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onAlertDismissAction) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = getSp(R.dimen.sp_password_item_block_subtitle)
                )
            }
        },
        title = {
            Text(
                text = stringResource(R.string.are_you_sure),
                fontSize = getSp(R.dimen.sp_password_item_block_title),
                fontWeight = FontWeight.SemiBold
            )
        },
        text = {
            Text(
                text = stringResource(R.string.this_action_will_permanently_delete),
                fontSize = getSp(R.dimen.sp_password_item_block_subtitle)
            )
        },
        containerColor = MaterialTheme.colorScheme.secondary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary,
        textContentColor = MaterialTheme.colorScheme.onPrimary
    )
}

@Preview
@Composable
private fun PasswordItemAlertDialogPreview() {
    CryptoVaultTheme {
        VaultItemAlertDialog(
            {},
            {},
            {}
        )
    }
}