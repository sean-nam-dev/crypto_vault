package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VaultItemMainFrame(
    modifier: Modifier,
    title: String,
    subtitle: String,
    isFavorite: Boolean,
    isInfoFrameActive: Boolean,
    onOptionMenuVisibilityChange: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.vault_item_block_inner_column_arrangement))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.vault_item_block_inner_column_arrangement)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f).basicMarquee(delayMillis = 3000),
                fontSize = getSp(R.dimen.sp_password_item_block_title),
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_round_option),
                contentDescription = null,
                modifier = Modifier
                    .size(getDp(R.dimen.vault_item_info_block_frame_icon_size))
                    .clickable(onClick = onOptionMenuVisibilityChange),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        AnimatedVisibility(
            visible = !isInfoFrameActive,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.vault_item_block_inner_column_arrangement)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = subtitle,
                    modifier = Modifier.weight(1f).basicMarquee(delayMillis = 3000),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = getSp(R.dimen.sp_password_item_block_subtitle),
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .size(getDp(R.dimen.vault_item_info_block_frame_icon_size))
                        .padding(getDp(R.dimen.vault_item_main_frame_box_padding)),
                    contentAlignment = Alignment.Center
                ) {
                    if (isFavorite) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_round_favorite),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}