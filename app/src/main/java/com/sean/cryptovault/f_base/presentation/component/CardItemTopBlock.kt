package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getSp

@Composable
fun CardItemTopBlock(
    bank: String,
    isFavorite: Boolean,
    isOptionMenuVisible: Boolean,
    onOptionMenuVisibilityChange: () -> Unit,
    onOptionMenuFavoriteAction: () -> Unit,
    onOptionMenuEditAction: () -> Unit,
    onOptionMenuDeleteAction: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = bank,
            color = Color.White,
            fontSize = getSp(R.dimen.sp_slightly_big_text),
            fontWeight = FontWeight.SemiBold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        CardItemOptionMenu(
            isFavorite = isFavorite,
            isOptionMenuVisible = isOptionMenuVisible,
            onOptionMenuChange = onOptionMenuVisibilityChange,
            onOptionMenuPinAction = onOptionMenuFavoriteAction,
            onOptionMenuEditAction = onOptionMenuEditAction,
            onOptionMenuDeleteAction = onOptionMenuDeleteAction
        )
    }
}