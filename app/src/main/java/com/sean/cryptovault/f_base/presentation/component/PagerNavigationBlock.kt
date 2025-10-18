package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun PagerNavigationBlock(
    selectedTabIndex: Int,
    titles: Array<String>,
    subtitles: Array<String>,
    navigationTexts: Array<String>,
    includeLeftArrows: List<Boolean>,
    includeRightArrows: List<Boolean>,
    onClickAction: (Int) -> Unit
) {
    Box {
        titles.forEachIndexed { index, title ->
            AnimatedVisibility(
                visible = selectedTabIndex == index,
                enter = expandHorizontally(
                    animationSpec = tween(300,300),
                    expandFrom = Alignment.End
                ),
                exit = shrinkHorizontally(
                    animationSpec = tween(300),
                    shrinkTowards = Alignment.End
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.pager_navigation_block_vertical_arrangement))
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = getSp(R.dimen.twenty_four_sp),
                            fontFamily = FontFamily(Font(R.font.titillium_web_bold))
                        )
                        PagerNavigationButton(
                            text = navigationTexts[index],
                            includeLeftArrow = includeLeftArrows[index],
                            includeRightArrow = includeRightArrows[index],
                            onClickAction = { onClickAction(if (index == 1) 0 else 1) }
                        )
                    }
                    Text(
                        text = subtitles[index],
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = getSp(R.dimen.twelve_sp),
                        fontFamily = FontFamily(Font(R.font.titillium_web_regular))
                    )
                }
            }
        }
    }
}


