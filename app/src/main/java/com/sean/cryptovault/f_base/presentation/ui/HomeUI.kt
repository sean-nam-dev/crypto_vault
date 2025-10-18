package com.sean.cryptovault.f_base.presentation.ui

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringArrayResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.f_base.data.room.card.Card
import com.sean.cryptovault.f_base.data.room.card.CardDao
import com.sean.cryptovault.f_base.data.room.vault.Vault
import com.sean.cryptovault.f_base.data.room.vault.VaultDao
import com.sean.cryptovault.f_base.presentation.ui_state.HomeUIState
import com.sean.cryptovault.f_base.presentation.component.ButtonRowWithMainButton
import com.sean.cryptovault.f_base.presentation.component.HomeFab
import com.sean.cryptovault.f_base.presentation.component.HomeTopBar
import com.sean.cryptovault.f_base.presentation.component.PagerNavigationBlock
import com.sean.cryptovault.f_base.presentation.component.SortPanel
import com.sean.cryptovault.f_base.presentation.ui_screen.CardItem
import com.sean.cryptovault.f_base.presentation.ui_screen.HomeVaultItem
import com.sean.cryptovault.f_base.presentation.view_model.CardItemViewModel
import com.sean.cryptovault.f_base.presentation.view_model.HomeVaultItemViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeUi(
    homeUIState: HomeUIState,
    pagerState: PagerState,
    scrollBehavior: TopAppBarScrollBehavior,
    focusRequester: FocusRequester,
    context: Context,
    navController: NavController,
    vaultDao: VaultDao,
    cardDao: CardDao,
    vaultList: List<Vault>,
    cardList: List<Card>,
    favoriteVaultList: List<Vault>,
    favoriteCardList: List<Card>,
    incorrectLinkText: String,
    onBurgerTopBarNavAction: () -> Unit,
    onBurgerTopBarLockAction: () -> Unit,
    onBurgerTopBarSearchAction: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    onClearAction: () -> Unit,
    onSearchNavAction: () -> Unit,
    mainFabAction: () -> Unit,
    vaultFabAction: () -> Unit,
    cardFabAction: () -> Unit,
    onSortPanelDismissRequest: () -> Unit,
    onSortClickAction: (Int) -> Unit,
    onTabClickAction: (Int) -> Unit,
    onSortPanelOpenRequest: () -> Unit,
    onListTypeClickAction: (Int) -> Unit,
) {
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehavior,
                focusRequester = focusRequester,
                isSearchVisible = homeUIState.isSearchVisible,
                searchText = homeUIState.searchText,
                onSearchTextChange = onSearchTextChange,
                onBurgerTopBarNavAction = onBurgerTopBarNavAction,
                onBurgerTopBarLockAction = onBurgerTopBarLockAction,
                onBurgerTopBarSearchAction = onBurgerTopBarSearchAction,
                onClearAction = onClearAction,
                onSearchNavAction = onSearchNavAction
            )
        },
        floatingActionButton = {
            HomeFab(
                isFabActive = homeUIState.isFabActive,
                mainFabAction = mainFabAction,
                vaultFabAction = vaultFabAction,
                cardFabAction = cardFabAction
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        if (homeUIState.isSortPanelActive) {
            SortPanel(
                selectedSortIndex = homeUIState.selectedSortIndex,
                onSortPanelDismissRequest = onSortPanelDismissRequest,
                onSortClickAction = onSortClickAction
            )
        }
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(
                    start = getDp(R.dimen.pager_window_big_padding),
                    top = getDp(R.dimen.pager_window_small_padding),
                    end = getDp(R.dimen.pager_window_big_padding)
                ),
            verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.home_ui_vertical_arrangement))
        ) {
            PagerNavigationBlock(
                selectedTabIndex = homeUIState.selectedTabIndex,
                titles = stringArrayResource(R.array.pager_navigation_block_titles),
                subtitles = stringArrayResource(R.array.pager_navigation_block_subtitles),
                navigationTexts = stringArrayResource(R.array.pager_navigation_block_titles).reversedArray(),
                includeLeftArrows = listOf(true, false),
                includeRightArrows = listOf(false, true),
                onClickAction = onTabClickAction
            )
            ButtonRowWithMainButton(
                currentListTypeIndex = homeUIState.currentListTypeIndex,
                onSortPanelOpenRequest = onSortPanelOpenRequest,
                onListTypeClickAction = onListTypeClickAction
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { index ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = getDp(R.dimen.home_ui_padding)),
                    verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.home_ui_vertical_arrangement))
                ) {
                    when(index) {
                        0 -> {
                            when {
                                homeUIState.currentListTypeIndex == 1 -> favoriteVaultList
                                homeUIState.isSearchVisible -> homeUIState.searchVaultList
                                else -> vaultList
                            }.run {
                                items(this, key = { it.id }) {
                                    HomeVaultItem(
                                        viewModel = HomeVaultItemViewModel(),
                                        navController = navController,
                                        context = context,
                                        vaultDao = vaultDao,
                                        vault = it,
                                        incorrectLinkText = incorrectLinkText
                                    )
                                }
                            }
                        }
                        1 -> {
                            when {
                                homeUIState.currentListTypeIndex == 1 -> favoriteCardList
                                homeUIState.isSearchVisible -> homeUIState.searchCardList
                                else -> cardList
                            }.run {
                                items(this, key = { it.id }) {
                                    CardItem(
                                        viewModel = CardItemViewModel(),
                                        navController = navController,
                                        cardDao = cardDao,
                                        card = it
                                    )
                                }
                            }
                        }
                    }
                    item { 
                        Spacer(modifier = Modifier.height(getDp(R.dimen.home_ui_spacer)))
                    }
                }
            }
        }
    }
}