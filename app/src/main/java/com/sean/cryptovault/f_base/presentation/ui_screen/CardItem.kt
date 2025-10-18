package com.sean.cryptovault.f_base.presentation.ui_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.sean.cryptovault.f_base.data.room.card.Card
import com.sean.cryptovault.f_base.data.room.card.CardDao
import com.sean.cryptovault.f_base.presentation.ui.CardItemUI
import com.sean.cryptovault.f_base.presentation.ui_action.CardItemUIAction
import com.sean.cryptovault.f_base.presentation.view_model.CardItemViewModel

@Composable
fun CardItem(
    viewModel: CardItemViewModel,
    navController: NavController,
    cardDao: CardDao,
    card: Card
) {
    val cardItemUIState = viewModel.state.collectAsState()

    CardItemUI(
        card = card,
        cardItemUIState = cardItemUIState.value,
        onOptionMenuVisibilityChange = {
            viewModel.onCardItemAction(CardItemUIAction.OnOptionMenuVisibilityChange())
        },
        onOptionMenuFavoriteAction = {
            viewModel.onCardItemAction(
                CardItemUIAction.OnOptionMenuPinAction(
                    cardDao = cardDao,
                    card = card
                )
            )
        },
        onOptionMenuEditAction = {
            viewModel.onCardItemAction(
                CardItemUIAction.OnOptionMenuEditAction(
                    navController = navController,
                    card = card
                )
            )
        },
        onOptionMenuDeleteAction = {
            viewModel.onCardItemAction(
                CardItemUIAction.OnOptionMenuDeleteAction(
                    cardDao = cardDao,
                    card = card
                )
            )
        },
        onDataVisibilityChange = {
            viewModel.onCardItemAction(CardItemUIAction.OnDataVisibilityChange())
        }
    )
}