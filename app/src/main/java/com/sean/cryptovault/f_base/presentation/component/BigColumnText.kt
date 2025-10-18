package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun BigColumnText(title: String, subtitle: String) {
    Column(
        modifier = Modifier.offset(y = -(getDp(R.dimen.big_column_text_offset))),
        verticalArrangement = Arrangement.spacedBy(-(getDp(R.dimen.big_column_text_vertical_arrangement))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.primary,
            fontSize = getSp(R.dimen.sp_huge_text),
            fontWeight = FontWeight.ExtraLight,
            overflow = TextOverflow.Clip,
            maxLines = 1
        )
        Text(
            text = subtitle,
            color = MaterialTheme.colorScheme.inversePrimary,
            fontSize = getSp(R.dimen.sp_primary_text),
            fontWeight = FontWeight.ExtraBold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}