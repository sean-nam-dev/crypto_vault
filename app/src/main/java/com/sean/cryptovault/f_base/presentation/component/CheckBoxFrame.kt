package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun CheckBoxFrame(
    isToggled: Boolean,
    onValueChange: () -> Unit,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(getDp(R.dimen.sort_panel_row_height))
            .toggleable(
                value = isToggled,
                onValueChange = { onValueChange() },
                role = Role.Checkbox
            ),
        horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.sort_panel_outer_vertical_arrangement)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isToggled,
            onCheckedChange = null,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.onPrimary,
                checkmarkColor = MaterialTheme.colorScheme.surfaceTint
            )
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = getSp(R.dimen.sp_password_item_block_subtitle)
        )
    }
}