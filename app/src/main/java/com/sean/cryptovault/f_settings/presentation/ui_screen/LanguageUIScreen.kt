package com.sean.cryptovault.f_settings.presentation.ui_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.Language
import com.sean.cryptovault.f_settings.presentation.ui.LanguageUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageUIScreen(
    navController: NavController,
    language: String,
    onLanguageChange: (String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val languageArray = stringArrayResource(R.array.language_list)
    val languageOriginalArray = stringArrayResource(R.array.language_original_list)

    LanguageUI(
        scrollBehavior = scrollBehavior,
        navController = navController,
        currentLanguageCode = language,
        languageCodeArray = Language.entries.toTypedArray(),
        languageArray = languageArray,
        languageOriginalArray = languageOriginalArray,
        onLanguageChange = onLanguageChange
    )
}

