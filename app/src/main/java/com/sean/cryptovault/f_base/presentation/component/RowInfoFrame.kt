package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun RowInfoFrame(
    title: String,
    subtitle: String,
    includePercentage: Boolean,
    content: @Composable() (RowScope.() -> Unit)
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = getDp(R.dimen.row_info_frame_small_padding),
                end = getDp(R.dimen.row_info_frame_small_padding),
                bottom = getDp(R.dimen.row_info_frame_big_padding)
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.row_info_arrangement)),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = title,
                    modifier = Modifier.offset(y = -(getDp(R.dimen.row_info_frame_small_padding))),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = getSp(R.dimen.sp_enormous_text),
                    fontWeight = FontWeight.ExtraLight,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                )
                if (includePercentage) {
                    Text(
                        text = "%",
                        modifier = Modifier.offset(y = getDp(R.dimen.row_info_arrangement)),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = getSp(R.dimen.sp_very_big_text),
                        fontWeight = FontWeight.ExtraLight,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                    )
                }
            }
            content()
        }
        Text(
            text = subtitle,
            color = MaterialTheme.colorScheme.inversePrimary,
            fontSize = getSp(R.dimen.sp_big_text),
            fontWeight = FontWeight.ExtraBold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}