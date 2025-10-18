package com.sean.cryptovault.f_base.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.f_base.data.room.vault.Vault
import com.sean.cryptovault.f_base.presentation.component.VaultItem
import com.sean.cryptovault.f_base.presentation.ui_state.StatisticsVaultItemUIState

@Composable
fun StatisticsVaultItemUI(
    vault: Vault,
    statisticsVaultItemUIState: StatisticsVaultItemUIState,
    onEditClickAction: () -> Unit,
    onPasswordVisibilityChange: () -> Unit
) {
    VaultItem(
        imageId = vault.imageId,
        title = Triple(
            vault.service,
            R.drawable.ic_round_small_arrow_right,
            onEditClickAction
        ),
        subtitle = Triple(
            vault.identifier,
            null,
            null
        ),
        password = Triple(
            if (statisticsVaultItemUIState.isPasswordVisible) {
                vault.password
            } else {
                "•".repeat(vault.password.length)
            },
            if (statisticsVaultItemUIState.isPasswordVisible) {
                R.drawable.ic_round_visibility_off
            } else {
                R.drawable.ic_round_visibility_on
            },
            onPasswordVisibilityChange
        )
    ) {
        LazyRow(
            modifier = Modifier
                .padding(top = getDp(R.dimen.vault_item_text_padding))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.vault_item_text_padding))
        ) {
            items(statisticsVaultItemUIState.problemList) {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = getSp(R.dimen.sp_tertiary_text),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}