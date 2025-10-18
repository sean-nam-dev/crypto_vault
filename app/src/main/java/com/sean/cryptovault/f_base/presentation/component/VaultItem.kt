package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VaultItem(
    imageId: Int,
    title: Triple<String, Int?, (() -> Unit)?>,
    subtitle: Triple<String, Int?, (() -> Unit)?>,
    password: Triple<String, Int?, (() -> Unit)?>,
    content: @Composable() (ColumnScope.() -> Unit)?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.vault_item_padding))
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(imageId).also { it.prepareToDraw() },
            contentDescription = null,
            modifier = Modifier
                .offset(y = 3.dp)
                .clip(CircleShape)
                .size(getDp(R.dimen.vault_item_image_size)),
            contentScale = ContentScale.FillBounds
        )
        Column(modifier = Modifier.weight(1f)) {
            ActionText(
                text = title.first,
                fontSize = getSp(R.dimen.sp_secondary_text),
                iconId = title.second,
                onIconClickAction = title.third
            )
            Text(
                text = subtitle.first,
                modifier = Modifier
                    .offset(y = -(getDp(R.dimen.vault_item_text_offset)))
                    .basicMarquee(delayMillis = 2000),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = getSp(R.dimen.sp_primary_text),
                maxLines = 1
            )
            ActionText(
                text = password.first,
                fontSize = getSp(R.dimen.sp_primary_text),
                iconId = password.second,
                onIconClickAction = password.third
            )
            if (content != null) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun VaultItemPreview() {
    CryptoVaultTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = 20.dp, horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            repeat(10) {
                item {
                    VaultItem(
                        imageId = R.drawable.logo_google,
                        title = Triple("Google", R.drawable.ic_round_small_arrow_right, {}),
                        subtitle = Triple("raccoon@kirin.kityu", null, null),
                        password = Triple("•••••••••••", R.drawable.ic_round_visibility_on, {})
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            items(listOf("UPPERCASE", "LOWERCASE", "LENGTH", "DIGITS", "SYMBOLS")) {
                                Text(
                                    text = it,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}