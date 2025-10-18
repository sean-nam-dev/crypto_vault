package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun PieChart(
    strongPasswords: Int,
    totalPasswords: Int
) {
    val radiusOuter = getDp(R.dimen.pie_chart_radius_outer)
    val chartBarWidth = getDp(R.dimen.pie_chart_chart_bar_width)

    val totalPasswordsColor = MaterialTheme.colorScheme.inversePrimary
    val strongPasswordsColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = Modifier.size((radiusOuter.value * 2.25f).dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(radiusOuter * 2)
        ) {
            drawArc(
                color = totalPasswordsColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = chartBarWidth.toPx())
            )
        }
        Canvas(
            modifier = Modifier
                .size(radiusOuter * 2)
                .rotate(270f)
        ) {
            drawArc(
                color = strongPasswordsColor,
                startAngle = 0f,
                sweepAngle = 360 * strongPasswords.toFloat() / totalPasswords,
                useCenter = false,
                style = Stroke(
                    width = chartBarWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    }
}

@Preview
@Composable
private fun PieChartPreview() {
    var total by remember { mutableIntStateOf(27) }
    var good by remember { mutableIntStateOf(23) }


    CryptoVaultTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.primary))
            PieChart(good, total)
//            Box(modifier = Modifier
//                .size(140.dp)
//                .background(Color.White, CircleShape))
        }
    }
}