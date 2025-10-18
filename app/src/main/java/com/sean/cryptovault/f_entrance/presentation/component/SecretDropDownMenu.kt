package com.sean.cryptovault.f_entrance.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.DpOffset
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun SecretDropDownMenu(
    isMenuActive: Boolean,
    onDismissRequest: () -> Unit,
    questionList: Array<String>,
    onMenuClickAction: (Int) -> Unit
) {
    DropdownMenu(
        expanded = isMenuActive,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceTint),
        offset = DpOffset(
            x = -getDp(R.dimen.secret_drop_down_menu_x_offset),
            y = -getDp(R.dimen.secret_drop_down_menu_y_offset)
        )
    ) {
        for (index in questionList.indices) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "${index + 1}. ${questionList[index]}",
                        modifier = Modifier.padding(getDp(R.dimen.secret_drop_down_text_padding)),
                        fontSize = getSp(R.dimen.twenty_sp),
                        fontFamily = FontFamily(Font(R.font.titillium_web_semi_bold))
                    )
                },
                onClick = { onMenuClickAction(index) },
                colors = MenuDefaults.itemColors(MaterialTheme.colorScheme.onSurfaceVariant)
            )
        }
    }
}