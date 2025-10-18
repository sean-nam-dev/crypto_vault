package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onClearAction: () -> Unit,
    onNavAction: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    focusRequester: FocusRequester
) {
    TopAppBar(
        title = { /*TODO*/ },
        actions = {
            Row(
                modifier = Modifier
                    .padding(
                        start = getDp(R.dimen.search_top_app_bar_padding_start),
                        end = getDp(R.dimen.search_top_app_bar_padding_end)
                    )
                    .fillMaxWidth()
                    .height(getDp(R.dimen.search_top_app_bar_row_height))
                    .background(
                        color = MaterialTheme.colorScheme.surfaceTint,
                        shape = RoundedCornerShape(getDp(R.dimen.search_top_app_bar_side_padding))
                    )
                    .padding(horizontal = getDp(R.dimen.search_top_app_bar_side_padding)),
                horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.search_top_app_bar_horizontal_arrangement)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_round_arrow_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(getDp(R.dimen.search_top_app_bar_icon_size))
                        .clickable(onClick = onNavAction)
                        .clip(CircleShape),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                BasicTextField(
                    value = searchText,
                    onValueChange = { onSearchTextChange(it) },
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester)
                        .onGloballyPositioned { focusRequester.requestFocus() },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = getSp(R.dimen.twenty_sp),
                        fontFamily = FontFamily(Font(R.font.titillium_web_regular))
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    ),
                    maxLines = 1,
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant)
                )
                if (searchText.isNotEmpty()) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_round_cancel),
                        contentDescription = null,
                        modifier = Modifier
                            .size(getDp(R.dimen.search_top_app_bar_icon_size))
                            .clickable(onClick = onClearAction)
                            .clip(CircleShape),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun SearchTopAppBarPreview() {
    CryptoVaultTheme {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        var text by remember { mutableStateOf("") }
        val focusRequester = FocusRequester()

        Scaffold(
            topBar = {
                SearchTopAppBar(
                    searchText = text,
                    onSearchTextChange = { text = it },
                    onClearAction = { text = "" },
                    onNavAction = {},
                    scrollBehavior = scrollBehavior,
                    focusRequester = focusRequester
                )
            },
            containerColor = MaterialTheme.colorScheme.secondary
        ) {
            it
        }
    }
}