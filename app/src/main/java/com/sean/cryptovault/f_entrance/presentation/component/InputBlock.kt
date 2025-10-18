package com.sean.cryptovault.f_entrance.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun InputBlock(
    onPINNUmberChange: (String) -> Unit,
    onBiometricUse: (() -> Unit)?,
    onPINBackSpaceAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceTint,
                shape = RoundedCornerShape(
                    topStart = getDp(R.dimen.input_block_rounded_corner_shape),
                    topEnd = getDp(R.dimen.input_block_rounded_corner_shape)
                )
            )
            .padding(getDp(R.dimen.input_block_padding)),
        verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.input_block_vertical_arrangement))
    ) {
        for (i in 1..7 step 3) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                for (j in i until i + 3) {
                    PINNumberButton(
                        value = j.toString(),
                        onValueChange = onPINNUmberChange
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            if (onBiometricUse == null) {
                Spacer(modifier = Modifier.size(getDp(R.dimen.pin_code_button_size)))
            } else {
                PINActionButton(
                    iconId = R.drawable.ic_round_fingerprint,
                    action = onBiometricUse
                )
            }
            PINNumberButton(
                value = "0",
                onValueChange = onPINNUmberChange
            )
            PINActionButton(
                iconId = R.drawable.ic_round_backspace,
                action = onPINBackSpaceAction
            )
        }
    }
}

@Preview
@Composable
private fun InputBlockPreview() {
    CryptoVaultTheme {
        InputBlock(
            onPINNUmberChange = { },
            onBiometricUse = {},
            onPINBackSpaceAction = {}
        )
    }
}