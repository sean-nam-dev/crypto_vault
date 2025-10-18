package com.sean.cryptovault.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.core.navigation.ScreenNavigation
import com.sean.cryptovault.f_base.presentation.component.BottomSheetButton
import com.sean.cryptovault.ui.theme.BrightBlue
import com.sean.cryptovault.ui.theme.BrightBrown
import com.sean.cryptovault.ui.theme.BrightGreen
import com.sean.cryptovault.ui.theme.BrightPurple
import com.sean.cryptovault.ui.theme.CryptoVaultTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBottomSheet(
    isSheetShown: Boolean,
    sheetState: SheetState,
    navController: NavController,
    onDismissRequest: () -> Unit,
    onHideBottomSheet: () -> Unit,
    content: @Composable() (BoxScope.() -> Unit)
) {
    val navigationText = stringResource(R.string.navigation_panel)

    val iconIds = listOf(
        R.drawable.ic_round_home,
        R.drawable.ic_round_statistics,
        R.drawable.ic_round_auto_awesome,
        R.drawable.ic_round_settings
    )
    val labels = listOf(
        stringResource(R.string.home),
        stringResource(R.string.statistics),
        stringResource(R.string.password_generator),
        stringResource(R.string.settings)
    )
    val colors = listOf(
        BrightPurple,
        BrightBrown,
        BrightGreen,
        BrightBlue
    )
    val routes = listOf(
        ScreenNavigation.Home.route,
        ScreenNavigation.Statistics.route,
        ScreenNavigation.PasswordGenerator.route,
        ScreenNavigation.Settings.route
    )

    Box(modifier = Modifier.fillMaxSize()) {
        content()

        if (isSheetShown) {
            ModalBottomSheet(
                onDismissRequest = onDismissRequest,
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            start = getDp(R.dimen.navigation_bottom_sheet_padding_small),
                            end = getDp(R.dimen.navigation_bottom_sheet_padding_small),
                            bottom = getDp(R.dimen.navigation_bottom_sheet_padding_big)
                        )
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.navigation_bottom_sheet_padding_small))
                ) {
                    Text(
                        text = navigationText,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = getSp(R.dimen.sp_pin_code),
                        fontWeight = FontWeight.Bold
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.navigation_bottom_sheet_arrangement))) {
                        routes.forEachIndexed { index, route ->
                            BottomSheetButton(
                                iconId = iconIds[index],
                                label = labels[index],
                                color = colors[index],
                                onClickAction = {
                                    navController.navigate(route) {
                                        launchSingleTop = true
                                    }
                                    onHideBottomSheet()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun NavigationBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    var isBottomSheetShown by remember { mutableStateOf(false) }

    val onShowBottomSheet: () -> Unit = { isBottomSheetShown = true }
    val onHideBottomSheet: () -> Unit = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                isBottomSheetShown = false
            }
        }
    }
    val onBottomSheetDR: () -> Unit = {
        isBottomSheetShown = false
    }
    val navController = rememberNavController()

    CryptoVaultTheme {
        NavigationBottomSheet(
            sheetState = sheetState,
            isSheetShown = isBottomSheetShown,
            navController = navController,
            onHideBottomSheet = onHideBottomSheet,
            onDismissRequest = onBottomSheetDR
        ) {
            NavHost(navController = navController, startDestination = "first") {
                composable("first") {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.secondary)) {
                        Button(onClick = onShowBottomSheet) {
                            Text(text = "Open bottom")
                        }
                        Button(onClick = { navController.navigate("second") }) {
                            Text(text = "Navigate to second window")
                        }
                    }
                }
                composable("second") {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)) {
                        Button(onClick = onShowBottomSheet) {
                            Text(text = "Open bottom")
                        }
                        Button(onClick = { navController.navigate("first") }) {
                            Text(text = "Navigate to first window")
                        }
                    }
                }
            }
        }
    }
}