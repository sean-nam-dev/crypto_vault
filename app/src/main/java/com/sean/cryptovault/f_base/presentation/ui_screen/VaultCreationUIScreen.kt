package com.sean.cryptovault.f_base.presentation.ui_screen

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.sean.cryptovault.R
import com.sean.cryptovault.f_base.data.room.vault.VaultDatabase
import com.sean.cryptovault.f_base.presentation.ui.VaultCreationUI
import com.sean.cryptovault.f_base.presentation.ui_action.VaultCreationUIAction
import com.sean.cryptovault.f_base.presentation.view_model.VaultCreationViewModel
import com.sean.cryptovault.f_base.common.CustomTextFieldContent
import com.sean.cryptovault.core.common.FALSE
import com.sean.cryptovault.core.common.PASSWORD_IS_VISIBLE
import com.sean.cryptovault.ui.theme.CryptoVaultTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VaultCreationUIScreen(
    viewModel: VaultCreationViewModel,
    navController: NavController,
    passwordHideStatus: String
) {
    val vaultCreationUIState = viewModel.state.collectAsState()

    val toastText = stringResource(R.string.fill_required_fields)

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val vaultCreationLabelList = stringArrayResource(R.array.vault_creation_label_list)
    val vaultCreationIdentifierList = stringArrayResource(R.array.vault_creation_username_list)
    val switchContentList = stringArrayResource(R.array.switch_content_list)

    val switchContent: List<Triple<String, Boolean, (Boolean) -> Unit>> = listOf(
        Triple(
            switchContentList[0],
            vaultCreationUIState.value.isNumberActive
        ) { viewModel.onVaultCreationAction(VaultCreationUIAction.OnNumberSwitchClickAction(it)) },
        Triple(
            switchContentList[1],
            vaultCreationUIState.value.isSpecialSignActive
        ) { viewModel.onVaultCreationAction(VaultCreationUIAction.OnSpecialSignSwitchClickAction(it)) },
        Triple(
            switchContentList[2],
            vaultCreationUIState.value.isUppercaseLetterActive
        ) { viewModel.onVaultCreationAction(VaultCreationUIAction.OnUppercaseLetterSwitchClickAction(it)) }
    )

    val customTextFieldContent = listOf(
        CustomTextFieldContent(
            value = vaultCreationUIState.value.serviceText,
            label = vaultCreationLabelList[0],
            placeholder = vaultCreationIdentifierList[0],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = vaultCreationUIState.value.isServiceError,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnServiceValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnServiceTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = vaultCreationUIState.value.identifierText,
            label = vaultCreationLabelList[1],
            placeholder = vaultCreationIdentifierList[1],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = vaultCreationUIState.value.isIdentifierError,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnIdentifierValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnIdentifierTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = vaultCreationUIState.value.linkText,
            label = vaultCreationLabelList[2],
            placeholder = vaultCreationIdentifierList[2],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnLinkValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnLinkTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = vaultCreationUIState.value.passwordText,
            label = vaultCreationLabelList[3],
            placeholder = vaultCreationIdentifierList[3],
            trailingIcon = R.drawable.ic_round_settings_suggest,
            isError = vaultCreationUIState.value.isPasswordError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (passwordHideStatus == PASSWORD_IS_VISIBLE) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnPasswordValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnPasswordTrailingIconClickAction()) }
        )
    )

    VaultCreationUI(
        vaultCreationUIState = vaultCreationUIState.value,
        scrollBehavior = scrollBehavior,
        customTextFieldContent = customTextFieldContent,
        switchContent = switchContent,
        onNavAction = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnNavAction(navController)) },
        onSaveAction = {
            viewModel.onVaultCreationAction(
                VaultCreationUIAction.OnSaveAction(
                    navController = navController,
                    context = context,
                    toastText = toastText
                )
            )
        },
        onSearchValueChange = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnSearchValueChange(it)) },
        onSearchBlockVisualChange = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnSearchBlockVisualChange()) },
        onSearchClickAction = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnSearchClickAction(focusManager)) },
        onSearchBlockExpansionChange = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnSearchBlockExpansionChange()) },
        onImageClickAction = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnImageClickAction(it)) },
        onPasswordDialogDismissRequest = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnPasswordDialogDismissRequest()) },
        onSliderValueChange = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnSliderValueChange(it)) },
        onGenerateClickAction = { viewModel.onVaultCreationAction(VaultCreationUIAction.OnGenerateClickAction()) }
    )
}

@Preview
@Composable
fun VaultCreationUIScreenPreview() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val vaultDatabase = Room.databaseBuilder(
        context,
        VaultDatabase::class.java,
        "vault"
    ).build()
    val vaultDao = vaultDatabase.vaultDao()


    CryptoVaultTheme {
        VaultCreationUIScreen(
            viewModel = VaultCreationViewModel(vaultDao),
            navController = navController,
            passwordHideStatus = FALSE
        )
    }
}