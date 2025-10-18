package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun PasswordDialog(
    sliderValue: Float,
    switchContent: List<Triple<String, Boolean, (Boolean) -> Unit>>,
    onPasswordDialogDismissRequest: () -> Unit,
    onSliderValueChange: (Float) -> Unit,
    onGenerateClickAction: () -> Unit
) {
    Dialog(onDismissRequest = onPasswordDialogDismissRequest) {
        Card(
            shape = AlertDialogDefaults.shape,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary)
        ) {
            Column(
                modifier = Modifier.padding(getDp(R.dimen.sort_panel_column_padding)),
                verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.sort_panel_outer_vertical_arrangement)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.password_generator),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = getSp(R.dimen.sp_password_item_block_title),
                    fontWeight = FontWeight.SemiBold
                )
                Column(
                    modifier = Modifier
                        .padding(vertical = getDp(R.dimen.password_dialog_column_padding))
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.password_dialog_vertical_arrangement)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${sliderValue.toInt()} ${stringResource(R.string.symbols)}",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = getSp(R.dimen.sp_password_item_block_title)
                    )
                    Slider(
                        value = sliderValue,
                        onValueChange = onSliderValueChange,
                        modifier = Modifier.fillMaxWidth(),
                        valueRange = 12f..24f,
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.onPrimary,

                            activeTrackColor = MaterialTheme.colorScheme.onPrimary,
                            inactiveTrackColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                    switchContent.forEach {
                        SwitchWithText(
                            text = it.first,
                            checked = it.second,
                            onCheckedChange = it.third
                        )
                    }
                }
                Button(
                    onClick = onGenerateClickAction,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(getDp(R.dimen.password_dialog_rounded_corner_shape)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(
                        text = stringResource(R.string.generate),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = getSp(R.dimen.sp_password_item_block_subtitle)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PasswordDialogPreview() {
    var sliderValue by remember { mutableFloatStateOf(12f) }

    CryptoVaultTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            PasswordDialog(
                sliderValue = sliderValue,
                onPasswordDialogDismissRequest = { /*TODO*/ },
                switchContent = listOf(
                    Triple("Numbers", true, {}),
                    Triple("Symbols", false, {}),
                    Triple("Uppercase letters", true, {})
                ),
                onSliderValueChange = { sliderValue = it },
                onGenerateClickAction = {}
            )
        }
    }
}