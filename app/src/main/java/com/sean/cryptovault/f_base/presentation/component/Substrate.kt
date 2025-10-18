package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp

@Composable
fun Substrate(
    modifier: Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    content: @Composable() (BoxScope.() -> Unit)
) {
    Box(
        modifier = modifier
            .background(
                color = color,
                shape = RoundedCornerShape(getDp(R.dimen.substrate_padding))
            )
            .padding(getDp(R.dimen.substrate_padding)),
        contentAlignment = Alignment.Center,
        content = content
    )
}