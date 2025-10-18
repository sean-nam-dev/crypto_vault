package com.sean.cryptovault.f_settings.presentation.ui_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.f_settings.presentation.ui.FAQUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAQUIScreen(navController: NavController) {
    val faqQuestionArray = stringArrayResource(R.array.faq_question_list)
    val faqAnswerArray = stringArrayResource(R.array.faq_answer_list)

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    FAQUI(
        scrollBehavior = scrollBehavior,
        navController = navController,
        faqQuestionArray = faqQuestionArray,
        faqAnswerArray = faqAnswerArray
    )
}
