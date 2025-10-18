package com.sean.cryptovault.f_base.presentation.ui_action

import androidx.navigation.NavController

sealed class HomeUIAction {
    data class OnSearchTextChange(val text: String): HomeUIAction()
    data class OnBurgerTopBarLockAction(val navController: NavController): HomeUIAction()
    data class OnBurgerTopBarSearchAction(val asd: Unit = Unit): HomeUIAction()
    data class OnClearAction(val asd: Unit = Unit): HomeUIAction()
    data class OnSearchNavAction(val asd: Unit = Unit): HomeUIAction()
    data class OnMainFabAction(val asd: Unit = Unit): HomeUIAction()
    data class OnVaultFabAction(val navController: NavController): HomeUIAction()
    data class OnCardFabAction(val navController: NavController): HomeUIAction()
    data class OnSortPanelOpenRequest(val asd: Unit = Unit): HomeUIAction()
    data class OnSortPanelDismissRequest(val asd: Unit = Unit): HomeUIAction()
    data class OnSortClickAction(val sortIndex: Int): HomeUIAction()
    data class OnTabClickAction(val index: Int): HomeUIAction()
    data class OnListTypeClickAction(val index: Int): HomeUIAction()
}