package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.vectorResource
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun VaultItemOptionMenu(
    isVaultFavorite: Boolean,
    ispOptionMenuVisible: Boolean,
    onOptionMenuDismissRequest: () -> Unit,
    onOptionMenuFavoriteAction: () -> Unit,
    onOptionMenuEditAction: () -> Unit,
    onOptionMenuDeleteAction: () -> Unit
) {
    val optionMenuItemTextList = stringArrayResource(R.array.option_menu_pin_list)
    val optionMenuItemIconList = listOf(
        if (isVaultFavorite) R.drawable.ic_round_favorite
            else R.drawable.ic_round_favorite_border,
        R.drawable.ic_round_edit,
        R.drawable.ic_round_delete
    )
    val optionMenuItemActionList = listOf(onOptionMenuFavoriteAction, onOptionMenuEditAction, onOptionMenuDeleteAction)

    Row {
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = Modifier.height(getDp(R.dimen.vault_item_block_row_box_height))) {
            DropdownMenu(
                expanded = ispOptionMenuVisible,
                onDismissRequest = onOptionMenuDismissRequest,
                modifier = Modifier.background(MaterialTheme.colorScheme.tertiary)
            ) {
                for (index in optionMenuItemTextList.indices) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = optionMenuItemTextList[index],
                                fontSize = getSp(R.dimen.sp_password_item_block_subtitle)
                            )
                        },
                        onClick = optionMenuItemActionList[index],
                        trailingIcon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(optionMenuItemIconList[index]),
                                contentDescription = null
                            )
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = MaterialTheme.colorScheme.onPrimary,
                            trailingIconColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }
        }
    }
}