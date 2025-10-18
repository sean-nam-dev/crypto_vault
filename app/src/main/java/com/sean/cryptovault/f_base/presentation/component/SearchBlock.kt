package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.VAULT_IMAGE_HASHMAP
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun SearchBlock(
    searchValue: String,
    isSearchBlockExpanded: Boolean,
    imageHashMap: HashMap<String, Int>,
    onSearchValueChange: (String) -> Unit,
    onSearchClickAction: () -> Unit,
    onSearchBlockExpansionChange: () -> Unit,
    onImageClickAction: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isSearchBlockExpanded) getDp(R.dimen.search_block_bjg_height) else getDp((R.dimen.search_block_small_height)))
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(getDp(R.dimen.search_block_rounded_corner_shape_size))
            )
            .padding(
                start = getDp(R.dimen.search_block_arrangement_and_padding),
                top = getDp(R.dimen.search_block_arrangement_and_padding),
                end = getDp(R.dimen.search_block_arrangement_and_padding)
            ),
        verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.search_block_arrangement_and_padding))
    ) {
        TextField(
            value = searchValue,
            onValueChange = onSearchValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                fontSize = getSp(R.dimen.sp_password_item_block_subtitle),
                fontWeight = FontWeight.SemiBold
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.search),
                    fontSize = getSp(R.dimen.sp_password_item_block_subtitle),
                    fontWeight = FontWeight.SemiBold
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_round_search),
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        if (isSearchBlockExpanded) R.drawable.ic_round_arrow_up
                        else R.drawable.ic_round_arrow_down
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(
                            role = Role.Button,
                            onClick = onSearchBlockExpansionChange
                        )
                )
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearchClickAction() }),
            maxLines = 1,
            shape = RoundedCornerShape(getDp(R.dimen.search_block_search_rounded_corner_shape)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.1f),
                unfocusedContainerColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.1f),

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,

                focusedTextColor = MaterialTheme.colorScheme.onSecondary,
                unfocusedTextColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),

                focusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),

                focusedLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),

                focusedTrailingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,

                cursorColor = MaterialTheme.colorScheme.onSecondary
            )
        )
        LazyHorizontalGrid(
            rows = GridCells.FixedSize(getDp(R.dimen.search_block_cell_size)),
            horizontalArrangement = Arrangement.spacedBy(getDp(R.dimen.search_block_arrangement_and_padding)),
            verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.search_block_arrangement_and_padding))
        ) {
            items(imageHashMap.values.toList()) {
                Image(
                    painter = painterResource(it),
                    contentDescription = null,
                    modifier = Modifier
                        .size(getDp(R.dimen.search_block_cell_size))
                        .clip(CircleShape)
                        .clickable(
                            role = Role.Button,
                            onClick = { onImageClickAction(it) }
                        ),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchBlockPreview() {
    var searchValue by remember { mutableStateOf("") }
    var isSearchExpanded by remember { mutableStateOf(false) }

    CryptoVaultTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(15.dp)) {
            SearchBlock(
                searchValue = searchValue,
                isSearchBlockExpanded = isSearchExpanded,
                onSearchValueChange = { searchValue = it },
                onSearchBlockExpansionChange = { isSearchExpanded = !isSearchExpanded },
                onSearchClickAction = {},
                imageHashMap = VAULT_IMAGE_HASHMAP,
                onImageClickAction = {}
            )
        }
    }
}