package com.sean.cryptovault.f_entrance.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun PINActionButton(iconId: Int, action: () -> Unit) {
    IconButton(
        onClick = action,
        modifier = Modifier.size(getDp(R.dimen.pin_code_button_size)),
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconId),
            contentDescription = null,
            modifier = Modifier.size(getDp(R.dimen.pin_code_icon_size))
        )
    }
}

@Preview
@Composable
private fun PINActionButtonPreview() {
    CryptoVaultTheme {
        PINActionButton(iconId = R.drawable.ic_round_fingerprint) {

        }
    }
}