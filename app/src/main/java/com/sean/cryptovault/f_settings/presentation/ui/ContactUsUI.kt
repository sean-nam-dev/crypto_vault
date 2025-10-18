package com.sean.cryptovault.f_settings.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.common.getSp
import com.sean.cryptovault.core.component.TopAppBarFrame

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUsUI(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController,
    onCopyClickAction: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarFrame(
                title = stringResource(R.string.contact_us),
                navIconId = R.drawable.ic_round_arrow_back,
                onNavAction = { navController.popBackStack() },
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(getDp(R.dimen.contact_us_ui_padding)),
            verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.contact_us_ui_vertical_arrangement))
        ) {
            Text(
                text = stringResource(R.string.you_can_reach_out_to_us_via),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = getSp(R.dimen.sp_primary_text)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.crypto_vault_team_gmail),
                    color = Color.White,
                    fontSize = getSp(R.dimen.sp_primary_text)
                )
                IconButton(onClick = onCopyClickAction) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_round_copy),
                        contentDescription = null
                    )
                }
            }
        }
    }
}