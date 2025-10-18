package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp

@Composable
fun CardItemOptionMenu(
    isFavorite: Boolean,
    isOptionMenuVisible: Boolean,
    onOptionMenuChange: () -> Unit,
    onOptionMenuPinAction: () -> Unit,
    onOptionMenuEditAction: () -> Unit,
    onOptionMenuDeleteAction: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.card_item_spacing)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(visible = isOptionMenuVisible) {
            IconButton(
                onClick = onOptionMenuPinAction,
                modifier = Modifier.size(getDp(R.dimen.card_item_option_menu_icon_button_size)),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f),
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        if (isFavorite) R.drawable.ic_round_favorite
                        else R.drawable.ic_round_favorite_border
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(getDp(R.dimen.card_item_icon_size))
                )
            }
        }
        for (
            pair in listOf(
                onOptionMenuEditAction to R.drawable.ic_round_edit,
                onOptionMenuDeleteAction to R.drawable.ic_round_delete
            )
        ) {
            AnimatedVisibility(visible = isOptionMenuVisible) {
                IconButton(
                    onClick = pair.first,
                    modifier = Modifier.size(getDp(R.dimen.card_item_option_menu_icon_button_size)),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f),
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(pair.second),
                        contentDescription = null,
                        modifier = Modifier.size(getDp(R.dimen.card_item_icon_size))
                    )
                }
            }
        }
        Icon(
            imageVector = ImageVector.vectorResource(
                if (isOptionMenuVisible) R.drawable.ic_round_close
                else R.drawable.ic_round_option
            ),
            contentDescription = null,
            modifier = Modifier
                .size(getDp(R.dimen.card_item_icon_size))
                .clip(CircleShape)
                .clickable(onClick = onOptionMenuChange),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}