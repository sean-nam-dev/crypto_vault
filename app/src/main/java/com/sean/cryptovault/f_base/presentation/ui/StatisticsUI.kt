package com.sean.cryptovault.f_base.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.f_base.data.room.card.Card
import com.sean.cryptovault.f_base.data.room.vault.Vault
import com.sean.cryptovault.f_base.presentation.component.BigColumnText
import com.sean.cryptovault.f_base.presentation.component.PieChart
import com.sean.cryptovault.f_base.presentation.component.RowInfoFrame
import com.sean.cryptovault.f_base.presentation.component.Substrate
import com.sean.cryptovault.core.component.TopAppBarFrame
import com.sean.cryptovault.f_base.presentation.ui_screen.StatisticsVaultItem
import com.sean.cryptovault.f_base.presentation.view_model.StatisticsVaultItemViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsUI(
    currentVaultList: List<Vault>,
    currentCardList: List<Card>,
    strongPasswords: List<String>,
    duplicatedPasswordVaults: List<Vault>,
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
    onBurgerTopBarNavAction: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarFrame(
                title = stringResource(R.string.statistics),
                navIconId = R.drawable.ic_round_menu,
                onNavAction = onBurgerTopBarNavAction,
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(getDp(R.dimen.statistics_ui_padding_and_arrangement)),
            verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.statistics_ui_padding_and_arrangement))
        ) {
            item {
                Substrate(modifier = Modifier.fillMaxWidth()) {
                    RowInfoFrame(
                        title = "${(strongPasswords.size.toFloat() / currentVaultList.size * 100).toInt()}",
                        subtitle = stringResource(R.string.secured_passwords),
                        includePercentage = true
                    ) {
                        PieChart(
                            strongPasswords = strongPasswords.size,
                            totalPasswords = currentVaultList.size
                        )
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.statistics_ui_padding_and_arrangement))
                ) {
                    Substrate(modifier = Modifier.weight(1f)) {
                        BigColumnText(
                            title = "${currentVaultList.size}",
                            subtitle = stringResource(R.string.vaults)
                        )
                    }
                    Substrate(modifier = Modifier.weight(1f)) {
                        BigColumnText(
                            title = "${currentCardList.size}",
                            subtitle = stringResource(R.string.cards)
                        )
                    }
                }
            }
            item {
                Substrate(modifier = Modifier.fillMaxWidth()) {
                    RowInfoFrame(
                        title = "${duplicatedPasswordVaults.size}",
                        subtitle = stringResource(R.string.reused_passwords),
                        includePercentage = false
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_round_repeat),
                            contentDescription = null,
                            modifier = Modifier.size(getDp(R.dimen.statistics_ui_icon_size)),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            if (currentVaultList.size != strongPasswords.size) {
                item {
                    Column(
                        modifier = Modifier.padding(top = getDp(R.dimen.statistics_ui_padding_top)),
                        verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.statistics_ui_arrangement))
                    ) {
                        Text(
                            text = stringResource(R.string.attention_needed),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = getSp(R.dimen.sp_big_text),
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = stringResource(R.string.some_of_your_passwords_seem_vulnerable),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = getSp(R.dimen.sp_primary_text)
                        )
                        Spacer(modifier = Modifier.padding(top = getDp(R.dimen.statistics_ui_padding_and_arrangement)))
                    }
                }
            }
            items(currentVaultList, key = { it.id }) {
                StatisticsVaultItem(
                    viewModel = StatisticsVaultItemViewModel(it, stringArrayResource(R.array.problem_list).toList()),
                    navController = navController
                )
            }
        }
    }
}