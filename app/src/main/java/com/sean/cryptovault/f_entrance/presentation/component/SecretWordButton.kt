package com.sean.cryptovault.f_entrance.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme
import com.sean.cryptovault.ui.theme.Gradient_1

@Composable
fun SecretWordButton(title: String, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = Modifier
            .fillMaxWidth()
            .height(getDp(R.dimen.secret_word_confirmation_button_height))
            .background(
                brush = Gradient_1,
                shape = RoundedCornerShape(10)
            ),
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Text(
            text = title,
            fontSize = getSp(R.dimen.twenty_four_sp),
            fontFamily = FontFamily(Font(R.font.titillium_web_bold))
        )
    }
}

@Preview
@Composable
private fun SecretWordConfirmButtonPreview() {
    CryptoVaultTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp)) {
            SecretWordButton("Confirm") {}
        }
    }
}