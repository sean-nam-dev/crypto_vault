package com.sean.cryptovault.f_base.presentation.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.sean.cryptovault.R
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.ui.theme.CryptoVaultTheme
import kotlinx.coroutines.launch

@Composable
fun HomeSnackbar(
    hostState: SnackbarHostState,
    onSnackbarAction: () -> Unit
) {
    SnackbarHost(hostState) {
        Snackbar(
            modifier = Modifier
                .padding(getDp(R.dimen.home_snackbar_padding))
                .shadow(
                    elevation = getDp(R.dimen.home_snackbar_elevation),
                    shape = SnackbarDefaults.shape
                ),
            action = {
                TextButton(onClick = onSnackbarAction) {
                    Text(
                        text = stringResource(R.string.restore),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {
            Text(
                text = stringResource(R.string.has_been_deleted_successfully),
                color = MaterialTheme.colorScheme.onSecondary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
private fun HomeSnackbarPreview() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    CryptoVaultTheme {
        Scaffold(
            snackbarHost = {
                HomeSnackbar(hostState = snackbarHostState) {
                    Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(it)) {
                Button(onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "",
                            actionLabel = "",
                            duration = SnackbarDuration.Short
                        )
                    }
                }) {
                    Text(text = "Click")
                }
            }
        }
    }
}