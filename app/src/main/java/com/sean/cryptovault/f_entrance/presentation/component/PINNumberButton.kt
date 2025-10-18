package com.sean.cryptovault.f_entrance.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun PINNumberButton(value: String, onValueChange: (String) -> Unit) {
    TextButton(
        onClick = {
            onValueChange(value)
        },
        modifier = Modifier.size(getDp(R.dimen.pin_code_button_size)),
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Text(
            text = value,
            fontSize = getSp(R.dimen.thirty_five_sp),
            fontFamily = FontFamily(Font(R.font.titillium_web_bold))
        )
    }
}

@Preview
@Composable
private fun PINNumberButtonPreview() {
    CryptoVaultTheme {
        PINNumberButton("5") {

        }
    }
}