package com.sean.cryptovault.f_entrance.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun BorderedSecretWordButton(title: String, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = Modifier
            .fillMaxWidth()
            .height(getDp(R.dimen.secret_word_confirmation_button_height))
            .border(
                width = getDp(R.dimen.secret_word_button_width),
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10)
            ),
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = title,
            fontSize = getSp(R.dimen.twenty_four_sp),
            fontFamily = FontFamily(Font(R.font.titillium_web_bold))
        )
    }
}