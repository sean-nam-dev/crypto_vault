package com.sean.cryptovault.f_base.presentation.ui_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.sean.cryptovault.f_base.presentation.ui.StatisticsVaultItemUI
import com.sean.cryptovault.f_base.presentation.ui_action.StatisticsVaultItemUIAction
import com.sean.cryptovault.f_base.presentation.view_model.StatisticsVaultItemViewModel

@Composable
fun StatisticsVaultItem(
    viewModel: StatisticsVaultItemViewModel,
    navController: NavController
) {
    val statisticsVaultItemUIState = viewModel.state.collectAsState()

    StatisticsVaultItemUI(
        vault = viewModel.vault,
        statisticsVaultItemUIState = statisticsVaultItemUIState.value,
        onEditClickAction = {
            viewModel.onStatisticsVaultItemAction(
                StatisticsVaultItemUIAction.OnEditClickAction(
                    navController,
                    viewModel.vault
                )
            )
        },
        onPasswordVisibilityChange = { viewModel.onStatisticsVaultItemAction(StatisticsVaultItemUIAction.OnPasswordVisibilityChange()) }
    )
}