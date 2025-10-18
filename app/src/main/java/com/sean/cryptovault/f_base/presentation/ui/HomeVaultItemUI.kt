package com.sean.cryptovault.f_base.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.DpOffset
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.f_base.data.room.vault.Vault
import com.sean.cryptovault.f_base.presentation.component.VaultItem
import com.sean.cryptovault.f_base.presentation.ui_state.HomeVaultItemUIState

@Composable
fun HomeVaultItemUI(
    vault: Vault,
    homeVaultItemUIState: HomeVaultItemUIState,
    onPasswordVisibilityChange: () -> Unit,
    onOptionMenuVisibilityChange: () -> Unit,
    optionMenuItems: List<Triple<String, Int, () -> Unit>>
) {
    Box(contentAlignment = Alignment.TopEnd) {
        VaultItem(
            imageId = vault.imageId,
            title = Triple(
                vault.service,
                R.drawable.ic_round_option,
                onOptionMenuVisibilityChange
            ),
            subtitle = Triple(
                vault.identifier,
                null,
                null
            ),
            password = Triple(
                if (homeVaultItemUIState.isPasswordVisible) {
                    vault.password
                } else {
                    "•".repeat(vault.password.length)
                },
                if (homeVaultItemUIState.isPasswordVisible) {
                    R.drawable.ic_round_visibility_off
                } else {
                    R.drawable.ic_round_visibility_on
                },
                onPasswordVisibilityChange
            ),
            content = null
        )
        Box {
            DropdownMenu(
                expanded = homeVaultItemUIState.isOptionMenuVisible,
                onDismissRequest = onOptionMenuVisibilityChange,
                modifier = Modifier,
                offset = DpOffset(
                    x = getDp(R.dimen.home_vault_item_ui_x_offset),
                    y = getDp(R.dimen.home_vault_item_ui_y_offset)
                )
            ) {
                optionMenuItems.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(text = it.first)
                        },
                        onClick = it.third,
                        colors = MenuDefaults.itemColors(),
                        trailingIcon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(it.second),
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }
    }
}