package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ActionText(
    text: String,
    fontSize: TextUnit,
    iconId: Int?,
    onIconClickAction: (() -> Unit)?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .basicMarquee(delayMillis = 2000),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = fontSize,
            maxLines = 1
        )
        if (iconId != null) {
            Icon(
                imageVector = ImageVector.vectorResource(iconId),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(
                        enabled = onIconClickAction != null,
                        role = Role.Button,
                        onClick = onIconClickAction ?: {}
                    ),
                tint = Color(0xFFCAC4D0)
            )
        }
    }
}