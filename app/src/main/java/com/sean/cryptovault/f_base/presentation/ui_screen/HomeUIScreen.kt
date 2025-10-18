package com.sean.cryptovault.f_base.presentation.ui_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.SORT_BY_DATE
import com.sean.cryptovault.core.common.SORT_BY_SERVICE
import com.sean.cryptovault.f_base.data.room.card.CardDao
import com.sean.cryptovault.f_base.data.room.vault.VaultDao
import com.sean.cryptovault.f_base.presentation.ui.HomeUi
import com.sean.cryptovault.f_base.presentation.ui_action.HomeUIAction
import com.sean.cryptovault.f_base.presentation.view_model.HomeViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeUIScreen(
    viewModel: HomeViewModel,
    vaultDao: VaultDao,
    cardDao: CardDao,
    navController: NavController,
    onBurgerTopBarNavAction: () -> Unit
) {
    val homeUIState = viewModel.state.collectAsState()

    val incorrectLinkText = stringResource(R.string.incorrect_link)

    val context = LocalContext.current

    val pagerState = rememberPagerState { 2 }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val focusRequester = FocusRequester()

    LaunchedEffect(homeUIState.value.selectedTabIndex) {
        pagerState.animateScrollToPage(homeUIState.value.selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        viewModel.onHomeAction(HomeUIAction.OnTabClickAction(pagerState.currentPage))
    }

    val (vaultList, cardList) = when(homeUIState.value.selectedSortIndex) {
        SORT_BY_DATE -> {
            vaultDao.getAllVaults().collectAsState(initial = emptyList()) to
                    cardDao.getAllCards().collectAsState(initial = emptyList())
        }
        SORT_BY_SERVICE -> {
            vaultDao.getAllVaultsServiceSorted().collectAsState(initial = emptyList()) to
                    cardDao.getAllCardsTitleSorted().collectAsState(initial = emptyList())
        }
        else -> {
            vaultDao.getAllVaultsIdentifierSorted().collectAsState(initial = emptyList()) to
                    cardDao.getAllCardsSubtitleSorted().collectAsState(initial = emptyList())
        }
    }
    val (favoriteVaultList, favoriteCardList) = vaultDao.getFavoriteVaults().collectAsState(initial = emptyList()) to
                                                        cardDao.getFavoriteCards().collectAsState(initial = emptyList())

    HomeUi(
        homeUIState = homeUIState.value,
        pagerState = pagerState,
        scrollBehavior = scrollBehavior,
        focusRequester = focusRequester,
        context = context,
        navController = navController,
        vaultDao = vaultDao,
        cardDao = cardDao,
        vaultList = vaultList.value,
        cardList = cardList.value,
        favoriteVaultList = favoriteVaultList.value,
        favoriteCardList = favoriteCardList.value,
        incorrectLinkText = incorrectLinkText,
        onBurgerTopBarNavAction = onBurgerTopBarNavAction,
        onBurgerTopBarLockAction = { viewModel.onHomeAction(HomeUIAction.OnBurgerTopBarLockAction(navController)) },
        onBurgerTopBarSearchAction = { viewModel.onHomeAction(HomeUIAction.OnBurgerTopBarSearchAction()) },
        onSearchTextChange = { viewModel.onHomeAction(HomeUIAction.OnSearchTextChange(it)) },
        onClearAction = { viewModel.onHomeAction(HomeUIAction.OnClearAction()) },
        onSearchNavAction = { viewModel.onHomeAction(HomeUIAction.OnSearchNavAction()) },
        mainFabAction = { viewModel.onHomeAction(HomeUIAction.OnMainFabAction()) },
        vaultFabAction = { viewModel.onHomeAction(HomeUIAction.OnVaultFabAction(navController)) },
        cardFabAction = { viewModel.onHomeAction(HomeUIAction.OnCardFabAction(navController)) },
        onSortPanelOpenRequest = { viewModel.onHomeAction(HomeUIAction.OnSortPanelOpenRequest()) },
        onSortPanelDismissRequest = { viewModel.onHomeAction(HomeUIAction.OnSortPanelDismissRequest()) },
        onSortClickAction = { viewModel.onHomeAction(HomeUIAction.OnSortClickAction(it)) },
        onTabClickAction = { viewModel.onHomeAction(HomeUIAction.OnTabClickAction(it)) },
        onListTypeClickAction = { viewModel.onHomeAction(HomeUIAction.OnListTypeClickAction(it)) }
    )
}