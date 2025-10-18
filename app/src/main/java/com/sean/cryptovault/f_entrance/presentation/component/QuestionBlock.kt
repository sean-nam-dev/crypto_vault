package com.sean.cryptovault.f_entrance.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun QuestionBlock(
    text: String,
    isMenuActive: Boolean,
    onClickAction: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.question_block_arrangement)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            fontSize = getSp(R.dimen.twenty_four_sp),
            fontFamily = FontFamily(Font(R.font.titillium_web_semi_bold)),
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )
        IconButton(
            onClick = onClickAction,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    if (isMenuActive) R.drawable.ic_round_small_arrow_down
                    else R.drawable.ic_round_small_arrow_right
                ),
                contentDescription = null,
                modifier = Modifier.size(getDp(R.dimen.question_block_icon_size))
            )
        }
    }
}

@Preview
@Composable
private fun QuestionBlockPreview() {
    CryptoVaultTheme {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {
            QuestionBlock(
                text = "Question is here",
                isMenuActive = false,
                onClickAction = {}
            )
        }
    }
}