package com.sean.cryptovault.f_base.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sean.cryptovault.core.navigation.ScreenNavigation
import com.sean.cryptovault.f_base.presentation.ui_action.CardItemUIAction
import com.sean.cryptovault.f_base.presentation.ui_state.CardItemUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CardItemViewModel: ViewModel() {
    private val _state = MutableStateFlow(CardItemUIState())
    val state = _state.asStateFlow()

    fun onCardItemAction(action: CardItemUIAction) {
        when(action) {
            is CardItemUIAction.OnOptionMenuVisibilityChange -> {
                _state.update { it.copy(isOptionMenuVisible = !it.isOptionMenuVisible) }
            }
            is CardItemUIAction.OnOptionMenuPinAction -> {
                viewModelScope.launch {
                    action.cardDao.update(action.card.copy(isFavorite = !action.card.isFavorite))
                }
            }
            is CardItemUIAction.OnOptionMenuEditAction -> {
                action
                    .navController
                    .navigate(
                        ScreenNavigation.CardEdition.withArgs(
                            action.card.id.toString(),
                            action.card.bank,
                            action.card.number,
                            action.card.validThru,
                            action.card.cvv.ifEmpty { " " },
                            action.card.fullName,
                            action.card.isFavorite.toString(),
                        )
                    ) {
                        launchSingleTop = true
                    }
            }
            is CardItemUIAction.OnOptionMenuDeleteAction -> {
                viewModelScope.launch {
                    action.cardDao.delete(action.card)
                }
            }
            is CardItemUIAction.OnDataVisibilityChange -> {
                _state.update { it.copy(isDataVisible = !it.isDataVisible) }
            }
        }
    }
}