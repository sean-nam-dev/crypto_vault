package com.sean.cryptovault.f_base.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.f_base.presentation.component.Substrate
import com.sean.cryptovault.core.component.TopAppBarFrame
import com.sean.cryptovault.f_base.presentation.ui_state.PasswordGeneratorUIState
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordGeneratorUI(
    passwordGeneratorUIState: PasswordGeneratorUIState,
    scrollBehavior: TopAppBarScrollBehavior,
    onBurgerTopBarNavAction: () -> Unit,
    onSliderValueChange: (Float) -> Unit,
    onIncludeButtonChange: (Int) -> Unit,
    onCopyClickAction: () -> Unit,
    onChangeClickAction: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarFrame(
                title = stringResource(R.string.password_generator),
                navIconId = R.drawable.ic_round_menu,
                onNavAction = onBurgerTopBarNavAction,
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(getDp(R.dimen.password_generator_ui_padding)),
            verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.password_generator_ui_vertical_arrangement_big))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = passwordGeneratorUIState.passwordLength.toString(),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = getSp(R.dimen.sp_enormous_text)
                )
                Text(
                    text = stringResource(R.string.characters),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = getSp(R.dimen.sp_primary_text)
                )
            }
            Slider(
                value = passwordGeneratorUIState.passwordLength.toFloat(),
                onValueChange = onSliderValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = -(getDp(R.dimen.password_generator_ui_button_stroke_width))),
                valueRange = 12f..36f,
                onValueChangeFinished = onChangeClickAction,
                colors = SliderDefaults.colors(inactiveTrackColor = MaterialTheme.colorScheme.inversePrimary)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.password_generator_ui_padding))
            ) {
                val buttonStates = listOf(
                    passwordGeneratorUIState.isDigitIncluded,
                    passwordGeneratorUIState.isSignIncluded,
                    passwordGeneratorUIState.isUppercaseIncluded
                )
                stringArrayResource(R.array.password_generator_buttons).forEachIndexed { index, text ->
                    Button(
                        onClick = { onIncludeButtonChange(index) },
                        modifier = Modifier
                            .height(getDp(R.dimen.password_generator_ui_button_height))
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (buttonStates[index]) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                            contentColor = if (buttonStates[index]) MaterialTheme.colorScheme.background
                            else Color.White
                        ),
                        border = BorderStroke(
                            width = getDp(R.dimen.password_generator_ui_button_stroke_width),
                            color = if (buttonStates[index]) MaterialTheme.colorScheme.primary
                            else Color.White
                        ),
                        contentPadding = PaddingValues()
                    ) {
                        Text(
                            text = text,
                            fontSize = getSp(R.dimen.sp_tertiary_text),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Substrate(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.password_generator_ui_padding))
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = passwordGeneratorUIState.password,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        fontSize = getSp(R.dimen.sp_big_text)
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_filled_copy),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .size(getDp(R.dimen.password_generator_ui_icon_size))
                            .clickable(
                                role = Role.Button,
                                onClick = onCopyClickAction
                            ),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(getDp(R.dimen.password_generator_ui_row_shape_and_arrangement)))
                    .clickable(
                        role = Role.Button,
                        onClick = onChangeClickAction
                    ),
                horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.password_generator_ui_row_shape_and_arrangement)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_round_refresh),
                    contentDescription = null,
                    modifier = Modifier.size(getDp(R.dimen.password_generator_ui_icon_size)),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = stringResource(R.string.change),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = getSp(R.dimen.sp_primary_text),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PasswordGeneratorUIPreview() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    CryptoVaultTheme {
        PasswordGeneratorUI(
            passwordGeneratorUIState = PasswordGeneratorUIState(
                passwordLength = 24,
                isDigitIncluded = true
            ),
            scrollBehavior = scrollBehavior,
            onBurgerTopBarNavAction = {},
            onSliderValueChange = {},
            onChangeClickAction = {},
            onCopyClickAction = {},
            onIncludeButtonChange = {}
        )
    }
}