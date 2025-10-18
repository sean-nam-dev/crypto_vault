package com.sean.cryptovault.f_settings.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.component.TopAppBarFrame
import com.sean.cryptovault.f_settings.presentation.component.QuestionAnswerCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAQUI(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController,
    faqQuestionArray: Array<String>,
    faqAnswerArray: Array<String>
) {
    Scaffold(
        topBar = {
            TopAppBarFrame(
                title = stringResource(R.string.faq),
                navIconId = R.drawable.ic_round_arrow_back,
                onNavAction = { navController.popBackStack() },
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(
                    start = getDp(R.dimen.contact_us_ui_padding),
                    end = getDp(R.dimen.contact_us_ui_padding),
                    bottom = getDp(R.dimen.contact_us_ui_padding)
                ),
            verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.contact_us_ui_padding))
        ) {
            itemsIndexed(faqQuestionArray) { index, question ->
                QuestionAnswerCard(
                    question = question,
                    answer = faqAnswerArray[index]
                )
            }
        }
    }
}