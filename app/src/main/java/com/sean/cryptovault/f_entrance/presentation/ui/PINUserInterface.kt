package com.sean.cryptovault.f_entrance.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.ChangeSystemBarsColor
import com.sean.cryptovault.f_entrance.presentation.component.InputBlock
import com.sean.cryptovault.f_entrance.presentation.component.PINBoxFrame
import com.sean.cryptovault.f_entrance.presentation.ui_state.PINUIState
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun PINUserInterface(
    pinUIState: PINUIState,
    text: String,
    onPINChange: (String) -> Unit,
    onBiometricUse: (() -> Unit)?,
    onBackSpace:() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.pin_creation_ui_column_arrangement)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = getSp(R.dimen.twenty_four_sp),
                fontFamily = FontFamily(Font(R.font.titillium_web_regular)),
                textAlign = TextAlign.Center
            )
            PINBoxFrame(text = pinUIState.pin)
            InputBlock(
                onPINNUmberChange = onPINChange,
                onBiometricUse = onBiometricUse,
                onPINBackSpaceAction = onBackSpace
            )
        }
    }
}