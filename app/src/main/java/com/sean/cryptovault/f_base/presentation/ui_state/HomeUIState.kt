package com.sean.cryptovault.f_base.presentation.ui_state

import com.sean.cryptovault.f_base.data.room.card.Card
import com.sean.cryptovault.f_base.data.room.vault.Vault

data class HomeUIState(
    val searchVaultList: List<Vault> = emptyList(),
    val searchCardList: List<Card> = emptyList(),
    val searchText: String = "",
    val selectedTabIndex: Int = 0,
    val selectedSortIndex: Int,
    val currentListTypeIndex: Int = 0,
    val isFabActive: Boolean = false,
    val isSearchVisible: Boolean = false,
    val isSortPanelActive: Boolean = false,
)

