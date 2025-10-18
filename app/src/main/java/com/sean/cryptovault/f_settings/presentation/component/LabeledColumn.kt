package com.sean.cryptovault.f_settings.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun LabeledColumn(
    label: String,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    Column {
        Text(
            text = label,
            modifier = Modifier.padding(
                start = getDp(R.dimen.labeled_column_padding),
                top = getDp(R.dimen.labeled_column_padding)
            ),
            color = MaterialTheme.colorScheme.primary,
            fontSize = getSp(R.dimen.sp_secondary_text)
        )
        content()
    }
}