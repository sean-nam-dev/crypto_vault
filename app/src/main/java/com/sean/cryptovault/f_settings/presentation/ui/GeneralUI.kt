package com.sean.cryptovault.f_settings.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.component.TopAppBarFrame
import com.sean.cryptovault.f_settings.common.IconTextButtonState
import com.sean.cryptovault.f_settings.presentation.component.IconTextButtonBlock
import com.sean.cryptovault.f_settings.presentation.component.LabeledColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralUI(
    scrollBehavior: TopAppBarScrollBehavior,
    isDarkTheme: Boolean,
    iconTextButtonStateList: List<List<IconTextButtonState>>,
    onBurgerTopBarNavAction: () -> Unit,
    onDarkThemeChange: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarFrame(
                title = stringResource(R.string.settings),
                navIconId = R.drawable.ic_round_menu,
                onNavAction = onBurgerTopBarNavAction,
                scrollBehavior = scrollBehavior,
                Pair(
                    if (isDarkTheme) R.drawable.ic_round_dark_mode_on
                        else R.drawable.ic_round_dark_mode_off,
                    onDarkThemeChange
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.password_generator_ui_padding))
        ) {
            stringArrayResource(R.array.general_ui_column_labels).forEachIndexed { index, label ->
                LabeledColumn(label) {
                    iconTextButtonStateList[index].forEach {
                        IconTextButtonBlock(
                            iconId = it.iconId,
                            text = it.text,
                            onClick = it.onClick
                        )
                    }
                }
            }
        }
    }
}