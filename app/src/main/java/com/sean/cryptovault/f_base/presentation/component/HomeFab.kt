package com.sean.cryptovault.f_base.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@Composable
fun HomeFab(
    isFabActive: Boolean,
    mainFabAction: () -> Unit,
    vaultFabAction: () -> Unit,
    cardFabAction: () -> Unit
) {
    val transition = updateTransition(targetState = isFabActive, label = null)
    val mainFabRotation = transition.animateFloat(label = "main_fab_rotation") {
        when(it) {
            true -> 180f
            false -> 45f
        }
    }

    val secondaryFabDataList = listOf(
        Triple(250, vaultFabAction, R.drawable.ic_round_vault),
        Triple(150, cardFabAction, R.drawable.ic_round_card)
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.home_fab_vertical_arrangement)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (data in secondaryFabDataList) {
            AnimatedVisibility(
                visible = isFabActive,
                enter = slideInVertically(
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutSlowInEasing
                    ),
                    initialOffsetY = {
                        data.first
                    }
                ),
                exit = slideOutVertically(
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutSlowInEasing
                    ),
                    targetOffsetY = {
                        data.first
                    }
                )
            ) {
                FloatingActionButton(
                    onClick = data.second,
                    modifier = Modifier.size(getDp(R.dimen.home_fab_secondary_fab_size)),
                    shape = FloatingActionButtonDefaults.largeShape,
                    containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(data.third),
                        contentDescription = null
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = mainFabAction,
            modifier = Modifier
                .size(getDp(R.dimen.home_fab_main_fab_size))
                .rotate(mainFabRotation.value),
            shape = FloatingActionButtonDefaults.largeShape,
            containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_round_close),
                contentDescription = null,
                modifier = Modifier.size(getDp(R.dimen.home_fab_main_fab_icon_size))
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HomeFabPreview() {
    CryptoVaultTheme {
        var isFabActive by remember { mutableStateOf(false) }
        var isFabVisible by remember { mutableStateOf(true) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { /*TODO*/ },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
            floatingActionButton = {
                HomeFab(
                    isFabActive = isFabActive,
                    mainFabAction = { isFabActive = !isFabActive },
                    vaultFabAction = { /*TODO*/ },
                    cardFabAction = { /*TODO*/ }
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            containerColor = MaterialTheme.colorScheme.secondary
        ) {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(it)) {
                item {
                    Button(onClick = { isFabVisible = !isFabVisible }) {

                    }
                }
                for (i in 0..50) {
                    item {
                        Text(text = "$i", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
    }
}