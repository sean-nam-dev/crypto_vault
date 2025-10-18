package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun SortPanel(
    selectedSortIndex: Int,
    onSortPanelDismissRequest: () -> Unit,
    onSortClickAction: (Int) -> Unit
) {
    Dialog(onDismissRequest = onSortPanelDismissRequest) {
        Card(
            shape = AlertDialogDefaults.shape,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceTint)
        ) {
            Column(
                modifier = Modifier.padding(getDp(R.dimen.sort_panel_column_padding)),
                verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.sort_panel_outer_vertical_arrangement))
            ) {
                Text(
                    text = stringResource(R.string.sort_by),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = getSp(R.dimen.twenty_sp),
                    fontFamily = FontFamily(Font(R.font.titillium_web_semi_bold))
                )
                Column {
                    stringArrayResource(R.array.sorting_type_list).forEachIndexed { index, type ->
                        CheckBoxFrame(
                            isToggled = selectedSortIndex == index,
                            onValueChange = { onSortClickAction(index) },
                            text = type
                        )
                    }
                }
            }
        }
    }
}