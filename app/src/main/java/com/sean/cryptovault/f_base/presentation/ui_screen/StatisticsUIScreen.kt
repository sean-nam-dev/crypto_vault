package com.sean.cryptovault.f_base.presentation.ui_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringArrayResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.f_base.data.room.card.CardDao
import com.sean.cryptovault.f_base.data.room.vault.VaultDao
import com.sean.cryptovault.f_base.common.PasswordManager
import com.sean.cryptovault.f_base.presentation.ui.StatisticsUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsUIScreen(
    navController: NavController,
    vaultDao: VaultDao,
    cardDao: CardDao,
    onBurgerTopBarNavAction: () -> Unit
) {
    val problemList = stringArrayResource(R.array.problem_list).toList()

    val currentVaultList = vaultDao.getAllVaults().collectAsState(initial = emptyList())
    val currentCardList = cardDao.getAllCards().collectAsState(initial = emptyList())

    val strongPasswords = vaultDao
        .getAllPasswords()
        .collectAsState(initial = emptyList())
        .value
        .filter { PasswordManager.checkProblems(it, problemList).isEmpty() }
    val duplicatedPasswordVaults = vaultDao.getDuplicatedPasswordVaults().collectAsState(initial = emptyList())

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    StatisticsUI(
        currentVaultList = currentVaultList.value,
        currentCardList = currentCardList.value,
        strongPasswords = strongPasswords,
        duplicatedPasswordVaults = duplicatedPasswordVaults.value,
        navController = navController,
        scrollBehavior = scrollBehavior,
        onBurgerTopBarNavAction = onBurgerTopBarNavAction
    )
}