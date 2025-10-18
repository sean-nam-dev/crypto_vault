package com.sean.cryptovault.f_settings.presentation.ui_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.core.navigation.ScreenNavigation
import com.sean.cryptovault.f_settings.common.IconTextButtonState
import com.sean.cryptovault.f_settings.presentation.ui.GeneralUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralUIScreen(
    navController: NavController,
    isDarkTheme: Boolean,
    onBurgerTopBarNavAction: () -> Unit,
    onDarkThemeChange: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val iconTextButtonStateList = listOf(
        listOf(
            IconTextButtonState(
                R.drawable.ic_round_language,
                stringResource(R.string.language)
            ) {
                navController.navigate(ScreenNavigation.Language.route)
            }
        ),
        listOf(
            IconTextButtonState(
                R.drawable.ic_outline_lock,
                stringResource(R.string.pin_and_secret_word)
            ) {
                navController.navigate(ScreenNavigation.PINChanger.route)
            }
        ),
        listOf(
            IconTextButtonState(
                R.drawable.ic_outline_message,
                stringResource(R.string.contact_us)
            ) {
                navController.navigate(ScreenNavigation.ContactUs.route)
            },
            IconTextButtonState(
                R.drawable.ic_outline_faq,
                stringResource(R.string.faq)
            ) {
                navController.navigate(ScreenNavigation.FAQ.route)
            }
        )
    )

    GeneralUI(
        scrollBehavior = scrollBehavior,
        isDarkTheme = isDarkTheme,
        onBurgerTopBarNavAction = onBurgerTopBarNavAction,
        iconTextButtonStateList = iconTextButtonStateList,
        onDarkThemeChange = onDarkThemeChange
    )
}

