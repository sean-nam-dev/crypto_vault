package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun ButtonRowWithMainButton(
    currentListTypeIndex: Int,
    onSortPanelOpenRequest: () -> Unit,
    onListTypeClickAction: (Int) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.home_button_horizontal_arrangement))) {
        Button(
            onClick = onSortPanelOpenRequest,
            modifier = Modifier
                .padding(end = getDp(R.dimen.home_button_horizontal_arrangement))
                .width(getDp(R.dimen.home_button_button_width))
                .height(getDp(R.dimen.home_button_button_height)),
            shape = RoundedCornerShape(getDp(R.dimen.home_button_rounded_corner_shape)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceTint,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_round_sort),
                contentDescription = null,
                modifier = Modifier.size(getDp(R.dimen.home_button_icon_size))
            )
        }
        stringArrayResource(R.array.button_row_with_main_button_texts).forEachIndexed { index, text ->
            Button(
                onClick = { onListTypeClickAction(index) },
                modifier = Modifier.height(getDp(R.dimen.home_button_button_height)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentListTypeIndex == index) MaterialTheme.colorScheme.primary
                                        else MaterialTheme.colorScheme.surfaceTint,
                    contentColor = if (currentListTypeIndex == index) MaterialTheme.colorScheme.surfaceTint
                                        else MaterialTheme.colorScheme.primary
                ),
                border = BorderStroke(
                    width = getDp(R.dimen.home_button_border_stroke_width),
                    color = MaterialTheme.colorScheme.primary
                ),
                contentPadding = PaddingValues(
                    horizontal = getDp(R.dimen.home_button_horizontal_padding),
                    vertical = getDp(R.dimen.home_button_rounded_corner_shape)
                )
            ) {
                Text(
                    text = text,
                    fontSize = getSp(R.dimen.twelve_sp),
                    fontFamily = FontFamily(Font(R.font.titillium_web_bold))
                )
            }
        }
    }
}