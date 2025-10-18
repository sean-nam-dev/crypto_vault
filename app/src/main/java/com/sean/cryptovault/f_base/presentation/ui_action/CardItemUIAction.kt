package com.sean.cryptovault.f_base.presentation.ui_action

import androidx.navigation.NavController
import com.sean.cryptovault.f_base.data.room.card.Card
import com.sean.cryptovault.f_base.data.room.card.CardDao

sealed class CardItemUIAction {
    data class OnOptionMenuVisibilityChange(val action: Unit = Unit): CardItemUIAction()
    data class OnOptionMenuPinAction(
        val cardDao: CardDao,
        val card: Card
    ): CardItemUIAction()
    data class OnOptionMenuEditAction(
        val navController: NavController,
        val card: Card
    ): CardItemUIAction()
    data class OnOptionMenuDeleteAction(
        val cardDao: CardDao,
        val card: Card
    ): CardItemUIAction()
    data class OnDataVisibilityChange(val action: Unit = Unit): CardItemUIAction()
}