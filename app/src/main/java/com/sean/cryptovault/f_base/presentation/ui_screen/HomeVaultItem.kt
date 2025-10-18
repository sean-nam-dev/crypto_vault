package com.sean.cryptovault.f_base.presentation.ui_screen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.f_base.data.room.vault.Vault
import com.sean.cryptovault.f_base.data.room.vault.VaultDao
import com.sean.cryptovault.f_base.presentation.ui.HomeVaultItemUI
import com.sean.cryptovault.f_base.presentation.ui_action.HomeVaultItemUIAction
import com.sean.cryptovault.f_base.presentation.view_model.HomeVaultItemViewModel

@Composable
fun HomeVaultItem(
    viewModel: HomeVaultItemViewModel,
    navController: NavController,
    context: Context,
    vaultDao: VaultDao,
    vault: Vault,
    incorrectLinkText: String
) {
    val homeVaultItemUIState = viewModel.state.collectAsState()

    HomeVaultItemUI(
        vault = vault,
        homeVaultItemUIState = homeVaultItemUIState.value,
        onOptionMenuVisibilityChange = { viewModel.onHomeVaultItemAction(HomeVaultItemUIAction.OnOptionMenuVisibilityChange()) },
        onPasswordVisibilityChange = { viewModel.onHomeVaultItemAction(HomeVaultItemUIAction.OnPasswordVisibilityChange()) },
        optionMenuItems = listOf(
            Triple(
                stringResource(R.string.browse),
                R.drawable.ic_round_browse
            ) { viewModel.onHomeVaultItemAction(HomeVaultItemUIAction.OnBrowseClickAction(context, vault.link, incorrectLinkText)) },
            Triple(
                stringResource(R.string.favorite),
                if (vault.isFavorite) R.drawable.ic_round_favorite
                else R.drawable.ic_round_favorite_border
            ) { viewModel.onHomeVaultItemAction(HomeVaultItemUIAction.OnFavoriteClickAction(vaultDao, vault)) },
            Triple(
                stringResource(R.string.edit),
                R.drawable.ic_round_edit
            ) { viewModel.onHomeVaultItemAction(HomeVaultItemUIAction.OnEditClickAction(navController, vault)) },
            Triple(
                stringResource(R.string.delete),
                R.drawable.ic_round_delete
            ) { viewModel.onHomeVaultItemAction(HomeVaultItemUIAction.OnDeleteClickAction(vaultDao, vault)) }
        )
    )
}