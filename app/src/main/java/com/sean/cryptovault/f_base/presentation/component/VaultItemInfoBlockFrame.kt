package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.sean.cryptovault.R
import com.sean.cryptovault.f_base.data.room.vault.Vault
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun VaultItemInfoBlockFrame(
    vault: Vault,
    isPasswordVisible: Boolean,
    onVisibilityChange: () -> Unit,
    onCopyAction: (String) -> Unit,
    onBrowseAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = getDp(R.dimen.vault_item_info_block_frame_horizontal_arrangement))
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(getDp(R.dimen.vault_item_block_row_arrangement))
            )
            .padding(getDp(R.dimen.vault_item_info_block_frame_horizontal_arrangement)),
        verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.vault_item_info_block_frame_horizontal_arrangement))
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.vault_item_info_block_frame_arrangement))) {
            Text(
                text = stringResource(R.string.username),
                fontSize = getSp(R.dimen.sp_password_item_block_subtitle),
                color = MaterialTheme.colorScheme.onSecondary,
                fontWeight = FontWeight.SemiBold
            )
            Row(horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.vault_item_info_block_frame_horizontal_arrangement))) {
                Text(
                    text = vault.identifier,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = getSp(R.dimen.sp_password_item_block_subtitle)
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_round_copy),
                    contentDescription = null,
                    modifier = Modifier
                        .size(getDp(R.dimen.vault_item_info_block_frame_icon_size))
                        .clickable {
                            onCopyAction(vault.identifier)
                        },
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.vault_item_info_block_frame_arrangement))) {
            Text(
                text = stringResource(R.string.password),
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = getSp(R.dimen.sp_password_item_block_subtitle),
                fontWeight = FontWeight.SemiBold
            )
            Row(horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.vault_item_info_block_frame_horizontal_arrangement))) {
                Text(
                    text = if (isPasswordVisible) vault.password
                    else "*".repeat(vault.password.length),
                    modifier = Modifier.weight(1f),
                    fontSize = getSp(R.dimen.sp_password_item_block_subtitle),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Icon(
                    imageVector = ImageVector.vectorResource(
                        if (isPasswordVisible) R.drawable.ic_round_visibility_on
                        else R.drawable.ic_round_visibility_off
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(getDp(R.dimen.vault_item_info_block_frame_icon_size))
                        .clickable(onClick = onVisibilityChange),
                    tint = MaterialTheme.colorScheme.onSecondary
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_round_copy),
                    contentDescription = null,
                    modifier = Modifier
                        .size(getDp(R.dimen.vault_item_info_block_frame_icon_size))
                        .clickable {
                            onCopyAction(vault.password)
                        },
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
        if (vault.link != null) {
            Button(
                onClick = onBrowseAction,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(getDp(R.dimen.vault_item_block_row_arrangement)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                contentPadding = PaddingValues(getDp(R.dimen.vault_item_info_block_frame_button_padding))
            ) {
                Text(
                    text = stringResource(R.string.open_in_browser),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = getSp(R.dimen.sp_password_item_block_subtitle)
                )
            }
        }
    }
}
