package com.sean.cryptovault.f_settings.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp

@Composable
fun QuestionAnswerCard(
    question: String,
    answer: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = getDp(R.dimen.question_answer_card_elevation),
                shape = RoundedCornerShape(getDp(R.dimen.question_answer_card_shape))
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(getDp(R.dimen.question_answer_card_shape))
            )
            .padding(getDp(R.dimen.question_answer_card_padding)),
        verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.question_answer_card_padding))
    ) {
        Text(
            text = question,
            fontSize = getSp(R.dimen.sp_primary_text),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = answer,
            fontSize = getSp(R.dimen.sp_secondary_text)
        )
    }
}