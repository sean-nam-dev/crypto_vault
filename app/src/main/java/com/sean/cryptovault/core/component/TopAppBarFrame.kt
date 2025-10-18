package com.sean.cryptovault.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarFrame(
    title: String,
    navIconId: Int,
    onNavAction: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    vararg iconPairs: Pair<Int, () -> Unit>
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = getSp(R.dimen.twenty_four_sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontFamily = FontFamily(Font(R.font.titillium_web_semi_bold))
            )
        },
        navigationIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(navIconId),
                contentDescription = null,
                modifier = Modifier
                    .padding(
                        start = getDp(R.dimen.burger_top_app_bar_side_padding),
                        end = getDp(R.dimen.burger_top_app_bar_side_wide_padding)
                    )
                    .size(getDp(R.dimen.burger_top_app_bar_icon_size))
                    .clip(CircleShape)
                    .clickable(onClick = onNavAction),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        actions = {
            for (pair in iconPairs) {
                Icon(
                    imageVector = ImageVector.vectorResource(pair.first),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = getDp(R.dimen.burger_top_app_bar_side_padding))
                        .size(getDp(R.dimen.burger_top_app_bar_icon_size))
                        .clip(CircleShape)
                        .clickable(onClick = pair.second),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun TopAppBarFramePreview() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    CryptoVaultTheme {
        Scaffold(
            topBar = {
                TopAppBarFrame(
                    title = "Home",
                    navIconId = R.drawable.ic_round_menu,
                    onNavAction = { /*TODO*/ },
                    scrollBehavior = scrollBehavior,
                    R.drawable.ic_round_sort to {},
                    R.drawable.ic_round_search to {}
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(it)
            )
        }
    }
}