package com.sean.cryptovault.f_settings.presentation.ui_screen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.f_settings.presentation.ui.ContactUsUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUsUIScreen(navController: NavController) {
    val cryptoVaultGmailText = stringResource(R.string.crypto_vault_team_gmail)
    val officialGmailText = stringResource(R.string.official_gmail)
    val successfullyCopiedText = stringResource(R.string.successfully_copied)

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    val onCopyClickAction: () -> Unit = {
        clipboard.setPrimaryClip(
            ClipData.newPlainText(
                officialGmailText,
                cryptoVaultGmailText
            )
        )
        Toast.makeText(context, successfullyCopiedText, Toast.LENGTH_SHORT).show()
    }

    ContactUsUI(
        scrollBehavior = scrollBehavior,
        navController = navController,
        onCopyClickAction = onCopyClickAction
    )
}

