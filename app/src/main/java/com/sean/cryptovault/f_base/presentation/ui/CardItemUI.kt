package com.sean.cryptovault.f_base.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sean.cryptovault.R
import com.sean.cryptovault.f_base.data.room.card.Card
import com.sean.cryptovault.f_base.presentation.ui_state.CardItemUIState
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.f_base.presentation.component.CardItemBottomBlock
import com.sean.cryptovault.f_base.presentation.component.CardItemTopBlock
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun CardItemUI(
    card: Card,
    cardItemUIState: CardItemUIState,
    onOptionMenuVisibilityChange: () -> Unit,
    onOptionMenuFavoriteAction: () -> Unit,
    onOptionMenuEditAction: () -> Unit,
    onOptionMenuDeleteAction: () -> Unit,
    onDataVisibilityChange: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(getDp(R.dimen.card_item_spacing))
            )
            .padding(getDp(R.dimen.card_item_spacing)),
        verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.card_item_spacing))
    ) {
        CardItemTopBlock(
            bank = card.bank,
            isFavorite = card.isFavorite,
            isOptionMenuVisible = cardItemUIState.isOptionMenuVisible,
            onOptionMenuVisibilityChange = onOptionMenuVisibilityChange,
            onOptionMenuFavoriteAction = onOptionMenuFavoriteAction,
            onOptionMenuEditAction = onOptionMenuEditAction,
            onOptionMenuDeleteAction = onOptionMenuDeleteAction
        )
        CardItemBottomBlock(
            cardNumber = card.number,
            cardCVV = card.cvv,
            cardOwnerFullName = card.fullName,
            cardValidThru = card.validThru,
            isDataVisible = cardItemUIState.isDataVisible,
            onDataVisibilityChange = onDataVisibilityChange
        )
    }
}

@Preview
@Composable
private fun CardItemUIPreview() {
    var isPinned by remember { mutableStateOf(false) }
    var isOptionMenuVisible by remember { mutableStateOf(false) }
    var isDataVisible by remember { mutableStateOf(false) }

    CryptoVaultTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(vertical = 15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            repeat(10) {
                item {
                    CardItemUI(
                        card = Card(
                            bank = "Visa",
                            number = "7200 3244 6539 7590",
                            validThru = "12/07",
                            cvv = "123",
                            fullName = "John Smith",
                            isFavorite = isPinned
                        ),
                        cardItemUIState = CardItemUIState(
                            isOptionMenuVisible = isOptionMenuVisible,
                            isDataVisible = isDataVisible
                        ),
                        onOptionMenuDeleteAction = {},
                        onOptionMenuEditAction = {},
                        onOptionMenuFavoriteAction = { isPinned = !isPinned },
                        onOptionMenuVisibilityChange = { isOptionMenuVisible = !isOptionMenuVisible },
                        onDataVisibilityChange = { isDataVisible = !isDataVisible }
                    )
                }
            }
        }
    }
}