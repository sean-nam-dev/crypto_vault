package com.sean.cryptovault.core.component

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun Test() {
    var dark by remember { mutableStateOf(false) }
    val systemUiController = rememberSystemUiController()

    val system = if (dark) Color.DarkGray else Color.Gray
    val status = if (dark) Color.White else Color.Black


    val gradient = Brush.horizontalGradient(colors = listOf(Color(0xFFCFBBFF), Color(0xFFC699F3)))

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(brush = gradient)) {
            append("Gradient Text")
        }
    }


    CryptoVaultTheme(darkTheme = dark) {
        SideEffect {
            systemUiController.setSystemBarsColor(system)
            systemUiController.setStatusBarColor(status)
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Asd",)
            BasicText(text = annotatedString)
        }
    }
}

@Preview
@Composable
private fun TestPreview() {
    Test()
}