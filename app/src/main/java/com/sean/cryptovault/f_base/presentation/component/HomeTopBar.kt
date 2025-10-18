package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import com.sean.cryptovault.R
import com.sean.cryptovault.core.component.TopAppBarFrame

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    isSearchVisible: Boolean,
    searchText: String,
    scrollBehavior: TopAppBarScrollBehavior,
    focusRequester: FocusRequester,
    onBurgerTopBarNavAction: () -> Unit,
    onBurgerTopBarLockAction: () -> Unit,
    onBurgerTopBarSearchAction: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    onClearAction: () -> Unit,
    onSearchNavAction: () -> Unit
) {
    AnimatedVisibility(
        visible = !isSearchVisible,
        enter = slideInHorizontally(initialOffsetX = { -it }),
        exit = slideOutHorizontally(targetOffsetX = { -it })
    ) {
        TopAppBarFrame(
            title = stringResource(R.string.home),
            navIconId = R.drawable.ic_round_menu,
            onNavAction = onBurgerTopBarNavAction,
            scrollBehavior = scrollBehavior,
            R.drawable.ic_outline_lock to onBurgerTopBarLockAction,
            R.drawable.ic_round_search to onBurgerTopBarSearchAction,
        )
    }
    AnimatedVisibility(
        visible = isSearchVisible,
        enter = slideInHorizontally(initialOffsetX = { it }),
        exit = slideOutHorizontally(targetOffsetX = { it })
    ) {
        SearchTopAppBar(
            searchText = searchText,
            onSearchTextChange = onSearchTextChange,
            onClearAction = onClearAction,
            onNavAction = onSearchNavAction,
            scrollBehavior = scrollBehavior,
            focusRequester = focusRequester
        )
    }
}