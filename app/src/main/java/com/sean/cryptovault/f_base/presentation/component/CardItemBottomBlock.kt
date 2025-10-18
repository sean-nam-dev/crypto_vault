package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.sean.cryptovault.R
import com.sean.cryptovault.f_base.common.CardVisualizer
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardItemBottomBlock(
    cardNumber: String,
    cardCVV: String,
    cardOwnerFullName: String,
    cardValidThru: String,
    isDataVisible: Boolean,
    onDataVisibilityChange: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF000000).copy(alpha = 0.2f),
                shape = RoundedCornerShape(getDp(R.dimen.card_item_spacing))
            )
            .padding(
                horizontal = getDp(R.dimen.card_item_spacing),
                vertical = getDp(R.dimen.card_item_vertical_arrangement)
            ),
        verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.card_item_bottom_block_vertical_arrangement))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isDataVisible) CardVisualizer.number(cardNumber)
                        else CardVisualizer.number(cardNumber.take(7) + "*****" + cardNumber.takeLast(4)),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = getSp(R.dimen.sp_password_item_block_title),
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = ImageVector.vectorResource(
                    if (isDataVisible) R.drawable.ic_round_visibility_off
                    else R.drawable.ic_round_visibility_on
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(getDp(R.dimen.card_item_icon_size))
                    .clickable(onClick = onDataVisibilityChange),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(
            text = if (isDataVisible) cardCVV
                    else "*".repeat(cardCVV.length),
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = getSp(R.dimen.sp_password_item_block_subtitle)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = cardOwnerFullName,
                modifier = Modifier.basicMarquee(delayMillis = 3000),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = getSp(R.dimen.sp_password_item_block_subtitle),
                maxLines = 1
            )
            Text(
                text = CardVisualizer.validThru(cardValidThru),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = getSp(R.dimen.sp_password_item_block_subtitle),
            )
        }
    }
}