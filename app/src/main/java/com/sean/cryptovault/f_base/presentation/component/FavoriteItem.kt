package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun FavoriteItem(
    iconId: Int,
    text: String,
    index: Int,
    onFavoriteClickAction: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(getDp(R.dimen.favorite_item_image_padding)))
            .clickable(
                role = Role.Button,
                onClick = { onFavoriteClickAction(index) }
            )
            .padding(getDp(R.dimen.favorite_item_column_padding))
            .width(getDp(R.dimen.favorite_item_column_width)),
        verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.favorite_item_vertical_arrangement)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(iconId),
            contentDescription = null,
            modifier = Modifier
                .border(
                    width = getDp(R.dimen.favorite_item_image_border_width),
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f),
                    shape = CircleShape
                )
                .padding(getDp(R.dimen.favorite_item_image_padding))
                .size(getDp(R.dimen.favorite_item_image_size))
                .clip(CircleShape),
            contentScale = ContentScale.Fit
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = getSp(R.dimen.sp_favorite_item_text),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Preview
@Composable
private fun FavoriteItemPreview() {
    CryptoVaultTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)) {
            LazyRow(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                repeat(20) {
                    item {
                        FavoriteItem(
                            iconId = R.drawable.logo_google,
                            text = "shoxanamazov@gmail.com",
                            index = 0,
                            onFavoriteClickAction = {

                            }
                        )
                    }
                }
            }
        }
    }
}