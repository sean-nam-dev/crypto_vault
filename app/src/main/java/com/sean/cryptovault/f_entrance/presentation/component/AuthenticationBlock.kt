package com.sean.cryptovault.f_entrance.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun AuthenticationBlock(
    isBiometricActivated: Boolean,
    onBiometricChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceTint,
                shape = RoundedCornerShape(20)
            )
            .padding(
                horizontal = getDp(R.dimen.secret_word_block_padding),
                vertical = getDp(R.dimen.authentication_block_padding)
            ),
        horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.secret_word_block_padding)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.biometric_authentication),
            modifier = Modifier.weight(1f),
            fontSize = getSp(R.dimen.twenty_sp),
            color = MaterialTheme.colorScheme.primary
        )
        Switch(
            checked = isBiometricActivated,
            onCheckedChange = onBiometricChange ,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedBorderColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.surfaceTint,
                uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedBorderColor = MaterialTheme.colorScheme.primary,
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceTint
            )
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AuthenticationBlockPreview() {
    var isActivated by remember { mutableStateOf(false) }

    CryptoVaultTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(20.dp)) {
            AuthenticationBlock(
                isBiometricActivated = isActivated,
                onBiometricChange = { isActivated = it }
            )
        }
    }
}