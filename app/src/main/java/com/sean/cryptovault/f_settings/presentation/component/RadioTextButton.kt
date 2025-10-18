package com.sean.cryptovault.f_settings.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun RadioTextButton(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClickAction: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(getDp(R.dimen.radio_text_button_padding)))
            .selectable(
                selected = isSelected,
                onClick = onClickAction,
                role = Role.RadioButton
            )
            .padding(getDp(R.dimen.radio_text_button_padding)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.radio_text_button_horizontal_arrangement))
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null
        )
        Column(verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.radio_text_button_vertical_arrangement))) {
            Text(
                text = title,
                color = Color.White,
                fontSize = getSp(R.dimen.sp_primary_text)
            )
            Text(
                text = subtitle,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = getSp(R.dimen.sp_secondary_text)
            )
        }
    }
}

@Preview
@Composable
private fun RadioTextButtonPreview() {
    CryptoVaultTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            RadioTextButton(
                title = "Title",
                subtitle = "Subtitle",
                isSelected = true,
                onClickAction = {}
            )
            RadioTextButton(
                title = "Title",
                subtitle = "Subtitle",
                isSelected = false,
                onClickAction = {}
            )
        }
    }
}