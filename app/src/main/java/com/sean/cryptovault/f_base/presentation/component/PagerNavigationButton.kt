package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun PagerNavigationButton(
    text: String,
    includeLeftArrow: Boolean,
    includeRightArrow: Boolean,
    onClickAction: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.pager_navigation_button_horizontal_arrangement)),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(getDp(R.dimen.pager_navigation_button_horizontal_arrangement)))
            .clickable(
                role = Role.Button,
                onClick = onClickAction
            )
    ) {
        if (includeRightArrow) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_round_arrow_left),
                contentDescription = null,
                modifier = Modifier
                    .size(getDp(R.dimen.pager_navigation_button_icon_size))
                    .offset(y = getDp(R.dimen.pager_navigation_button_y_offset)),
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            )
        }
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            fontSize = getSp(R.dimen.twenty_sp),
            fontFamily = FontFamily(Font(R.font.titillium_web_regular))
        )
        if (includeLeftArrow) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_round_arrow_right),
                contentDescription = null,
                modifier = Modifier
                    .size(getDp(R.dimen.pager_navigation_button_icon_size))
                    .offset(y = getDp(R.dimen.pager_navigation_button_y_offset)),
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            )
        }
    }
}