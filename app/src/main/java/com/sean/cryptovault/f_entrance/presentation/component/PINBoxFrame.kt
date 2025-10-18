package com.sean.cryptovault.f_entrance.presentation.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun PINBoxFrame(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = getDp(R.dimen.pin_box_frame_padding)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in 1..4) {
            PINBox(isCovered = i <= text.length)
        }
    }
}

@Preview
@Composable
private fun PINBoxFramePreview() {
    CryptoVaultTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Log.i("CHECKCOLOR", MaterialTheme.colorScheme.primary.value.toString())
            PINBoxFrame(text = "ssss")
        }
    }
}