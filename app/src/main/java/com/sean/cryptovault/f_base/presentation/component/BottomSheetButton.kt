package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun BottomSheetButton(
    iconId: Int,
    label: String,
    color: Color,
    onClickAction: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.navigation_bottom_sheet_arrangement))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(getDp(R.dimen.bottom_sheet_button_content_padding)))
                .clickable(
                    role = Role.Button,
                    onClick = onClickAction
                )
                .padding(getDp(R.dimen.bottom_sheet_button_content_padding)),
            horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.bottom_sheet_button_horizontal_arrangement)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = color.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(getDp(R.dimen.bottom_sheet_button_content_padding))
                    )
                    .padding(getDp(R.dimen.navigation_bottom_sheet_arrangement)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(iconId),
                    contentDescription = null,
                    modifier = Modifier.size(getDp(R.dimen.bottom_sheet_button_icon_size)),
                    tint = color
                )
            }
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = getSp(R.dimen.sp_password_item_block_subtitle),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}