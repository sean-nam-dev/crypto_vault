package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.VAULT_IMAGE_HASHMAP
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun ItemCreationInsigniaFrame(
    currentImageId: Int,
    searchValue: String,
    imageHashMap: HashMap<String, Int>,
    isSearchBlockVisible: Boolean,
    isSearchBlockExpanded: Boolean,
    onSearchValueChange: (String) -> Unit,
    onSearchBlockVisualChange: () -> Unit,
    onSearchClickAction: () -> Unit,
    onSearchBlockExpansionChange: () -> Unit,
    onImageClickAction: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.item_creation_insignia_frame_column_arrangement))
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CurrentImageBlock(
                currentImageId = currentImageId,
                isSearchBlockVisible = isSearchBlockVisible,
                onSearchBlockVisualChange = onSearchBlockVisualChange
            )
        }
        AnimatedVisibility(
            visible = isSearchBlockVisible,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            SearchBlock(
                searchValue = searchValue,
                isSearchBlockExpanded = isSearchBlockExpanded,
                imageHashMap = imageHashMap,
                onSearchValueChange = onSearchValueChange,
                onSearchClickAction = onSearchClickAction,
                onSearchBlockExpansionChange = onSearchBlockExpansionChange,
                onImageClickAction = onImageClickAction
            )
        }
    }
}

@Preview
@Composable
private fun ItemCreationInsigniaFramePreview() {
    var currentImage by remember { mutableIntStateOf(R.drawable.logo_google) }
    var searchValue by remember { mutableStateOf("") }
    var isSearchBlockVisible by remember { mutableStateOf(false) }
    var isSearchBlockExpanded by remember { mutableStateOf(false) }

    CryptoVaultTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(15.dp)) {
            ItemCreationInsigniaFrame(
                currentImageId = currentImage,
                searchValue = searchValue,
                imageHashMap = VAULT_IMAGE_HASHMAP,
                isSearchBlockVisible = isSearchBlockVisible,
                isSearchBlockExpanded = isSearchBlockExpanded,
                onSearchValueChange = { searchValue = it },
                onSearchBlockVisualChange = { isSearchBlockVisible = !isSearchBlockVisible },
                onSearchClickAction = {},
                onSearchBlockExpansionChange = { isSearchBlockExpanded = !isSearchBlockExpanded },
                onImageClickAction = { currentImage = it }
            )
        }
    }
}