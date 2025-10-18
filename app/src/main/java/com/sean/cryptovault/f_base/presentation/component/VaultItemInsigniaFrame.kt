package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp

@Composable
fun VaultItemInsigniaFrame(
    iconId: Int,
    isInfoFrameVisible: Boolean
) {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_round_arrow_right),
        contentDescription = null,
        modifier = Modifier
            .size(getDp(R.dimen.vault_item_block_icon_arrow_size))
            .rotate(if (isInfoFrameVisible) 90f else 0f),
        tint = MaterialTheme.colorScheme.onPrimary
    )
    Image(
        bitmap = ImageBitmap.imageResource(iconId).also { it.prepareToDraw() }, //painterResource(iconId)
        contentDescription = null,
        modifier = Modifier.size(getDp(R.dimen.vault_item_block_image_size)),
        contentScale = ContentScale.Fit
    )
}