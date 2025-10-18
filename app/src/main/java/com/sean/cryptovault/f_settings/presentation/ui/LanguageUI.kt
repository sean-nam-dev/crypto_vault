package com.sean.cryptovault.f_settings.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.Language
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.component.TopAppBarFrame
import com.sean.cryptovault.f_settings.presentation.component.RadioTextButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageUI(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController,
    currentLanguageCode: String,
    languageCodeArray: Array<Language>,
    languageArray: Array<String>,
    languageOriginalArray: Array<String>,
    onLanguageChange: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarFrame(
                title = stringResource(R.string.language),
                navIconId = R.drawable.ic_round_arrow_back,
                onNavAction = { navController.popBackStack() },
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .selectableGroup()
                .padding(paddingValues)
                .fillMaxSize()
                .padding(getDp(R.dimen.language_ui_screen_padding))
        ) {
            itemsIndexed(languageArray) {index, language ->
                RadioTextButton(
                    title = language,
                    subtitle = languageOriginalArray[index],
                    isSelected = currentLanguageCode == languageCodeArray[index].name,
                    onClickAction = { onLanguageChange(languageCodeArray[index].name) }
                )
            }
        }
    }
}