package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp

@Composable
fun CurrentImageBlock(
    currentImageId: Int,
    isSearchBlockVisible: Boolean,
    onSearchBlockVisualChange: () -> Unit
) {
    Row(
        modifier = Modifier.offset(getDp(R.dimen.current_image_block_icon_button_size)),
        horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.current_image_block_row_arrangement))
    ) {
        Image(
            painter = painterResource(currentImageId),
            contentDescription = null,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
                .padding(getDp(R.dimen.current_image_block_current_image_padding))
                .size(getDp(R.dimen.current_image_block_current_image_size))
                .clip(CircleShape),
            contentScale = ContentScale.FillBounds
        )
        Icon(
            imageVector = ImageVector.vectorResource(
                if (isSearchBlockVisible) R.drawable.ic_round_close
                else R.drawable.ic_outline_edit
            ),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable(
                    role = Role.Button,
                    onClick = onSearchBlockVisualChange
                )
                .background(
                    color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f),
                    shape = CircleShape
                )
                .padding(getDp(R.dimen.current_image_block_icon_button_padding))
                .size(getDp(R.dimen.current_image_block_icon_button_size)),
            tint = Color.White.copy(alpha = 0.5f)
        )
    }
}