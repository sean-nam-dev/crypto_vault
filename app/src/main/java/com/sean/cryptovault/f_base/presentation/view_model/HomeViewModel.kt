package com.sean.cryptovault.f_base.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sean.cryptovault.core.common.FileManager
import com.sean.cryptovault.core.di.annotation.qualifier.SortFMAnnotation
import com.sean.cryptovault.core.navigation.ScreenNavigation
import com.sean.cryptovault.f_base.data.room.card.CardDao
import com.sean.cryptovault.f_base.data.room.vault.VaultDao
import com.sean.cryptovault.f_base.presentation.ui_action.HomeUIAction
import com.sean.cryptovault.f_base.presentation.ui_state.HomeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val vaultDao: VaultDao,
    private val cardDao: CardDao,
    @SortFMAnnotation private val sortFM: FileManager
): ViewModel() {
    private val _state = MutableStateFlow(HomeUIState(selectedSortIndex = sortFM.read().toInt()))
    val state = _state.asStateFlow()

    fun onHomeAction(action: HomeUIAction) {
        when(action) {
            is HomeUIAction.OnSearchTextChange -> {
                _state.update { it.copy(searchText = action.text) }
                viewModelScope.launch {
                    _state.update {
                        if (action.text.isBlank()) {
                            it.copy(
                                searchVaultList = emptyList(),
                                searchCardList = emptyList()
                            )
                        } else {
                            it.copy(
                                searchVaultList = vaultDao.search(action.text).first(),
                                searchCardList = cardDao.search(action.text).first()
                            )
                        }
                    }
                }
            }
            is HomeUIAction.OnBurgerTopBarLockAction -> {
                action.navController.navigate(ScreenNavigation.Entrance.route) {
                    popUpTo(ScreenNavigation.Base.route) {
                        inclusive = true
                    }
                }
            }
            is HomeUIAction.OnBurgerTopBarSearchAction -> {
                _state.update {
                    it.copy(
                        currentListTypeIndex = 0,
                        isSearchVisible = true
                    )
                }
            }
            is HomeUIAction.OnClearAction -> {
                _state.update {
                    it.copy(
                        searchVaultList = emptyList(),
                        searchCardList = emptyList(),
                        searchText = ""
                    )
                }
            }
            is HomeUIAction.OnSearchNavAction -> {
                _state.update {
                    it.copy(
                        searchVaultList = emptyList(),
                        searchCardList = emptyList(),
                        isSearchVisible = false,
                        searchText = ""
                    )
                }
            }
            is HomeUIAction.OnMainFabAction -> {
                _state.update { it.copy(isFabActive = !it.isFabActive) }
            }
            is HomeUIAction.OnVaultFabAction -> {
                action.navController.navigate(ScreenNavigation.VaultCreation.route)
                _state.update { it.copy(isFabActive = false) }
            }
            is HomeUIAction.OnCardFabAction -> {
                action.navController.navigate(ScreenNavigation.CardCreation.route)
                _state.update { it.copy(isFabActive = false) }
            }
            is HomeUIAction.OnSortPanelOpenRequest -> {
                _state.update { it.copy(isSortPanelActive = true) }
            }
            is HomeUIAction.OnSortPanelDismissRequest -> {
                _state.update { it.copy(isSortPanelActive = false) }
            }
            is HomeUIAction.OnSortClickAction -> {
                _state.update {
                    it.copy(
                        selectedSortIndex = action.sortIndex,
                        isSortPanelActive = false
                    )
                }
                sortFM.assign(action.sortIndex.toString())
            }
            is HomeUIAction.OnTabClickAction -> {
                _state.update { it.copy(selectedTabIndex = action.index) }
            }
            is HomeUIAction.OnListTypeClickAction -> {
                _state.update { it.copy(currentListTypeIndex = action.index) }
            }
        }
    }
}