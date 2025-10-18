package com.sean.cryptovault.f_entrance.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun PINBox(isCovered: Boolean) {
    Box(
        modifier = Modifier
            .size(getDp(R.dimen.pin_box_size))
            .background(
                color = MaterialTheme.colorScheme.surfaceTint,
                shape = RoundedCornerShape(getDp(R.dimen.pin_box_padding))
            )
            .border(
                width = getDp(R.dimen.pin_box_border_width),
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(getDp(R.dimen.pin_box_padding))
            )
            .padding(getDp(R.dimen.pin_box_padding)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(getDp(R.dimen.pin_value_box_size))
                .background(
                    color = if (isCovered) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceTint,
                    shape = CircleShape
                )
        )
    }
}

@Preview
@Composable
private fun PINBoxPreview() {
    CryptoVaultTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            PINBox(isCovered = true)
            PINBox(isCovered = false)
        }
    }
}